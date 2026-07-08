Create table Product (
    id serial unique not null,
    name char(30) not null,
    cost integer check(cost >= 0)
);

Insert into Product (name, cost) values ('банан', 10);