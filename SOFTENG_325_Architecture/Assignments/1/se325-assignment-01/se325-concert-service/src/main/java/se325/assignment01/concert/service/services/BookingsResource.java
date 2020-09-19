package se325.assignment01.concert.service.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se325.assignment01.concert.common.dto.BookingDTO;
import se325.assignment01.concert.service.domain.Booking;
import se325.assignment01.concert.service.mapper.BookingMapper;
import se325.assignment01.concert.service.util.ConcertUtils;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("/concert-service/bookings")
public class BookingsResource {
    private static Logger LOGGER = LoggerFactory.getLogger(BookingsResource.class);

    @GET
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = ConcertUtils.getBookings();
        List<BookingDTO> bookingDTOS = new ArrayList<>();
        for (Booking booking : bookings) {
            bookingDTOS.add(BookingMapper.toDTO(booking));
        }
        if (bookingDTOS.size() == 0) {
            LOGGER.info("Could not find any bookings");
            throw new NotFoundException();
        }
        return bookingDTOS;
    }

}
