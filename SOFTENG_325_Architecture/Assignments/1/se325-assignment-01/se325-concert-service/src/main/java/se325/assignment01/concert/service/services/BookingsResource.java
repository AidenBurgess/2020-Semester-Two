package se325.assignment01.concert.service.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se325.assignment01.concert.common.dto.BookingDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/concert-service/bookings")
public class BookingsResource {
    private static Logger LOGGER = LoggerFactory.getLogger(BookingsResource.class);

    @GET
    public List<BookingDTO> getAllBookings() {
        return null;
    }

}
