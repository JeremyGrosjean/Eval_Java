package com.freestack.evaluation.api;

import com.freestack.evaluation.models.Booking;
import com.freestack.evaluation.models.UberDriver;
import com.freestack.evaluation.models.UberUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class UberApi {

    static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("myPostGreSqlEntityManager");

    public static void enrollUser(UberUser uberUser){

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(uberUser);
        em.getTransaction().commit();
        em.close();
    }

    public static void enrollDriver(UberDriver uberDriver) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(uberDriver);
        em.getTransaction().commit();
        em.close();
    }


    public static Booking bookOneDriver(UberUser uberUser) {

        EntityManager em = emf.createEntityManager();
            List<UberDriver> uberDriverList = (List<UberDriver>) em.createQuery("SELECT u From UberDriver u WHERE u.available = true").setMaxResults(1).getResultList();
            if (!uberDriverList.isEmpty()) {
                Booking booking = new Booking();
                em.getTransaction().begin();
                UberDriver uberDriver = uberDriverList.get(0);
                booking.setUberDriver(uberDriver);
                booking.setUberUser(uberUser);
                booking.setStartOfBooking(new Date());
                uberDriver.setAvailable(false);
                em.persist(uberDriver);
                em.persist(booking);
                em.getTransaction().commit();
                em.close();
                return booking;
            } else return null;
    }

    public static void finishBooking(Booking booking) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
            Query query = em.createQuery("SELECT b FROM Booking b WHERE b= :booking");
            query.setParameter("booking", booking);
            Booking result = (Booking) query.getSingleResult();
            result.setEndOfBooking(new Date());
            em.persist(result);
            UberDriver uberDriver = result.getUberDriver();
            uberDriver.setAvailable(true);
            em.persist(uberDriver);
        em.getTransaction().commit();
        em.close();
    }

    public static void evaluateDriver(Booking booking, int evaluationOfTheUser) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
            Query query = em.createQuery("SELECT b FROM Booking b WHERE b= :booking");
            query.setParameter("booking", booking);
            Booking result = (Booking) query.getSingleResult();

            result.setEvaluation(evaluationOfTheUser);
            em.persist(result);
        em.getTransaction().commit();
        em.close();
    }

    public static List<Booking> listDriverBookings(UberDriver uberDriver) {
        EntityManager em = emf.createEntityManager();
        List<Booking> driverBookings = (List<Booking>) em.createQuery("SELECT b FROM Booking b where b.uberDriver = :uberDriver")
            .setParameter("uberDriver", uberDriver)
            .getResultList();
        em.close();
        return driverBookings;
    }

    public static List<Booking> listUnfinishedBookings() {
        EntityManager em = emf.createEntityManager();
        return (List<Booking>) em.createQuery("SELECT b FROM Booking b WHERE b.endOfBooking IS NULL").getResultList();
    }

    public static float meanScore(UberDriver uberDriver) {
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT AVG(b.evaluation) FROM Booking b WHERE b.uberDriver= :uberD");
        query.setParameter("uberD", uberDriver);
        Double result = (Double) query.getSingleResult();
        float newResult = result.floatValue();
        return newResult;
    }
}
