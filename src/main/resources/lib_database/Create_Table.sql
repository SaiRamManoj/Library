use lms;

-- create the address table
CREATE TABLE address (
	address_id INT AUTO_INCREMENT primary key,
    line1 VARCHAR(30),
    line2 VARCHAR(30),
    city VARCHAR(20),
    state CHAR(2),
    zip INT
);

-- create the book table
create table book (
	book_id int AUTO_INCREMENT primary key,
	title varchar(45),
	author_name varchar(50),
	year_published int,
	quantity int
);

-- create the book_isbn table
-- ON DELETE CASCADE to the foreign key constraint if you want the associated ISBNs 
-- automatically deleted when a book is deleted.
CREATE TABLE book_isbn(
	isbn bigint AUTO_INCREMENT primary key,
    book_id int,
    FOREIGN KEY (book_id) REFERENCES book(book_id) ON DELETE CASCADE
);

-- create the library_member table
CREATE TABLE library_member (
	member_id INT AUTO_INCREMENT PRIMARY KEY, 
	first_name VARCHAR(25),
	last_name VARCHAR(25),
	email_address VARCHAR(35),
	phone_number BIGINT,
	membership_level VARCHAR(10),
	address_id INT,
    foreign key (address_id) references address(address_id)
);

-- create the checkout table
CREATE TABLE checkout (
	id INT AUTO_INCREMENT PRIMARY KEY,
	isbn BIGINT,
	member_id INT,
	checkout_date DATETIME,
	due_date DATETIME,
	is_returned BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (member_id) REFERENCES library_member(member_id),
    FOREIGN KEY (isbn) REFERENCES book_isbn(isbn)
);