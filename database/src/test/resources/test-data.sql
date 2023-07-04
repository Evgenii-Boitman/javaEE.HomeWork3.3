INSERT INTO users(name, surname, email, password, gender, role, contact)
VALUES ('Petr', 'Petrov', 'petrpetrov@gmail.com', 'petrpetrov', 'MALE', 'USER', '+375441000001');

INSERT INTO account (id, user_id, name, surname, gender, number_account, account_balance)
VALUES (1, 1, 'Petr', 'Petrov', 'MALE', 123, 1000.9),
       (2, 2, 'Ivan', 'Ivanov', 'MALE', 321, 500.5);

INSERT INTO user_account (user_id, account_id)
SELECT u.id, a.id
FROM users u
         JOIN account a ON u.name = 'Petr' AND a.number_account = '123';
