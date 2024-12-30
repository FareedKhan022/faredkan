package com.jsp.shoppingcart_application.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingcart_application.dao.MerchantDao;
import com.jsp.shoppingcart_application.dao.ProductDao;
import com.jsp.shoppingcart_application.dto.Merchant;
import com.jsp.shoppingcart_application.dto.Product;

@Controller
public class ProductController {

	@Autowired
	ProductDao dao;
	@Autowired
	MerchantDao mdao;
	@RequestMapping("/addproduct")
	public ModelAndView addCustomer() {
		Product m=new Product();
		ModelAndView mav=new ModelAndView();
		mav.addObject("productobj",m);
		mav.setViewName("productform");
		return mav;
		
	}
	@RequestMapping("/saveproduct")
	
	   public ModelAndView saveProduct(@ModelAttribute("productobj") Product p, HttpSession session) {
	      Merchant merchant = (Merchant)session.getAttribute("merchantinfo");
	      List<Product> products = merchant.getProduct();
	      if (products.size() > 0) {
	         products.add(p);
	         merchant.setProduct(products);
	      } else {
	         List<Product> productslist = new ArrayList<Product>();
	         productslist.add(p);
	         merchant.setProduct(productslist);
	      }
	      dao.saveProduct(p);
	      mdao.UpdateMerchant(merchant);
	      ModelAndView mav = new ModelAndView();
	      mav.addObject("msg", "data saved successfully");
	      mav.setViewName("merchantoptions");
	      return mav;
	}
	
	@RequestMapping("/fetchallproducts")
	public  ModelAndView fetchAllProducts()
	{
		List<Product> products=dao.findAllProducts();
		ModelAndView mav=new ModelAndView();
		mav.addObject("Productslist",products);
		mav.setViewName("displayallproducts");

		return mav;
	}
	
	
}