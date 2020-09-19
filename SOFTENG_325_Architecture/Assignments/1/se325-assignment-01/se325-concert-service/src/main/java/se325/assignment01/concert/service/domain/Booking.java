package se325.assignment01.concert.service.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Booking implements Serializable {
    @Id
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "CONCERT_ID"), @JoinColumn(name = "DATE")})
    private ConcertDate concertDate;
    @Id
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @OneToMany
    private List<Seat> bookedSeats;

    public ConcertDate getConcertDate() {
        return concertDate;
    }

    public User getUser() {
        return user;
    }

    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(concertDate, booking.concertDate) &&
                Objects.equals(user, booking.user) &&
                Objects.equals(bookedSeats, booking.bookedSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(concertDate, user, bookedSeats);
    }
}
