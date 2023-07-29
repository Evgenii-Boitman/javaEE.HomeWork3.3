DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS card;
DROP TABLE IF EXISTS contact;
DROP TABLE IF EXISTS account_card;
DROP TABLE IF EXISTS user_account;

CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(50)        NOT NULL,
    surname    VARCHAR(50)        NOT NULL,
    email      VARCHAR(50) UNIQUE NOT NULL,
    password   VARCHAR(256)       NOT NULL,
    gender     VARCHAR(10)        NOT NULL,
    role       VARCHAR(10)        NOT NULL,
    contact    VARCHAR(30)        NULL,
    created_at DATE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE account
(
    id              BIGSERIAL PRIMARY KEY,
    user_id         BIGINT REFERENCES users (id),
    name            VARCHAR(50)                  NOT NULL,
    surname         VARCHAR(50)                  NOT NULL,
    gender          VARCHAR(10)                  NOT NULL,
    number_account  BIGSERIAL,
    account_balance BIGINT                       NULL,
    created_at      DATE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE card
(
    id           BIGSERIAL PRIMARY KEY,
    account_id   BIGINT REFERENCES account (id) NOT NULL,
    name         VARCHAR(50)                    NOT NULL,
    surname      VARCHAR(50)                    NOT NULL,
    card_number  BIGSERIAL,
    card_balance BIGINT                         NULL,
    created_at   DATE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE contact
(
    user_id BIGINT REFERENCES users (id),
    tel     VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE account_card
(
    account_id BIGINT REFERENCES account (id),
    card_id    BIGINT REFERENCES card (id)
);

CREATE TABLE user_account
(
    account_id BIGINT REFERENCES account (id),
    user_id    BIGINT REFERENCES users (id)
);