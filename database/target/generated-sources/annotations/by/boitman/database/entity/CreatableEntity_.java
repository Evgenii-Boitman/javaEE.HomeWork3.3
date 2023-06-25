package by.boitman.database.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CreatableEntity.class)
public abstract class CreatableEntity_ {

	public static volatile SingularAttribute<CreatableEntity, LocalDate> created;

	public static final String CREATED = "created";

}

