package pe.gob.sunat.microservices.curso.customers.service;

import pe.gob.sunat.microservices.curso.customers.dao.AddressDaoImpl;
import pe.gob.sunat.microservices.curso.customers.dao.CustomerDaoImpl;
import pe.gob.sunat.microservices.curso.customers.model.Customer;
//import pe.gob.sunat.microservices.curso.orders.service.InvalidOrderException;

import java.util.Optional;

public class CustomerService {
  private final CustomerDaoImpl dao;
  private final AddressDaoImpl addressDao;
  private final OrderService orderService;

  public CustomerService(CustomerDaoImpl dao, AddressDaoImpl addressDao, OrderService orderService) {
    this.dao = dao;
    this.addressDao = addressDao;
    this.orderService = orderService;
  }

  public Customer create(Customer customer) {
    return dao.create(customer);
  }

  public Optional<Customer> findById(Long id, Boolean includeAddresses) {
    return dao.find(id).map(customer -> {
      if (includeAddresses) {
        customer.setAddresses(addressDao.findByCustomer(id));
      }
      return customer;
    });
  }

  public void delete(Long id) {
    //dao.delete(id);
	  Boolean validatedCustomer = orderService.validateOrder(id);

	    if (!validatedCustomer) {
	      throw new InvalidOrderException("No se puede eliminar, el cliente ya tiene pedidos ", id.toString());
	    }
  }
}
