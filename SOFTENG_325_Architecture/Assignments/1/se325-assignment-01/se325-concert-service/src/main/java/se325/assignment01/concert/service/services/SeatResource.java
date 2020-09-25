package se325.assignment01.concert.service.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se325.assignment01.concert.common.dto.SeatDTO;
import se325.assignment01.concert.common.types.BookingStatus;
import se325.assignment01.concert.service.domain.Seat;
import se325.assignment01.concert.service.jaxrs.LocalDateTimeParam;
import se325.assignment01.concert.service.mapper.SeatMapper;
import se325.assignment01.concert.service.util.ConcertUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Path("/concert-service/seats")
public class SeatResource {

    private static Logger LOGGER = LoggerFactory.getLogger(SeatResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{dateParam}")
    public List<SeatDTO> getSeatsForDate(
            @PathParam("dateParam") LocalDateTimeParam dateParam,
            @DefaultValue("Any") @QueryParam("status") BookingStatus bookingStatus
    ) {
        LocalDateTime date = dateParam.getLocalDateTime();
        List<Seat> seats = ConcertUtils.getSeatsForDay(date);
        List<SeatDTO> seatDTOS = new ArrayList<>();
        for (Seat seat : seats) {
            // TODO: Can move this logic into DB step
            switch (bookingStatus) {
                case Any:
                    seatDTOS.add(SeatMapper.toDTO(seat));
                    break;
                case Booked:
                    if (seat.isBooked()) seatDTOS.add(SeatMapper.toDTO(seat));
                    break;
                case Unbooked:
                    if (!seat.isBooked()) seatDTOS.add(SeatMapper.toDTO(seat));
                    break;
            }
        }
/*        if (seatDTOS.size() == 0) {
            LOGGER.info("Could not find " + bookingStatus + " seats for date: " + date.toString());
            throw new NotFoundException();
        }*/

        return seatDTOS;
    }
}
