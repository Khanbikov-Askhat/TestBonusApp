drop table if exists payments cascade;
drop table if exists accounts cascade;
drop table if exists bonus cascade;



create table if not exists accounts
(
    account_id INTEGER not null primary key auto_increment,
    account_amount DOUBLE PRECISION
);

create table if not exists bonus
(
    bonus_id INTEGER not null primary key auto_increment,
    bonus_amount DOUBLE PRECISION,
    account_id INTEGER NOT NULL,
    foreign key (account_id) references accounts(account_id) ON DELETE CASCADE
);

create table if not exists payments
(
    payment_id INTEGER not null primary key auto_increment,
    operation_amount float,
    operation_type varchar(255),
    account_id INTEGER NOT NULL,
    bonus_id INTEGER NOT NULL,
    foreign key (account_id) references accounts(account_id) ON DELETE CASCADE,
    foreign key (bonus_id) references bonus(bonus_id) ON DELETE CASCADE
);

