package se325.assignment01.concert.service.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se325.assignment01.concert.common.dto.BookingDTO;
import se325.assignment01.concert.common.dto.BookingRequestDTO;
import se325.assignment01.concert.service.domain.Booking;
import se325.assignment01.concert.service.mapper.BookingMapper;
import se325.assignment01.concert.service.util.ConcertUtils;

import javax.persistence.EntityExistsException;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;

@Path("/concert-service/bookings")
public class BookingsResource {
    private static Logger LOGGER = LoggerFactory.getLogger(BookingsResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BookingDTO> getAllBookings(@CookieParam("auth") String userId) {
        // Confirm auth cookie present
        if (userId == null) throw new WebApplicationException(Response.Status.UNAUTHORIZED);

        List<Booking> bookings = ConcertUtils.getBookingsForUser(userId);
        // Convert all bookings to DTO
        List<BookingDTO> bookingDTOS = new ArrayList<>();
        for (Booking booking : bookings) {
            bookingDTOS.add(BookingMapper.toDTO(booking));
        }
        return bookingDTOS;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public BookingDTO getBookingById(@CookieParam("auth") String userId, @PathParam("id") String bookingId) {
        // Confirm auth cookie present
        if (userId == null) throw new WebApplicationException(Response.Status.UNAUTHORIZED);

        Booking booking = ConcertUtils.getBookingById(bookingId);
        if (booking == null) throw new NotFoundException();
        if (booking.getUser().getId() != Long.parseLong(userId)) {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
        return BookingMapper.toDTO(booking);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBooking(@CookieParam("auth") String userId,
                               BookingRequestDTO bookingRequestDTO,
                               @Context UriInfo uriInfo,
                               @Context ResourceContext resourceContext
    ) {
        // Confirm auth cookie present
        if (userId == null) throw new WebApplicationException(Response.Status.UNAUTHORIZED);

        Booking booking = BookingMapper.toDomainModel(bookingRequestDTO, userId);
        try {
            ConcertUtils.persistBooking(booking);
        } catch (EntityExistsException ex) {
            // Seat already booked
            throw new ForbiddenException();
        }
        // Notify subscriptions a new booking has been made
        resourceContext.getResource(SubscriptionResource.class).processBookingChange(booking.getConcertDate());
        // Build location of booking resource
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Long.toString(booking.getId()));
        LOGGER.info("New booking successfully created.");
        return Response.created(uriBuilder.build()).build();
    }

}
