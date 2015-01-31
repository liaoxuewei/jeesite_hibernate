/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.demo.service;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.demo.entity.ProductBrand;
import com.thinkgem.jeesite.modules.demo.dao.ProductBrandDao;

/**
 * 品牌Service
 * @author jAmEs_
 * @version 2015-01-27
 */
@Component
@Transactional(readOnly = true)
public class ProductBrandService extends BaseService {

	@Autowired
	private ProductBrandDao productBrandDao;
	
	public ProductBrand get(String id) {
		return productBrandDao.get(id);
	}
	
	public Page<ProductBrand> find(Page<ProductBrand> page, ProductBrand productBrand) {
		DetachedCriteria dc = productBrandDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(productBrand.getName())){
			dc.add(Restrictions.like("name", "%"+productBrand.getName()+"%"));
		}
		dc.add(Restrictions.eq(ProductBrand.FIELD_DEL_FLAG, ProductBrand.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return productBrandDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(ProductBrand productBrand) {
		productBrandDao.save(productBrand);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		productBrandDao.deleteById(id);
	}
	
}
