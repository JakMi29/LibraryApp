INSERT INTO orders (book_id, received_date, return_date, email)
VALUES
  (1, '2023-12-14 12:00:00', '2023-12-21 12:00:00', 'user1@gmail.com'),
  (2, '2023-12-15 09:30:00', '2023-12-22 09:30:00', 'user1@gmail.com'),
  (3, '2023-12-16 15:45:00', '2023-12-23 15:45:00', 'user1@gmail.com'),
  (3, '2023-12-16 15:45:00', '2023-12-10 15:45:00', 'email1@gmail.com'),
  (4, '2023-12-17 18:00:00', NULL, 'user1@gmail.com');