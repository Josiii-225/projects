create database bank
use bank

create table Customer(
	CustomerID int not null,
    CustomerName varchar(35),
	CustomerPassword varchar(35),
    CustomerAge int
);

alter table customer
add constraint pk1 primary key(CustomerID);

create table Account(
	CustomerID int not null,
    AccountType varchar(35),
    BalanceAmount numeric
);

alter table Account
add constraint fk1 foreign key (CustomerID) references customer(customerID)CustomerNameCustomerID

insert into Account value(1,'Saving',50000);
insert into Customer value(1,'Josi','josi123',20);

