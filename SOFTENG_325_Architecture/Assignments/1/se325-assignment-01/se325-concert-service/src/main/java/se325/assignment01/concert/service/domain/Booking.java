package se325.assignment01.concert.service.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Booking implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "DATE")})
    private ConcertDate concertDate;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany()
    private List<Seat> bookedSeats;

    public Booking() {
    }

    public Booking(ConcertDate concertDate, User user, List<Seat> bookedSeats) {
        this.concertDate = concertDate;
        this.user = user;
        this.bookedSeats = bookedSeats;
    }


    public ConcertDate getConcertDate() {
        return concertDate;
    }

    public long getId() {
        return id;
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

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", concertDate=" + concertDate +
                ", user=" + user +
                ", bookedSeats=" + bookedSeats +
                '}';
    }
}
