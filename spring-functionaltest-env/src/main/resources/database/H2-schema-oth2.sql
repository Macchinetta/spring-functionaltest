/* define the schemas. */

create table if not exists users(
    username varchar_ignorecase(50) not null primary key,
    password varchar_ignorecase(60) not null,
    enabled boolean not null
);

create table if not exists authorities (
    username varchar_ignorecase(50) not null,
    authority varchar_ignorecase(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);

create unique index ix_auth_username on authorities (username,authority);

create table if not exists clients (
	client_id varchar_ignorecase(50) not null primary key,
	client_secret varchar_ignorecase(60) not null,
	client_name varchar_ignorecase(256) not null,
	access_token_validity int(6) not null,
    refresh_token_validity int(6)
);

create table if not exists scopes (
	client_id varchar_ignorecase(50) not null,
	scope varchar_ignorecase(50) not null,
	constraint fk_scopes_clients foreign key(client_id) references clients(client_id)
);

create table if not exists resource_ids (
	client_id varchar_ignorecase(50) not null,
	resource_id varchar_ignorecase(50) not null,
	constraint fk_resources_clients foreign key(client_id) references clients(client_id)
);

create table if not exists authorized_grant_types (
	client_id varchar_ignorecase(50) not null,
	authorized_grant_type varchar_ignorecase(50) not null,
	constraint fk_grants_clients foreign key(client_id) references clients(client_id)
);

create table if not exists web_server_redirect_uris (
	client_id varchar_ignorecase(50) not null,
	web_server_redirect_uri varchar_ignorecase(256) not null,
	constraint fk_redirect_uris_clients foreign key(client_id) references clients(client_id)
);

create table if not exists oauth_access_token (
  token_id varchar_ignorecase(256),
  token BINARY,
  authentication_id varchar_ignorecase(256) PRIMARY KEY,
  user_name varchar_ignorecase(50),
  client_id varchar_ignorecase(50),
  authentication BINARY,
  refresh_token varchar_ignorecase(256)
);

create table if not exists oauth_refresh_token (
  token_id varchar_ignorecase(256),
  token BINARY,
  authentication BINARY
);

create table if not exists oauth_approvals (
  userId varchar_ignorecase(50),
  clientId varchar_ignorecase(50),
  scope varchar_ignorecase(50),
  status varchar_ignorecase(10),
  expiresAt TIMESTAMP,
  lastModifiedAt TIMESTAMP,
);

create table if not exists oauth_code (
  code varchar_ignorecase(256) PRIMARY KEY,
  authentication BINARY
);
