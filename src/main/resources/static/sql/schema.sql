create table if not exists country
(
    id    varchar(2) primary key,
    value varchar(64) not null
);

create table if not exists user_table
(
    id             uuid default gen_random_uuid() primary key,
    username       varchar(100)     not null unique,
    password       varchar(60)      not null,
    age            int              not null check (age > 0),
    gender         gender_t         not null,
    country        varchar(2),
    marital_status marital_status_t not null,
    constraint country_fk foreign key (country) references country (id)
        on delete set null
        on update cascade
);

create table if not exists questionnaire
(
    id        uuid default gen_random_uuid() primary key,
    name      varchar(100) not null,
    question  varchar(300) not null,
    author_id uuid,
    multiple  boolean      not null,
    constraint author_fk foreign key (author_id) references user_table(id)
        on delete set null
        on update cascade
);

create table if not exists answered
(
    user_id             uuid not null,
    questionnaire_id uuid not null,
    constraint answered_complex_pk primary key (user_id, questionnaire_id),
    constraint user_fk foreign key (user_id) references user_table(id)
        on delete cascade
        on update cascade,
    constraint questionnaire_fk foreign key (questionnaire_id) references questionnaire(id)
        on delete cascade
        on update cascade
);

create table if not exists option
(
    id               uuid default gen_random_uuid() primary key,
    text             varchar(75) not null,
    questionnaire_id uuid        not null,
    constraint questionnaire_fk foreign key (questionnaire_id) references questionnaire (id)
        on delete cascade
        on update cascade
);

create table if not exists choice
(
    id             uuid default gen_random_uuid() primary key,
    option_id      uuid             not null,
    age            int              not null check (age > 0),
    gender         gender_t         not null,
    country        varchar(2),
    marital_status marital_status_t not null,
    constraint option_fk foreign key (option_id) references option (id)
        on delete cascade
        on update cascade,
    constraint country_fk foreign key (country) references country (id)
        on delete set null
        on update cascade
);