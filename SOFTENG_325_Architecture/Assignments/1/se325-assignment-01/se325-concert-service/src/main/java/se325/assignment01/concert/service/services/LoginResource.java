package se325.assignment01.concert.service.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se325.assignment01.concert.common.dto.UserDTO;
import se325.assignment01.concert.service.domain.User;
import se325.assignment01.concert.service.mapper.UserMapper;
import se325.assignment01.concert.service.util.ConcertUtils;

import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import java.util.UUID;

@Path("concert-service/login")
public class LoginResource {

    private static Logger LOGGER = LoggerFactory.getLogger(LoginResource.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(UserDTO userDTO, @CookieParam("auth") Cookie clientId) {
        try {
            // Check credentials
            User user = ConcertUtils.getUserFromLogin(userDTO.getUsername(), userDTO.getPassword());
            // setup cookie for user
            NewCookie cookie = makeCookie(clientId);

            // Return cookie to user
            LOGGER.info(userDTO.getUsername() + " successfully signed in!");
            LOGGER.debug(cookie.toString());
            return Response.ok().cookie(cookie).build();
        } catch (NoResultException ex) {
            //Response.Status.UNAUTHORIZED.getStatusCode();
            return Response.status(Response.Status.UNAUTHORIZED).build();
            //throw new NotFoundException("Could not find user for given credentials");
        }
    }

    /**
     * Helper method that can be called from every service method to generate a
     * NewCookie instance, if necessary, based on the clientId parameter.
     *
     * @param clientId the Cookie whose name is Config.CLIENT_COOKIE, extracted
     *                 from a HTTP request message. This can be null if there was no cookie
     *                 named Config.CLIENT_COOKIE present in the HTTP request message.
     * @return a NewCookie object, with a generated UUID value, if the clientId
     * parameter is null. If the clientId parameter is non-null (i.e. the HTTP
     * request message contained a cookie named Config.CLIENT_COOKIE), this
     * method returns null as there's no need to return a NewCookie in the HTTP
     * response message.
     */
    private NewCookie makeCookie(Cookie clientId) {
        NewCookie newCookie = null;

        if (clientId == null) {
            newCookie = new NewCookie("auth", UUID.randomUUID().toString());
            LOGGER.info("Generated cookie: " + newCookie.getValue());
        }

        return newCookie;
    }
}
