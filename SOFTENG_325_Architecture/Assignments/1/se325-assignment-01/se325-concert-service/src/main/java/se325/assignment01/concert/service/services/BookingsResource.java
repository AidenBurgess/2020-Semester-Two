package se325.assignment01.concert.service.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se325.assignment01.concert.common.dto.BookingDTO;
import se325.assignment01.concert.common.dto.BookingRequestDTO;
import se325.assignment01.concert.service.domain.Booking;
import se325.assignment01.concert.service.domain.ConcertDate;
import se325.assignment01.concert.service.domain.User;
import se325.assignment01.concert.service.mapper.BookingMapper;
import se325.assignment01.concert.service.util.ConcertUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Path("/concert-service/bookings")
public class BookingsResource {
    private static Logger LOGGER = LoggerFactory.getLogger(BookingsResource.class);

    @GET
    public List<BookingDTO> getAllBookings(@CookieParam("auth") String userId) {
        if (userId == null) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
        LOGGER.debug(userId);
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

    @GET
    @Path("{id}")
    public BookingDTO getBookingById(@CookieParam("auth") String userId, @PathParam("id") String bookingId) {
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBooking(@CookieParam("auth") String userId, BookingRequestDTO bookingRequestDTO, @Context UriInfo uriInfo) throws URISyntaxException {
        if (userId == null) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
        Booking booking = BookingMapper.toDomainModel(bookingRequestDTO, userId);
        ConcertUtils.persistBooking(booking);
        // Change persistBooking to also change boolean of Seat(isBooked)

        System.out.println(ConcertUtils.getBookings());

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Long.toString(booking.getId()));
        return Response.created(uriBuilder.build()).build();
    }

}
