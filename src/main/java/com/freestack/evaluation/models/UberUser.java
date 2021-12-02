package com.freestack.evaluation.models;

import javax.persistence.*;

@Entity
@Table(name = "uber_user")
public class UberUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    public UberUser() {
    }

    public UberUser(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
