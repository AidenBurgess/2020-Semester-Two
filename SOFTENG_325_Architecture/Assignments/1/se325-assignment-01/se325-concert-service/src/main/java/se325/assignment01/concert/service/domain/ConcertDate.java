package se325.assignment01.concert.service.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

//@Entity
//@Table(name="CONCERT_DATES")
public class ConcertDate implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Concert concert;
    @Id
    private LocalDateTime date;

}
