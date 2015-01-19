
/* Drop Tables */

DROP TABLE gl_product;


/* Create Tables */

CREATE TABLE gl_product
(
	id varchar2(64) NOT NULL,
	create_by varchar2(64),
	create_date timestamp,
	update_by varchar2(64),
	update_date timestamp,
	remarks varchar2(255),
	del_flag char(1) DEFAULT '0' NOT NULL,
	name varchar2(100) NOT NULL,
	type char(1),
	cate1 char(1),
	price money NOT NULL,
	PRIMARY KEY (id)
);


/* Create Indexes */

CREATE INDEX gl_product_del_flag ON gl_product (del_flag);


/* Comments */

COMMENT ON TABLE gl_product IS '产品表';
COMMENT ON COLUMN gl_product.id IS '编号';
COMMENT ON COLUMN gl_product.name IS '产品名称';
COMMENT ON COLUMN gl_product.type IS '产品类型';
COMMENT ON COLUMN gl_product.cate1 IS '产品分类1';
COMMENT ON COLUMN gl_product.create_by IS '创建者';
COMMENT ON COLUMN gl_product.create_date IS '创建时间';
COMMENT ON COLUMN gl_product.update_by IS '更新者';
COMMENT ON COLUMN gl_product.update_date IS '更新时间';
COMMENT ON COLUMN gl_product.remarks IS '备注信息';
COMMENT ON COLUMN gl_product.del_flag IS '删除标记';
COMMENT ON COLUMN gl_product.price IS '零售价';
