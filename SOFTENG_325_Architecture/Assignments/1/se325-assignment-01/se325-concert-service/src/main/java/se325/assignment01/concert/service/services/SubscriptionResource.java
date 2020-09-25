package se325.assignment01.concert.service.services;

import se325.assignment01.concert.common.dto.ConcertInfoNotificationDTO;
import se325.assignment01.concert.common.dto.ConcertInfoSubscriptionDTO;
import se325.assignment01.concert.service.domain.Concert;
import se325.assignment01.concert.service.domain.ConcertDate;
import se325.assignment01.concert.service.domain.ConcertInfoSubscription;
import se325.assignment01.concert.service.domain.Seat;
import se325.assignment01.concert.service.util.ConcertUtils;

import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

@Path("/concert-service/subscribe")
public class SubscriptionResource {

    private static final List<ConcertInfoSubscription> concertInfoSubscriptions = new Vector<>();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("concertInfo")
    public void addConcertInfoSubscription(
            @CookieParam("auth") String userId,
            @Suspended AsyncResponse sub,
            ConcertInfoSubscriptionDTO subInfoDTO
    ) {
        if (userId == null) throw new WebApplicationException(Response.Status.UNAUTHORIZED);

        Concert concert = ConcertUtils.getConcertById(subInfoDTO.getConcertId());
        ConcertDate concertDate = ConcertUtils.getConcertDateByDate(subInfoDTO.getDate());

        if (concert == null || concertDate == null) throw new BadRequestException();

        concertInfoSubscriptions.add(new ConcertInfoSubscription(sub, concertDate.getDate(), subInfoDTO.getPercentageBooked()));
    }

    public void processBookingChange(ConcertDate concertDate) {
        // find out seat statistics
        List<Seat> seatsForConcert = ConcertUtils.getSeatsForDay(concertDate.getDate());
        int bookedSeats = (int) seatsForConcert.stream().filter(Seat::isBooked).count();
        float percentBooked = (bookedSeats * 100.0f) / seatsForConcert.size();

        // Go through each subscription
        synchronized (concertInfoSubscriptions) {
            for (Iterator<ConcertInfoSubscription> iterator = concertInfoSubscriptions.iterator(); iterator.hasNext(); ) {
                ConcertInfoSubscription sub = iterator.next();
                // Check if they match concert, date, and have percentage high enough to trigger notification
                if (sub.getDate().equals(concertDate.getDate()) && percentBooked >= sub.getPercentageBooked()) {
                    // notify with ConcertInfoNotificationDTO
                    sub.getSubscription().resume(new ConcertInfoNotificationDTO(seatsForConcert.size() - bookedSeats));
                    iterator.remove();
                }
            }
        }
    }

}
