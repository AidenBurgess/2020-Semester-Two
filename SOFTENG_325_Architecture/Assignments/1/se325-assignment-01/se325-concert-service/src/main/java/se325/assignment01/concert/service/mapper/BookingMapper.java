package se325.assignment01.concert.service.mapper;

import se325.assignment01.concert.common.dto.BookingDTO;
import se325.assignment01.concert.common.dto.ConcertDTO;
import se325.assignment01.concert.common.dto.SeatDTO;
import se325.assignment01.concert.service.domain.Booking;
import se325.assignment01.concert.service.domain.Concert;
import se325.assignment01.concert.service.domain.Seat;

import java.util.ArrayList;
import java.util.List;

public class BookingMapper {
    public static Booking toDomainModel(BookingDTO bookingDTO) {
        return null;
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
