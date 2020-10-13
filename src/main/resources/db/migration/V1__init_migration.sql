create table if not exists users
(
	id bigserial not null,
	external_id bigint,
	username varchar(64) not null,
	password varchar(128) not null,
	name varchar(64) default null,
	patronymic varchar(64) default null,
	surname varchar(64) default null,
	role_id int default 2 not null,
	deleted bool default false,
	last_update timestamp default current_timestamp not null,
	created_at timestamp default current_timestamp not null
);

comment on table users is 'Password';

comment on column users.id is 'Identificator of user';

comment on column users.external_id is 'External identificator';

comment on column users.username is 'Username of user';

comment on column users.password is 'Password';

comment on column users.name is 'Name of user';

comment on column users.patronymic is 'Patronymic of user';

comment on column users.surname is 'Surname of user';

comment on column users.role_id is 'Id of role user';

comment on column users.deleted is 'Deleted state';

create unique index users_id_uindex
	on users (id);

create unique index users_username_uindex
	on users (username);

create unique index users_external_id_uindex
	on users (external_id);

alter table users
	add constraint users_pk
		primary key (id);



create table if not exists web_pages
(
	id bigserial not null,
	external_id bigint,
	name varchar(128),
	last_update timestamp default current_timestamp not null,
	created_at timestamp default current_timestamp not null
);

comment on table web_pages is 'Web page table';

comment on column web_pages.id is 'Identificator';

comment on column users.external_id is 'External identificator';

comment on column web_pages.name is 'Name of page';

create unique index web_pages_id_uindex
	on web_pages (id);

create unique index web_pages_external_id_uindex
	on web_pages (external_id);

alter table web_pages
	add constraint web_pages_pk
		primary key (id);


create table if not exists visit_events
(
	id bigserial not null,
	page_id bigint not null
		constraint visit_events_page_id_fk
				references web_pages,
	user_id bigint not null
		constraint visit_events_user_id_fk
				references users,
	event_date timestamp default current_timestamp not null,
	created_at timestamp default current_timestamp not null
);

comment on table visit_events is 'Visit events table';

comment on column visit_events.id is 'Identificator';

comment on column visit_events.page_id is 'Id of page';

comment on column visit_events.user_id is 'Id of user';

comment on column visit_events.event_date is 'Event date';

create unique index visit_events_id_uindex
	on visit_events (id);

alter table visit_events
	add constraint visit_events_pk
		primary key (id);

INSERT INTO public.users(username, password, role_id, deleted, last_update, created_at) VALUES ('admin', '21232F297A57A5A743894A0E4A801FC3', 0, false, now(), now());
INSERT INTO public.users(username, password, role_id, deleted, last_update, created_at) VALUES ('user', 'EE11CBB19052E40B07AAC0CA060C23EE', 1, false, now(), now());
