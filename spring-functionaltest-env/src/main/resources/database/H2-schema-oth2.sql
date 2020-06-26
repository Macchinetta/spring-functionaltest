/* define the schemas. */

create table if not exists users(
    username varchar(50) not null primary key,
    password varchar(88) not null,
    enabled boolean not null
);

create table if not exists authorities (
    username varchar(50) not null references users(username),
    authority varchar(50) not null,
    primary key(username, authority)
);

/* Extend the table along with the extends of BaseClientDetails. (Start) */
create table if not exists clients (
    client_id varchar(50) not null primary key,
    client_secret varchar(88) not null,
    client_name varchar(256) not null,
    access_token_validity integer not null,
    refresh_token_validity integer
);

create table if not exists scopes (
    client_id varchar(50) not null references clients(client_id),
    scope varchar(50) not null
);

create table if not exists resource_ids (
    client_id varchar(50) not null references clients(client_id),
    resource_id varchar(50) not null
);

create table if not exists authorized_grant_types (
    client_id varchar(50) not null references clients(client_id),
    authorized_grant_type varchar(50) not null
);

create table if not exists web_server_redirect_uris (
    client_id varchar(50) not null references clients(client_id),
    web_server_redirect_uri varchar(256) not null
);
/* Extend the table along with the extends of BaseClientDetails. (End) */

create table if not exists oauth_access_token (
  token_id VARCHAR(256),
  token BYTEA,
  authentication_id VARCHAR(256) primary key,
  user_name VARCHAR(50),
  client_id VARCHAR(50),
  authentication BYTEA,
  refresh_token VARCHAR(256)
);

create table if not exists oauth_refresh_token (
  token_id VARCHAR(256) primary key,
  token BYTEA,
  authentication BYTEA
);

create table if not exists oauth_approvals (
  userId VARCHAR(50),
  clientId VARCHAR(50),
  scope VARCHAR(50),
  status VARCHAR(10),
  expiresAt TIMESTAMP,
  lastModifiedAt TIMESTAMP,
  primary key(userId, clientId, scope)
);

create table if not exists oauth_code (
  code VARCHAR(256) primary key,
  authentication BYTEA
);
