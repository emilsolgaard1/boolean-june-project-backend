package com.booleanuk.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "product_owners")
public class ProductOwner {
    //region // FIELDS //
    @Id
    private int id;
    @OneToOne
    @JoinColumn(name = "user_id")
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

    public ProductOwner() {

    }
    public ProductOwner(int id) {
        this.id = id;
    }
    public ProductOwner(User user) {
        this.user = user;
    }
}
