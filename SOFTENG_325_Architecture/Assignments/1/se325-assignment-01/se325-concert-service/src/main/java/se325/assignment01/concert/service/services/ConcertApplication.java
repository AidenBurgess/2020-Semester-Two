package se325.assignment01.concert.service.services;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import se325.assignment01.concert.service.domain.ConcertDate;
import se325.assignment01.concert.service.domain.Performer;
import se325.assignment01.concert.service.domain.Seat;
import se325.assignment01.concert.service.util.ConcertUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This is the Application class for the concert service. This class is complete - you should not need to modify it
 * (but will not be deducted marks if you decide you need to do so).
 */
@ApplicationPath("/services")
public class ConcertApplication extends Application {

    private Set<Object> singletons = new HashSet<>();
    private Set<Class<?>> classes = new HashSet<>();

    public ConcertApplication() {
        classes.add(TestResource.class);
        classes.add(ConcertResource.class);
        singletons.add(PersistenceManager.instance());

        ConcertUtils.initConcerts();

        EntityManager em = PersistenceManager.instance().createEntityManager();
        TypedQuery<Performer> queryConcertDates = em.createQuery("select p from Performer p ", Performer.class);
        List<Performer> performers = queryConcertDates.getResultList();

        System.out.println(performers.toString());

    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

}