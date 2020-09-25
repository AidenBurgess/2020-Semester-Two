package se325.assignment01.concert.service.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se325.assignment01.concert.common.dto.UserDTO;
import se325.assignment01.concert.service.domain.User;
import se325.assignment01.concert.service.util.ConcertUtils;

import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

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
            NewCookie cookie = makeCookie(user.getId());
            // Return cookie to user
            LOGGER.info(userDTO.getUsername() + " successfully signed in!");
            return Response.ok().cookie(cookie).build();
        } catch (NoResultException ex) {
            // User not found, so unauthorized
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private NewCookie makeCookie(long userId) {
        NewCookie newCookie = new NewCookie("auth", Long.toString(userId));
        LOGGER.info("Generated cookie: " + newCookie.getValue());
        return newCookie;
    }
}
