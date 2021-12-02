package com.freestack.evaluation.models;

import javax.persistence.*;

@Entity
@Table(name = "uber_driver")
public class UberDriver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fisrt_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    private boolean available;

    public UberDriver() {
    }

    public UberDriver(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.available = true;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
