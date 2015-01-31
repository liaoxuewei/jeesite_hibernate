/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.demo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.demo.entity.ProductBrand;
import com.thinkgem.jeesite.modules.demo.service.ProductBrandService;

/**
 * 品牌Controller
 * @author jAmEs_
 * @version 2015-01-27
 */
@Controller
@RequestMapping(value = "${adminPath}/demo/productBrand")
public class ProductBrandController extends BaseController {

	@Autowired
	private ProductBrandService productBrandService;
	
	@ModelAttribute
	public ProductBrand get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return productBrandService.get(id);
		}else{
			return new ProductBrand();
		}
	}
	
	@RequiresPermissions("demo:productBrand:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProductBrand productBrand, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			productBrand.setCreateBy(user);
		}
        Page<ProductBrand> page = productBrandService.find(new Page<ProductBrand>(request, response), productBrand); 
        model.addAttribute("page", page);
		return "modules/" + "demo/productBrandList";
	}

	@RequiresPermissions("demo:productBrand:view")
	@RequestMapping(value = "form")
	public String form(ProductBrand productBrand, Model model) {
		model.addAttribute("productBrand", productBrand);
		return "modules/" + "demo/productBrandForm";
	}

	@RequiresPermissions("demo:productBrand:edit")
	@RequestMapping(value = "save")
	public String save(ProductBrand productBrand, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, productBrand)){
			return form(productBrand, model);
		}
		productBrandService.save(productBrand);
		addMessage(redirectAttributes, "保存品牌'" + productBrand.getName() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/demo/productBrand/?repage";
	}
	
	@RequiresPermissions("demo:productBrand:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		productBrandService.delete(id);
		addMessage(redirectAttributes, "删除品牌成功");
		return "redirect:"+Global.getAdminPath()+"/demo/productBrand/?repage";
	}

}
