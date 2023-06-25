package by.boitman.database.entity;

import by.boitman.database.entity.enam.Gender;
import by.boitman.database.entity.enam.Role;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserEntity.class)
public abstract class UserEntity_ extends by.boitman.database.entity.CreatableEntity_ {

	public static volatile SingularAttribute<UserEntity, String> password;
	public static volatile SingularAttribute<UserEntity, Role> role;
	public static volatile SingularAttribute<UserEntity, Gender> gender;
	public static volatile SingularAttribute<UserEntity, String> surname;
	public static volatile SingularAttribute<UserEntity, String> contact;
	public static volatile SingularAttribute<UserEntity, String> name;
	public static volatile SingularAttribute<UserEntity, Long> id;
	public static volatile ListAttribute<UserEntity, AccountEntity> accounts;
	public static volatile SingularAttribute<UserEntity, String> email;

	public static final String PASSWORD = "password";
	public static final String ROLE = "role";
	public static final String GENDER = "gender";
	public static final String SURNAME = "surname";
	public static final String CONTACT = "contact";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String ACCOUNTS = "accounts";
	public static final String EMAIL = "email";

}

