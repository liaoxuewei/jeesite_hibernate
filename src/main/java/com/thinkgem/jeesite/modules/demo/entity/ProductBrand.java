/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 品牌Entity
 * @author jAmEs_
 * @version 2015-01-27
 */
@Entity
@Table(name = "gl_productbrand")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductBrand extends IdEntity<ProductBrand> {
	
	private static final long serialVersionUID = 1L;
	private String name; 	// 名称

	public ProductBrand() {
		super();
	}

	public ProductBrand(String id){
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
	
}


