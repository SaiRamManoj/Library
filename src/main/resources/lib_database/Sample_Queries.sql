-- create schema lms;

use lms;
-- To drop the tablespaces
drop table checkout;
drop table library_member;
drop table book_isbn;
drop table book;
drop table address;

use lms;
-- To display all the rows of Tables created
select * from checkout;
select * from library_member;
select * from book_isbn;
select * from book;
select * from address;

delete from book where book_id=3000018;

-- to check the count of books
select book_id, count(book_id) from book_isbn group by book_id;

-- Give me all the book titles
select title from book;

-- Give me all the titles which are published in 1947, more than 2 in quantity
select title from book where year_published > 1947 and quantity >2;

-- Get all the gmail users in california
select email_address from library_member l join address a 
on l.address_id = a.address_id
where l.email_address like "%@gmail.com" and a.city = "california";

select email_address from library_member lm join address a 
on lm.address_id = a.address_id
where lm.email_address like "%@email.com" and a.city = "Townton";

