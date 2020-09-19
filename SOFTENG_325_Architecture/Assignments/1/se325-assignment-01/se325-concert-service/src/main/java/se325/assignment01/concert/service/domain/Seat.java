package se325.assignment01.concert.service.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Seat implements Serializable {
    @Id
    private String label;
    @Id
    private LocalDateTime date;
    private boolean isBooked;
    private BigDecimal price;

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

    @Override
    public String toString() {
        return "Seat{" +
                "label='" + label + '\'' +
                ", isBooked=" + isBooked +
                ", price=" + price +
                '}';
    }
}
