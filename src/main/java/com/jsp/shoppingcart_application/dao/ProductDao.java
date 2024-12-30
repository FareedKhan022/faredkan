package com.jsp.shoppingcart_application.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.shoppingcart_application.dto.Product;

@Repository
public class ProductDao {

	@Autowired
	private EntityManagerFactory emf;
	
	public void saveProduct(Product p)
	{
		EntityManager em= emf.createEntityManager();
		EntityTransaction et= em.getTransaction();
		
		et.begin();
		em.persist(p);
		et.commit();
	}
	
	public Product findProductById(int id)
	{
		EntityManager em= emf.createEntityManager();
		
		Product p= em.find(Product.class, id);
		return p;
	}
	
	public void UpdateProduct(Product p)
	{
		EntityManager em= emf.createEntityManager();
		EntityTransaction et= em.getTransaction();
		
		et.begin();
		em.merge(p);
		et.commit();
	}
	
	public void deleteProductById(int id)
	{
		EntityManager em= emf.createEntityManager();
		EntityTransaction et= em.getTransaction();
		
		Product p= em.find(Product.class, id);
		
		et.begin();
		em.remove(p);
		et.commit();
	}
	
	public List<Product> findAllProducts(){
		EntityManager em= emf.createEntityManager();
		
		Query query= em.createQuery("select p from Product p");
		
		@SuppressWarnings("unchecked")
		List<Product> products= query.getResultList();
		return products;
	}
	
	public List<Product> findProductByBrand(String brand){
		EntityManager em= emf.createEntityManager();
		
		Query query= em.createQuery("select p from Product P where brand=?1");
		query.setParameter(1, brand);
		@SuppressWarnings("unchecked")
		List<Product> products= query.getResultList();
		return products;
	}
	
}
