package se325.assignment01.concert.service.services;

import se325.assignment01.concert.common.dto.ConcertDTO;
import se325.assignment01.concert.common.dto.ConcertSummaryDTO;
import se325.assignment01.concert.service.domain.Concert;
import se325.assignment01.concert.service.mapper.ConcertMapper;
import se325.assignment01.concert.service.mapper.ConcertSummaryMapper;
import se325.assignment01.concert.service.util.ConcertUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/concert-service/concerts")
public class ConcertResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ConcertDTO> getAllConcerts() {
        List<Concert> concerts = ConcertUtils.getConcerts();
        List<ConcertDTO> concertDTOS = new ArrayList<>();
        for (Concert concert : concerts) {
            concertDTOS.add(ConcertMapper.toDTO(concert));
        }
        if (concertDTOS.size() == 0) {
            throw new NotFoundException("Could not find any concerts");
        }
        return concertDTOS;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("summaries")
    public List<ConcertSummaryDTO> getSummary() {
        List<Concert> concerts = ConcertUtils.getConcerts();
        List<ConcertSummaryDTO> concertSummaryDTOS = new ArrayList<>();
        for (Concert concert : concerts) {
            concertSummaryDTOS.add(ConcertSummaryMapper.toDTO(concert));
        }
        if (concertSummaryDTOS.size() == 0) {
            throw new NotFoundException("Could not find any concerts");
        }
        return concertSummaryDTOS;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{concertId}")
    public ConcertDTO getConcertById(@PathParam("concertId") long concertId) {
        Concert concert = ConcertUtils.getConcertById(concertId);
        if (concert == null) {
            throw new NotFoundException("Could not find concert with id " + concertId);
        }

        return ConcertMapper.toDTO(concert);
    }

}
