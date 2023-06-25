INSERT INTO users(name)
VALUES ('Petr');

INSERT INTO account (name, surname, gender, number_account, account_balance, created_at)
VALUES ('Petr', 'Petrov', 'MALE', 1, 100.5, '2023-06-18'),
       ('Nikolai', 'Nikolaev', 'MALE',2, 1000.9, '2023-06-19'),
       ('Ivan', 'Ivanov', 'MALE',3, 5000, '2023-06-20');


INSERT INTO user_account (user_id, account_id)
SELECT u.id, a.id
FROM users u
         JOIN account a ON u.name = 'Petr' AND a.number_account = 123;



