/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.demo.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.modules.sys.entity.Dict;

/**
 * 产品Entity
 * @author jAmEs_
 * @version 2015-01-15
 */
@Entity
@Table(name = "gl_product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product extends IdEntity<Product> {
	
	private static final long serialVersionUID = 1L;
	private String name; 	// 名称
	private String type; 	// 產品类型（0：未定義；1：服裝；2：配件）
	private Dict cate1; 	// 產品分類1
	private double price; 	// 單價

	public Product() {
		super();
	}

	public Product(String id){
		this();
		this.id = id;
	}
	 
	@Length(min=1, max=200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min=1, max=1)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	//@JoinColumn(name="cate1")
	@JoinColumnsOrFormulas(value = {
			@JoinColumnOrFormula(column = @JoinColumn(name = "cate1", referencedColumnName = "value")),
			@JoinColumnOrFormula(formula = @JoinFormula(referencedColumnName = "type", value = "'gl_product_cate1'")) })
	public Dict getCate1() {
		return cate1;
	}

	public void setCate1(Dict cate1) {
		this.cate1 = cate1;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}


