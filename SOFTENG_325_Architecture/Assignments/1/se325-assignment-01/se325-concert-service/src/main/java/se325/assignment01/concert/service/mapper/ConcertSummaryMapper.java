package se325.assignment01.concert.service.mapper;

import se325.assignment01.concert.common.dto.ConcertSummaryDTO;
import se325.assignment01.concert.service.domain.Concert;

public class ConcertSummaryMapper {
    public static ConcertSummaryDTO toDTO(Concert concert) {
        return new ConcertSummaryDTO(concert.getId(), concert.getTitle(), concert.getImageName());
    }
}
