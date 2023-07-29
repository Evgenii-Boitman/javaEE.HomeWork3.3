package by.boitman.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
public abstract class CreatableEntity{

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDate created;
}
