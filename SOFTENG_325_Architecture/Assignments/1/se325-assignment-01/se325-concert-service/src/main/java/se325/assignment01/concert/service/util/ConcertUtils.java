package se325.assignment01.concert.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se325.assignment01.concert.service.domain.Concert;
import se325.assignment01.concert.service.domain.ConcertDate;
import se325.assignment01.concert.service.domain.Seat;
import se325.assignment01.concert.service.services.ConcertApplication;
import se325.assignment01.concert.service.services.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ConcertUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(ConcertUtils.class);

    /**
     * This method will clear all seat and booking data from the database. Then, it will create all Seat objects for
     * all concerts and dates.
     */
    public static void initConcerts() {
        LOGGER.debug("initConcerts(): Creating the Application");

        EntityManager em = PersistenceManager.instance().createEntityManager();
        try {

            // Get all concerts
            em.getTransaction().begin();
            TypedQuery<Concert> query = em.createQuery("select c from Concert c", Concert.class);
            List<Concert> concerts = query.getResultList();
            LOGGER.debug(concerts.toString());

            // Get all dates for all concerts
            Set<LocalDateTime> allDates = new HashSet<>();
            for (Concert c : concerts) {
                Set<LocalDateTime> dates = c.getDates();
                //LOGGER.debug(dates.toString());
                allDates.addAll(dates);
            }
            em.getTransaction().commit();

            // Get all concert dates
            TypedQuery<ConcertDate> queryConcertDates = em.createQuery("select cd from ConcertDate cd", ConcertDate.class);
            List<ConcertDate> concertDates = queryConcertDates.getResultList();
            //LOGGER.debug(concertDates.toString());

            LOGGER.debug("initConcerts(): There are " + allDates.size() + " concert dates");

            // For each concert date, create the seats for that date and persist them.
            int seatCount = 0;
            for (ConcertDate concertDate : concertDates) {

                em.getTransaction().begin();
                Set<Seat> seatsForDate = TheatreLayout.createSeatsFor(concertDate.getDate());
                for (Seat s : seatsForDate) {
                    seatCount++;
                }
                concertDate.setSeats(seatsForDate);
                em.getTransaction().commit();

                // Ensures we aren't braking the EM with thousands of seat entities.
                //em.clear();
            }

            LOGGER.debug("initConcerts(): Created " + seatCount + " seats!");
        } finally {
            em.close();
        }
    }

    public static Concert getConcertById(long id) {
        EntityManager em = PersistenceManager.instance().createEntityManager();
        TypedQuery<Concert> query = em.createQuery("select c from Concert c", Concert.class);
        Optional<Concert> matchedConcert = query.getResultStream().filter(concert -> concert.getId().equals(id)).findFirst();

        return matchedConcert.orElse(null);
    }

    public static List<Concert> getConcerts() {
        EntityManager em = PersistenceManager.instance().createEntityManager();
        TypedQuery<Concert> query = em.createQuery("select c from Concert c", Concert.class);
        return query.getResultList();
    }
}
