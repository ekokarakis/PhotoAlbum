# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table photo (
  id                        bigint not null,
  title                     varchar(255),
  timestamp                 timestamp,
  constraint pk_photo primary key (id))
;

create table photo_data (
  photo_id                  bigint,
  data                      bytea,
  encoding                  varchar(255))
;

create sequence photo_seq;

alter table photo_data add constraint fk_photo_data_photo_1 foreign key (photo_id) references photo (id);
create index ix_photo_data_photo_1 on photo_data (photo_id);



# --- !Downs

drop table if exists photo cascade;

drop table if exists photo_data cascade;

drop sequence if exists photo_seq;

