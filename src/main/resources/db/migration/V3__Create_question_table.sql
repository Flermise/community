create table question
(
	id int not null,
	title varchar(50),
	description text,
	gmt_create bigint,
	gmt_modified int,
	creator int,
	comment_count int,
	view_count int,
	fav_count int,
	tag varchar(256),
	constraint question_pk
		primary key (id)
);

