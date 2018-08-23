package pe.gob.sunat.microservices.curso.orders.service;

import pe.gob.sunat.microservices.curso.orders.dao.OrderDaoImpl;
import pe.gob.sunat.microservices.curso.orders.model.Order;

import java.util.Date;
import java.util.List;

public class OrderService {

  private final CustomerService customerService;
  private final OrderDaoImpl dao;

  public OrderService(CustomerService customerService, OrderDaoImpl dao) {
    this.customerService = customerService;
    this.dao = dao;
  }

  public Order create(Order order) {
    Boolean validatedCustomer = customerService.validateCustomer(order.getCustomerId());

    if (!validatedCustomer) {
      throw new InvalidCustomerException("No se pudo validar al cliente. Se cancela la creación del pedido.", order.getCustomerId().toString());
    }

    order.setCreatedAt(new Date());
    return dao.create(order);
  }

  public List<Order> ordersByCustomer(Long id) {
    return dao.findByCustomer(id);
  }

  public void delete(Long id) {
    dao.delete(id);
  }
}
