create table book (
  book_code varchar2(15) primary key,
  book_title varchar2(50) not null,
  book_author varchar2(50) not null,
  book_press varchar2(50) not null,
  book_desc varchar2(500),
  book_price int
);