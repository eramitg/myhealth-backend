create table PASSWORD_RESET_TOKEN (
  ID bigint not null,
  TOKEN varchar(255),
  VALID_TILL datetime,
  CREATED datetime,
  ACTIVE  bool,
  EMAIL varchar(255)
);