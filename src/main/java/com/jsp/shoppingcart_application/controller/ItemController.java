package com.jsp.shoppingcart_application.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingcart_application.dao.CartDao;
import com.jsp.shoppingcart_application.dao.ItemDao;
import com.jsp.shoppingcart_application.dao.ProductDao;
import com.jsp.shoppingcart_application.dto.Cart;
import com.jsp.shoppingcart_application.dto.Customer;
import com.jsp.shoppingcart_application.dto.Item;
import com.jsp.shoppingcart_application.dto.Product;

@Controller
public class ItemController
{
	@Autowired
	ProductDao pdao;
	
	@Autowired
	ItemDao idao;
	
	@Autowired
	CartDao cdao;
	
	@RequestMapping("/addcart")
	public ModelAndView addItem(@RequestParam("id")int pid)
	{
		Product p=pdao.findProductById(pid);
		Item i=new Item();
		i.setBrand(p.getBrand());
		i.setCategory(p.getCategory());
		i.setPrice(p.getPrice());
		i.setpId(p.getId());
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("itemform");
		mav.addObject("itemobj",i);
		return mav;
	}
	
	@RequestMapping("/additemtocart")
	public ModelAndView addItemToCart(@ModelAttribute("itemobj") Item i, HttpSession session) 
	{
		i.setPrice(i.getQuantity() * i.getPrice());
		
		Customer customer=(Customer) session.getAttribute("customerinfo");
		Cart cart= customer.getCart();
		
		List<Item> items= cart.getItem();
		if(items.size()>0) {
			items.add(i);
			cart.setItem(items);
			cart.setTotalPrice(cart.getTotalPrice() + i.getPrice());
		}
		else {
			List<Item> items1= new ArrayList<Item>();
			items1.add(i);
			
			cart.setTotalPrice(i.getPrice());
			cart.setItem(items1);
		}
		
		idao.saveItem(i);
		cdao.UpdateCart(cart);
		
		ModelAndView mav= new ModelAndView();
		mav.setViewName("redirect://fetchallproducts");
		return mav;
	}
}
