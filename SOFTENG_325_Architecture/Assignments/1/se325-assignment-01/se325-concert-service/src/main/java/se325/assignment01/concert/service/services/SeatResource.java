package se325.assignment01.concert.service.services;

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{dateParam}")
    public List<SeatDTO> getSeatsForDate(
            @PathParam("dateParam") LocalDateTimeParam dateParam,
            @DefaultValue("Any") @QueryParam("status") BookingStatus bookingStatus
    ) {
        LocalDateTime date = dateParam.getLocalDateTime();
        List<Seat> seats = ConcertUtils.getSeatsForDay(date);
        // Convert seats to DTO
        List<SeatDTO> seatDTOS = new ArrayList<>();
        // Filter seats by bookingStatus
        for (Seat seat : seats) {
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

        return seatDTOS;
    }
}
