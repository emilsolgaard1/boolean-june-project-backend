package com.booleanuk.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "developers")
public class Developer {
    //region // FIELDS //
    @Id
    private int id;
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    @MapsId
    private User user;
    //endregion

    //region // PROPERTIES //
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    //endregion

    public Developer() {

    }
    public Developer(int id) {
        this.id = id;
    }
    public Developer(User user) {
        this.user = user;
    }
}
