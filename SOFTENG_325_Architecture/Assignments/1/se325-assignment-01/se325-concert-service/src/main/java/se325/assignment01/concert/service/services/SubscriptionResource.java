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
import java.util.List;
import java.util.Vector;

@Path("/concert-service/subscribe")
public class SubscriptionResource {

    private final List<ConcertInfoSubscription> concertInfoSubscriptions = new Vector<>();


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("concertInfo")
    public void addSubscription(
            @CookieParam("auth") String userId,
            ConcertInfoSubscriptionDTO subscriptionInfoDTO,
            @Suspended AsyncResponse sub
    ) {
        if (userId == null) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }

        Concert concert = ConcertUtils.getConcertById(subscriptionInfoDTO.getConcertId());
        ConcertDate concertDate = ConcertUtils.getConcertDateByDate(subscriptionInfoDTO.getDate());

        if (concert == null || concertDate == null) {
            throw new BadRequestException();
        }

        concertInfoSubscriptions.add(new ConcertInfoSubscription(sub, concertDate.getDate(), subscriptionInfoDTO.getPercentageBooked()));
    }

    public void processBookingChange(ConcertDate concertDate) {
        System.out.println("processBookingChange() called");
        // find out percentage booked
        List<Seat> seatsForConcert = ConcertUtils.getSeatsForDay(concertDate.getDate());
        int bookedSeats = (int) seatsForConcert.stream().filter(Seat::isBooked).count();
        float percentBooked = (bookedSeats * 100.0f) / seatsForConcert.size();
        System.out.println("bookedseats: " + bookedSeats);
        System.out.println("percentBooked: " + percentBooked);
        System.out.println("size: " + seatsForConcert.size());


        // Go through each subscription
        synchronized (concertInfoSubscriptions) {
            for (ConcertInfoSubscription sub : concertInfoSubscriptions) {
                System.out.println("checking: " + sub.toString());

                // Check if they are subscribed to current concertdate
                if (sub.getDate().equals(concertDate.getDate()) && percentBooked >= sub.getPercentageBooked()) {
                    // check if bookings is enough to notify
                    // notify with ConcertInfoNotificationDTO
                    System.out.println("releasing subscription");
                    sub.getSubscription().resume(new ConcertInfoNotificationDTO(seatsForConcert.size() - bookedSeats));
                }
            }
        }
    }

}
