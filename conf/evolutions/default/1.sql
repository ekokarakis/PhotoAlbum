# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table comment (
  id                        bigint not null,
  photo_id                  bigint,
  text                      varchar(255),
  timestamp                 timestamp,
  constraint pk_comment primary key (id))
;

create table photo (
  id                        bigint not null,
  title                     varchar(255),
  timestamp                 timestamp,
  constraint pk_photo primary key (id))
;

create table photo_data (
  id                        bigint not null,
  photo_id                  bigint,
  data                      bytea,
  encoding                  varchar(255),
  constraint pk_photo_data primary key (id))
;

create sequence comment_seq;

create sequence photo_seq;

create sequence photo_data_seq;

alter table comment add constraint fk_comment_photo_1 foreign key (photo_id) references photo (id);
create index ix_comment_photo_1 on comment (photo_id);
alter table photo_data add constraint fk_photo_data_photo_2 foreign key (photo_id) references photo (id);
create index ix_photo_data_photo_2 on photo_data (photo_id);



# --- !Downs

drop table if exists comment cascade;

drop table if exists photo cascade;

drop table if exists photo_data cascade;

drop sequence if exists comment_seq;

drop sequence if exists photo_seq;

drop sequence if exists photo_data_seq;

