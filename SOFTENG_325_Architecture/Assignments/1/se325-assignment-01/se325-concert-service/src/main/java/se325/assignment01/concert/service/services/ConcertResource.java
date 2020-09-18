package se325.assignment01.concert.service.services;

import se325.assignment01.concert.common.dto.ConcertDTO;
import se325.assignment01.concert.service.domain.Concert;
import se325.assignment01.concert.service.mapper.ConcertMapper;
import se325.assignment01.concert.service.util.ConcertUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/concert-service")
public class ConcertResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("concerts/summaries")
    public List<ConcertDTO> getSummary() {
        List<Concert> concerts = ConcertUtils.getConcerts();
        List<ConcertDTO> concertDTOS = new ArrayList<>();
        for (Concert concert : concerts) {
            concertDTOS.add(ConcertMapper.toDto(concert));
        }
        return concertDTOS;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("concerts/{concertId}")
    public ConcertDTO getConcertById(@PathParam("concertId") long concertId) {
        Concert concert = ConcertUtils.getConcertById(concertId);
        return ConcertMapper.toDto(concert);
    }


}
