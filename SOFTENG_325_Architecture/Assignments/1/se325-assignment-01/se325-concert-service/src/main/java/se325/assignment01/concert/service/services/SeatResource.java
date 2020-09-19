package se325.assignment01.concert.service.services;

import se325.assignment01.concert.common.dto.PerformerDTO;
import se325.assignment01.concert.common.dto.SeatDTO;
import se325.assignment01.concert.service.domain.Performer;
import se325.assignment01.concert.service.domain.Seat;
import se325.assignment01.concert.service.mapper.PerformerMapper;
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
    @Path("{dateString}")
    public List<SeatDTO> getSeatsForDate(@PathParam("dateString") String dateString) {
        LocalDateTime date = LocalDateTime.parse(dateString);
        List<Seat> seats = ConcertUtils.getSeatsForDay(date);
        List<SeatDTO> seatDTOS = new ArrayList<>();
        for (Seat seat : seats) {
            seatDTOS.add(SeatMapper.toDTO(seat));
        }
        if (seats.size() == 0) {
            throw new NotFoundException("Could not find seats for date: " + dateString);
        }

        return seatDTOS;
    }
}
