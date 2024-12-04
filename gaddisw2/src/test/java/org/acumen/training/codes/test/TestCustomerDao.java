package org.acumen.training.codes.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.acumen.training.codes.BestBuyConfiguration;
import org.acumen.training.codes.dao.CustomerDao;
import org.acumen.training.codes.model.Customer;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCustomerDao {

	private BestBuyConfiguration config;
	private SessionFactory factory;
	private CustomerDao customerDao;

	@BeforeEach
	public void setup() {
		config = new BestBuyConfiguration();
		config.createConfiguration();
		factory = config.getSessionFactory();
		customerDao = new CustomerDao(factory);
	}

	@AfterEach
	public void teardown() {
		factory.close(); 
		config = null;
	}

	@Test
	public void testInsertCustomer() {
		Customer customer = new Customer();
		customer.setSalesmanId((long) 3001);
		customer.setCustomerName("John Doe");
		customer.setCity("Washington");		
		customer.setGrade(3);

		boolean isInserted = customerDao.insert(customer);
		assertTrue(isInserted, "Customer should be successfully inserted");
	}

	@Test
	public void testUpdateCustomerGrade() {
		Customer customer = new Customer();
		customer.setSalesmanId((long) 3001);
		customer.setCustomerName("Jane Doe");
		customer.setCity("Makati");		
		customer.setGrade(2);
		customerDao.insert(customer);
		Integer newGrade = 4;
		
		boolean isUpdated = customerDao.updateCustomerGrade(customer.getCustomerId(), newGrade);
		assertTrue(isUpdated, "Customer grade should be updated");

		List<Customer> customers = customerDao.selectAllCustomers();
		Customer updatedCustomer = customers.stream().filter(cust -> cust.getCustomerId().equals(customer.getCustomerId()))
				.findFirst().orElse(null);

		assertNotNull(updatedCustomer, "Updated customer should exist");
		assertEquals(newGrade, updatedCustomer.getGrade(), "Customer grade should match");
	}

	@Test
	public void testDeleteCustomer() {
		Customer customer = new Customer();
		customer.setSalesmanId((long) 3002);
		customer.setCustomerName("Rhandell");
		customer.setCity("Libertad");		
		customer.setGrade(2);
		customerDao.insert(customer);
		boolean isDeleted = customerDao.deleteCustomerById(customer.getCustomerId());
		
		assertTrue(isDeleted, "Customer should be successfully deleted");

		List<Customer> customers = customerDao.selectAllCustomers();
		Customer deletedCustomer = customers.stream().filter(cust -> cust.getCustomerId().equals(customer.getCustomerId()))
				.findFirst().orElse(null);

		assertNull(deletedCustomer, "Customer should be deleted from the database");
	}

	@Test
	public void testSelectAllCustomers() {
		CustomerDao dao = new CustomerDao(factory);
		List<Customer> recs = dao.selectAllCustomers();
		System.out.println(recs);
		assertNotNull(recs, "Customer list should not be null");
		assertFalse(recs.isEmpty(), "Customer list should not be empty");		
	}
}
