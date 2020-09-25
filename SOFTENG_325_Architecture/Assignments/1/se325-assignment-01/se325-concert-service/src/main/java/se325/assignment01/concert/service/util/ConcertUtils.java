package se325.assignment01.concert.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se325.assignment01.concert.common.dto.UserDTO;
import se325.assignment01.concert.service.domain.*;
import se325.assignment01.concert.service.services.PersistenceManager;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
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

            // Get all dates for all concerts
            Set<LocalDateTime> allDates = new HashSet<>();
            for (Concert c : concerts) {
                Set<LocalDateTime> dates = c.getDates();
                allDates.addAll(dates);
            }
            em.getTransaction().commit();

            LOGGER.debug("initConcerts(): There are " + allDates.size() + " concert dates");

            // For each concert date, create the seats for that date and persist them.
            int seatCount = 0;
            for (LocalDateTime date : allDates) {

                em.getTransaction().begin();
                Set<Seat> seatsForDate = TheatreLayout.createSeatsFor(date);
                for (Seat s : seatsForDate) {
                    em.persist(s);
                    seatCount++;
                }
                em.getTransaction().commit();

                // Ensures we aren't braking the EM with thousands of seat entities.
                em.clear();
            }

            LOGGER.debug("initConcerts(): Created " + seatCount + " seats!");
        } finally {
            em.close();
        }
    }

    public static Concert getConcertById(long id) {
        EntityManager em = PersistenceManager.instance().createEntityManager();
        return em.find(Concert.class, id);
    }

    public static List<Concert> getConcerts() {
        EntityManager em = PersistenceManager.instance().createEntityManager();
        TypedQuery<Concert> query = em.createQuery("select c from Concert c", Concert.class);
        return query.getResultList();
    }

    public static Performer getPerformerById(long id) throws NoResultException {
        EntityManager em = PersistenceManager.instance().createEntityManager();
        TypedQuery<Performer> query = em.createQuery("select p from Performer p where p.id = :id", Performer.class)
                .setParameter("id", id);
        return query.getSingleResult();
    }

    public static List<Performer> getPerformers() {
        EntityManager em = PersistenceManager.instance().createEntityManager();
        TypedQuery<Performer> query = em.createQuery("select c from Performer c", Performer.class);
        return query.getResultList();
    }

    public static List<Seat> getSeatsForDay(LocalDateTime date) {
        EntityManager em = PersistenceManager.instance().createEntityManager();
        TypedQuery<Seat> query = em.createQuery("select s from Seat s where s.date = :date", Seat.class)
                .setParameter("date", date);
        return query.getResultList();
    }

    public static User getUserFromLogin(String username, String password) throws NoResultException {
        EntityManager em = PersistenceManager.instance().createEntityManager();
        TypedQuery<User> query = em.createQuery("select u from User u where u.username = :username and u.password = :password", User.class)
                .setParameter("username", username)
                .setParameter("password", password);
        return query.getSingleResult();
    }

    public static User getUserById(String userId) {
        EntityManager em = PersistenceManager.instance().createEntityManager();
        return em.find(User.class, Long.parseLong(userId));
    }

    public static List<Booking> getBookings(String userId) {
        EntityManager em = PersistenceManager.instance().createEntityManager();
        TypedQuery<Booking> query = em.createQuery("select b from Booking b where b.user.id = :id", Booking.class)
                .setParameter("id", Long.parseLong(userId));
        return query.getResultList();
    }

    public static Booking getBookingById(String bookingId) {
        EntityManager em = PersistenceManager.instance().createEntityManager();
        return em.find(Booking.class, Long.parseLong(bookingId));
    }

    public static ConcertDate getConcertDateByDate(LocalDateTime date) {
        EntityManager em = PersistenceManager.instance().createEntityManager();
        return em.find(ConcertDate.class, date);
    }

    public static List<Seat> getSeatsFromLabels(List<String> seatLabels, LocalDateTime date) {
        Set<String> seatLabelsSet = new HashSet<>(seatLabels);
        EntityManager em = PersistenceManager.instance().createEntityManager();
        TypedQuery<Seat> query = em.createQuery("select s from Seat s where s.date = :date and s.label in :seatLabels", Seat.class)
                .setParameter("date", date)
                .setParameter("seatLabels", seatLabelsSet);
        return query.getResultList();
    }

    public static void persistBooking(Booking booking) {
        EntityManager em = PersistenceManager.instance().createEntityManager();
        System.out.println("before: " + booking);
        try {
            em.getTransaction().begin();
            for (Seat seat : booking.getBookedSeats()) {
                if (seat.isBooked()) {
                    em.getTransaction().setRollbackOnly();
                    throw new EntityExistsException();
                }
                seat.setBooked(true);
                em.merge(seat);
            }
            em.persist(booking);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        System.out.println("after: " + booking);


    }
}
