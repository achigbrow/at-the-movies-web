create table movie (movie_id CHAR(16) FOR BIT DATA not null, created timestamp not null, genre varchar(255), screenwriter varchar(255), title varchar(255) not null, updated timestamp not null, primary key (movie_id))
create table actor (actor_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, updated timestamp not null, primary key (actor_id))
create table actor_movies (actor_id CHAR(16) FOR BIT DATA not null, movie_id CHAR(16) FOR BIT DATA not null)
create table movie (movie_id CHAR(16) FOR BIT DATA not null, created timestamp not null, genre varchar(255), screenwriter varchar(255), title varchar(255) not null, updated timestamp not null, primary key (movie_id))
alter table actor add constraint UK_5wtiqyg64stukhxyqy62m7aq5 unique (name)
alter table actor_movies add constraint FK59dhq8qa32r9rv3wl60eq9tss foreign key (movie_id) references movie
alter table actor_movies add constraint FKlxdxq2ypa4soqy6223nlp7api foreign key (actor_id) references actor
