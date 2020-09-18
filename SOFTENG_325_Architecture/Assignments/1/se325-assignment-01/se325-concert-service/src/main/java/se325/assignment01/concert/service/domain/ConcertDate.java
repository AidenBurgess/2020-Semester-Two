package se325.assignment01.concert.service.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CONCERT_DATES")
public class ConcertDate implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Concert concert;
    @Id
    private LocalDateTime date;

    //@OneToMany(mappedBy = "concertDate")
    @ElementCollection
    @CollectionTable(name = "CONCERT_DATE_SEATS", joinColumns = {@JoinColumn(name = "CONCERT_ID"), @JoinColumn(name = "DATE")})
    @Column(name = "SEATS")
    private Set<Seat> seats = new HashSet<>();

    public LocalDateTime getDate() {
        return date;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }
}
