package se325.assignment01.concert.service.mapper;

import se325.assignment01.concert.common.dto.PerformerDTO;
import se325.assignment01.concert.service.domain.Performer;

import java.util.List;

public class PerformerMapper {
    public static Performer toDomainModel(PerformerDTO concertDTO) {
        return null;
    }

    public static PerformerDTO toDto(Performer performer) {
        return new PerformerDTO(performer.getId(), performer.getName(), performer.getImageName(), performer.getGenre(), performer.getBlurb());
    }

}
