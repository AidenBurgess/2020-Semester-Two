package se325.assignment01.concert.service.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Seat implements Serializable {
    @Id
    private String label;
    @Id
    private LocalDateTime date;
    private boolean isBooked;
    private BigDecimal price;

    @Version
    private int version;

    public Seat() {
    }

    public Seat(String label, boolean isBooked, LocalDateTime date, BigDecimal price) {
        this.label = label;
        this.isBooked = isBooked;
        this.date = date;
        this.price = price;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return isBooked == seat.isBooked &&
                Objects.equals(label, seat.label) &&
                Objects.equals(date, seat.date) &&
                Objects.equals(price, seat.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, date, isBooked, price);
    }

    @Override
    public String toString() {
        return "Seat{" +
                "label='" + label + '\'' +
                ", isBooked=" + isBooked +
                ", price=" + price +
                '}';
    }
}
