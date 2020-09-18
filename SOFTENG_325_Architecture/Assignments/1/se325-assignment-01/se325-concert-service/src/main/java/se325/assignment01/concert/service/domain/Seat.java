package se325.assignment01.concert.service.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Embeddable
public class Seat implements Serializable {
    private String label;
    private boolean isBooked;
    //private LocalDateTime date;
    private BigDecimal price;

    public Seat() {
    }

    public Seat(String label, boolean isBooked, LocalDateTime date, BigDecimal price) {
        this.label = label;
        this.isBooked = isBooked;
        //this.date = date;
        this.price = price;
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
