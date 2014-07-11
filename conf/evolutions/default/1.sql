# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table photo (
  id                        bigint not null,
  title                     varchar(255),
  data                      bytea,
  timestamp                 timestamp,
  constraint pk_photo primary key (id))
;

create sequence photo_seq;




# --- !Downs

drop table if exists photo cascade;

drop sequence if exists photo_seq;

