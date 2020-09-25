package se325.assignment01.concert.service.domain;

import javax.ws.rs.container.AsyncResponse;
import java.time.LocalDateTime;

public class ConcertInfoSubscription {
    private AsyncResponse subscription;
    private LocalDateTime date;
    private int percentageBooked;

    public ConcertInfoSubscription(AsyncResponse subscription, LocalDateTime date, int percentageBooked) {
        this.subscription = subscription;
        this.date = date;
        this.percentageBooked = percentageBooked;
    }

    public AsyncResponse getSubscription() {
        return subscription;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getPercentageBooked() {
        return percentageBooked;
    }
}
