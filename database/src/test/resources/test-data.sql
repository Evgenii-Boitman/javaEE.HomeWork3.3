--
-- INSERT INTO user_account (user_id, account_id)
-- SELECT u.id, a.id
-- FROM users u
--          JOIN account a ON u.name = 'Petr' AND a.number_account = '123';

INSERT INTO users(name, surname, email, password, gender, role, contact, created_at)
VALUES ('Petr', 'Petrov', 'petrpetrov@gmail.com', 'petrpetrov', 'MALE', 'USER', '+375441000001', CURRENT_DATE),
       ('Ivan', 'Ivanov', 'ivanivanov@gmail.com', 'ivanivanov', 'MALE', 'USER', '+375441000002', CURRENT_DATE);

INSERT INTO account (number_account, account_balance, created_at)
VALUES (123, 1000.9, CURRENT_DATE),
       (321, 500.5, CURRENT_DATE);

UPDATE account SET user_id =(SELECT id FROM users WHERE name = 'Petr') WHERE account.name = 'Petr';
UPDATE account SET user_id =(SELECT id FROM users WHERE name = 'Ivan') WHERE account.name = 'Ivan';
