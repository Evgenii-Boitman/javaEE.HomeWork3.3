package by.boitman.database.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CardEntity.class)
public abstract class CardEntity_ extends by.boitman.database.entity.CreatableEntity_ {

	public static volatile SingularAttribute<CardEntity, String> ownerName;
	public static volatile SingularAttribute<CardEntity, Float> balance;
	public static volatile SingularAttribute<CardEntity, String> ownerSurname;
	public static volatile SingularAttribute<CardEntity, Long> id;
	public static volatile SingularAttribute<CardEntity, AccountEntity> accounts;
	public static volatile SingularAttribute<CardEntity, Long> cardNumber;

	public static final String OWNER_NAME = "ownerName";
	public static final String BALANCE = "balance";
	public static final String OWNER_SURNAME = "ownerSurname";
	public static final String ID = "id";
	public static final String ACCOUNTS = "accounts";
	public static final String CARD_NUMBER = "cardNumber";

}

