DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS card;
DROP TABLE IF EXISTS contact;
DROP TABLE IF EXISTS account_card_owner;

CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(50)        NOT NULL,
    surname    VARCHAR(50)        NOT NULL,
    email      VARCHAR(50) UNIQUE NOT NULL,
    password   VARCHAR(100)       NOT NULL,
    gender     VARCHAR(10)        NOT NULL,
    role       VARCHAR(10)        NOT NULL,
    contact    VARCHAR(30)        NULL,
    created_at TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE account
(
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(50) NOT NULL,
    surname         VARCHAR(50) NOT NULL,
    number_account  BIGSERIAL   NOT NULL,
    account_balance NUMERIC
);

CREATE TABLE card
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(50) NOT NULL,
    surname      VARCHAR(50) NOT NULL,
    card_number  BIGSERIAL   NOT NULL,
    card_balance NUMERIC
);

CREATE TABLE contact
(
    user_id BIGINT REFERENCES users (id),
    tel     VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE account_card_owner
(
    account_id BIGINT REFERENCES account (id),
    card_id    BIGINT REFERENCES card (id),
    user_id    BIGINT REFERENCES users (id)
);