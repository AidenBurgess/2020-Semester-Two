package se325.assignment01.concert.service.services;

import se325.assignment01.concert.common.dto.PerformerDTO;
import se325.assignment01.concert.service.domain.Performer;
import se325.assignment01.concert.service.mapper.PerformerMapper;
import se325.assignment01.concert.service.util.ConcertUtils;

import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/concert-service/performers")
public class PerformerResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PerformerDTO> getAllPerformers() {
        List<Performer> performers = ConcertUtils.getPerformers();
        // Convert all performers to DTO
        List<PerformerDTO> performerDTOS = new ArrayList<>();
        for (Performer performer : performers) {
            performerDTOS.add(PerformerMapper.toDTO(performer));
        }

        if (performerDTOS.size() == 0) throw new NotFoundException("Could not find any performers");

        return performerDTOS;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{performerId}")
    public PerformerDTO getConcertById(@PathParam("performerId") long performerId) {
        Performer performer = ConcertUtils.getPerformerById(performerId);

        if (performer == null) throw new NotFoundException("Could not find performer with id " + performerId);

        return PerformerMapper.toDTO(performer);
    }
}