package by.boitman.database.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

public class ContactEntity {
    private String tel;
    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
