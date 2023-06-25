package by.boitman.database.entity;

import by.boitman.database.entity.enam.Gender;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AccountEntity.class)
public abstract class AccountEntity_ extends by.boitman.database.entity.CreatableEntity_ {

	public static volatile SingularAttribute<AccountEntity, String> ownerNameAccount;
	public static volatile SingularAttribute<AccountEntity, String> ownerSurnameAccount;
	public static volatile ListAttribute<AccountEntity, CardEntity> cards;
	public static volatile SingularAttribute<AccountEntity, Gender> gender;
	public static volatile SingularAttribute<AccountEntity, Long> numberAccount;
	public static volatile SingularAttribute<AccountEntity, Long> id;
	public static volatile SingularAttribute<AccountEntity, Float> accountBalance;
	public static volatile SingularAttribute<AccountEntity, UserEntity> users;

	public static final String OWNER_NAME_ACCOUNT = "ownerNameAccount";
	public static final String OWNER_SURNAME_ACCOUNT = "ownerSurnameAccount";
	public static final String CARDS = "cards";
	public static final String GENDER = "gender";
	public static final String NUMBER_ACCOUNT = "numberAccount";
	public static final String ID = "id";
	public static final String ACCOUNT_BALANCE = "accountBalance";
	public static final String USERS = "users";

}

