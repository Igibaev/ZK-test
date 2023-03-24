create table address
(
    id        bigint auto_increment
        primary key,
    apartment varchar(255) null,
    city      varchar(255) not null,
    house     varchar(255) not null,
    mailIndex int          null,
    street    varchar(255) not null
)
    engine = MyISAM;

create table branch
(
    id         bigint auto_increment
        primary key,
    name       varchar(255) not null,
    address_id bigint       not null
)
    engine = MyISAM;

create index branch_address_id_fk
    on branch (address_id);

create table company
(
    id         bigint auto_increment
        primary key,
    legalForm  varchar(255) null,
    name       varchar(255) not null,
    address_id bigint       not null
)
    engine = MyISAM;

create index company_address_id_fk
    on company (address_id);

create table company_branch
(
    Company_id    bigint not null,
    branchList_id bigint not null,
    primary key (Company_id, branchList_id)
)
    engine = MyISAM;

create index company_branch_branch_id_fk
    on company_branch (branchList_id);

