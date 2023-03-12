use books;

SET FOREIGN_KEY_CHECKS=0;

insert into Books (isbn, book_name, book_description, price, author_id, genre, publisher, year_published, copies_sold) values ('0439708184', 'HARRY POTTER', 'Wizarding World of Harry Potter...', 6.99, 1, 'FANTASY', 'Amazon', 2002, 20000000);
insert into Books (isbn, book_name, book_description, price, author_id, genre, publisher, year_published, copies_sold) values ('2564676723', 'Murder On The Orient Express', 'Murder Mystery...', 10.99, 2, 'FANTASY', 'Amazon', 2011, 500000);
insert into Books (isbn, book_name, book_description, price, author_id, genre, publisher, year_published, copies_sold) values ('9780743273', 'The Great Gatsby', 'The story of the mysteriously wealthy Jay Gatsby...', 6.74, 3, 'FANTASY', 'Amazon', 2002, 750000);
insert into Books (isbn, book_name, book_description, price, author_id, genre, publisher, year_published, copies_sold) values ('0393089053', 'The Odyssey', 'The first great adventure story in the Western canon...', 21.99, 4, 'HISTORY', 'Amazon', 2017, 62500);
insert into Books (isbn, book_name, book_description, price, author_id, genre, publisher, year_published, copies_sold) values ('0234245431', 'To Kill a Mockingbird', 'The unforgettable novel of a childhood in a sleepy Southern town...', 11.01, 5, 'FANTASY', 'Amazon', 1988, 3500000);