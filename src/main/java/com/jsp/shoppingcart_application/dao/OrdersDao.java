package com.jsp.shoppingcart_application.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.shoppingcart_application.dto.Orders;

@Repository
public class OrdersDao {

	@Autowired
	private EntityManagerFactory emf;
	
	public void saveOrders(Orders o)
	{
		EntityManager em= emf.createEntityManager();
		EntityTransaction et= em.getTransaction();
		
		et.begin();
		em.persist(o);
		et.commit();
	}
	
	public Orders findOrdersById(int id)
	{
		EntityManager em= emf.createEntityManager();
		
		Orders o= em.find(Orders.class, id);
		return o;
	}
	
	public void UpdateOrders(Orders o)
	{
		EntityManager em= emf.createEntityManager();
		EntityTransaction et= em.getTransaction();
		
		et.begin();
		em.merge(o);
		et.commit();
	}
	
	public void deleteOrdersById(int id)
	{
		EntityManager em= emf.createEntityManager();
		EntityTransaction et= em.getTransaction();
		
		Orders o= em.find(Orders.class, id);
		
		et.begin();
		em.remove(o);
		et.commit();
	}
}
