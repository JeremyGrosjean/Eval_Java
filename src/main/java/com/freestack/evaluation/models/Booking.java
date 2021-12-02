package com.freestack.evaluation.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_of_booking")
    private Date startOfBooking;
    @Column(name = "end_of_booking")
    private Date endOfBooking;
    private Integer evaluation;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UberUser uberUser;
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private UberDriver uberDriver;

    @Transient
    private Map<UberDriver, List<Integer>> evaluationMap;

    public Booking() {
    }

    public Long getId() {
        return id;
    }

    public Date getStartOfBooking() {
        return startOfBooking;
    }

    public void setStartOfBooking(Date startOfBooking) {
        this.startOfBooking = startOfBooking;
    }

    public Date getEndOfBooking() {
        return endOfBooking;
    }

    public void setEndOfBooking(Date endOfBooking) {
        this.endOfBooking = endOfBooking;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }

    public UberUser getUberUser() {
        return uberUser;
    }

    public void setUberUser(UberUser uberUser) {
        this.uberUser = uberUser;
    }

    public UberDriver getUberDriver() {
        return uberDriver;
    }

    public void setUberDriver(UberDriver uberDriver) {
        this.uberDriver = uberDriver;
    }

}
