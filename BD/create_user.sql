/*
 * Script de tabla tbl_user (tabla de usuario)
 */
create table tbl_user(

	user_id serial primary key,
    user_name character(250) not null,
    user_last_name character(250) not null,
    user_mother_last_name character(250) not null,
    user_rfc character(30) not null,
    user_birth_date timestamp not null,
    constraint rfcunique unique (user_rfc)
	
)
