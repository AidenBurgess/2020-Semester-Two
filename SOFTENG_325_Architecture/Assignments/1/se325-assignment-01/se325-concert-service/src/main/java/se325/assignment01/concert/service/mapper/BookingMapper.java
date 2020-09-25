package se325.assignment01.concert.service.mapper;

import se325.assignment01.concert.common.dto.BookingDTO;
import se325.assignment01.concert.common.dto.BookingRequestDTO;
import se325.assignment01.concert.common.dto.ConcertDTO;
import se325.assignment01.concert.common.dto.SeatDTO;
import se325.assignment01.concert.service.domain.*;
import se325.assignment01.concert.service.util.ConcertUtils;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

public class BookingMapper {
    public static Booking toDomainModel(BookingRequestDTO bookingRequestDTO, String userId) {
        User user = ConcertUtils.getUserById(userId);
        ConcertDate concertDate = ConcertUtils.getConcertDateByDate(bookingRequestDTO.getDate());
        if (concertDate == null || concertDate.getConcert().getId() != bookingRequestDTO.getConcertId() || bookingRequestDTO.getSeatLabels().size() == 0) {
            throw new BadRequestException();
        }

        List<Seat> seats = ConcertUtils.getSeatsFromLabels(bookingRequestDTO.getSeatLabels(), concertDate.getDate());
        if (seats.size() != bookingRequestDTO.getSeatLabels().size()) {
            throw new BadRequestException();
        }
        System.out.println(seats);

        return new Booking(concertDate, user, seats);
    }


    public static BookingDTO toDTO(Booking booking) {
        List<Seat> seats = booking.getBookedSeats();
        List<SeatDTO> seatDTOS = new ArrayList<>();
        for (Seat seat : seats) {
            seatDTOS.add(SeatMapper.toDTO(seat));
        }

        return new BookingDTO(booking.getConcertDate().getConcert().getId(), booking.getConcertDate().getDate(), seatDTOS);
    }
}
