package se325.assignment01.concert.service.mapper;

import se325.assignment01.concert.common.dto.ConcertDTO;
import se325.assignment01.concert.common.dto.PerformerDTO;
import se325.assignment01.concert.service.domain.Concert;

import java.util.ArrayList;
import java.util.List;

public class ConcertMapper {
    public static Concert toDomainModel(ConcertDTO concertDTO) {
        return null;
    }

    public static ConcertDTO toDTO(Concert concert) {
        ConcertDTO concertDTO = new ConcertDTO(concert.getId(), concert.getTitle(), concert.getImageName(), concert.getBlurb());
        concertDTO.setDates(new ArrayList<>(concert.getDates()));
        // Convert set of performers to list of performerDtos
        List<PerformerDTO> performersDTO = new ArrayList<>();
        System.out.println(concert.getPerformers());
        concert.getPerformers().forEach(performer -> {
            performersDTO.add(PerformerMapper.toDTO(performer));
        });
        concertDTO.setPerformers(performersDTO);
        return concertDTO;
    }
}
