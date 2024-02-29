-- create member record
INSERT INTO library_member (first_name, last_name, email_address, phone_number, membership_level, address_id)
VALUES ('Michael', 'John', 'john.mich@gmail.com', '1234567810', 'Gold', 2000004);

-- find the member by name and mobile_number
select * from library_member where first_name = "Eva" and phone_number = 9990001111;

-- display all the members
select * from library_member;

-- list all the books a member has checked out
select b.* from book b 
join book_isbn bi on bi.book_id = b.book_id
join checkout c on c.isbn = bi.isbn
where c.member_id = 1000001 and c.is_returned = false;

-- checkout a book for a given member and given book
INSERT INTO checkout (isbn, member_id, checkout_date, due_date)
values (1000000000031, 1000001, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '45' DAY);

-- list all checkouts
select * from checkout;

-- list avaialble books and quantity that can be checked out
select * from book where quantity > 0;

-- list all the checkouts that are due in two days
select * from checkout 
where due_date BETWEEN CURRENT_TIMESTAMP AND CURRENT_TIMESTAMP + INTERVAL '2' DAY;

-- list all the checkouts that are over due 
SELECT * FROM checkout
WHERE due_date < CURRENT_TIMESTAMP AND is_returned = false;

-- list all the books that are checked out today
select b.* from book b 
join book_isbn bi on bi.book_id = b.book_id
join checkout c on c.isbn = bi.isbn
WHERE DATE(c.checkout_date) = CURRENT_DATE;

-- create a book record
INSERT INTO book (title, author_name, year_published, quantity)
VALUES ('Echoes of Eternity', 'Jane Archer', 1965, 9);

use lms;
-- display all isbns and their checkout status along with book information (title, author etc,.)
select b.*, bi.isbn, c.is_returned from book b
join book_isbn bi on b.book_id = bi.book_id
join checkout c on c.isbn = bi.isbn;