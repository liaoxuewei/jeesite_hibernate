SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE gl_product;



/* Create Tables */

CREATE TABLE gl_product
(
	id varchar(64) NOT NULL COMMENT '编号',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	name varchar(100) NOT NULL COMMENT '区域名称',
	type char(1) COMMENT '产品类型',
	cate1 char(1) COMMENT '产品分类1',
	price money NOT NULL COMMENT '零售价',
	PRIMARY KEY (id)
) COMMENT = '产品表';



/* Create Indexes */

CREATE INDEX gl_product_del_flag ON gl_product (del_flag ASC);

