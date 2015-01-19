
/* Drop Tables */

DROP TABLE gl_product;



/* Create Tables */

CREATE TABLE gl_product
(
	id varchar(64) NOT NULL,
	create_by varchar(64),
	create_date datetime,
	update_by varchar(64),
	update_date datetime,
	remarks varchar(255),
	del_flag char(1) DEFAULT '0' NOT NULL,
	name varchar(100) NOT NULL,
	type char(1),
	cate1 char(1),
	price money NOT NULL,
	PRIMARY KEY (id)
);


/* Create Indexes */

CREATE INDEX gl_product_del_flag ON gl_product (del_flag);

