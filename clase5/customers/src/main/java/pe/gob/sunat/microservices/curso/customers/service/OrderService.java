package pe.gob.sunat.microservices.curso.customers.service;

import pe.gob.sunat.microservices.curso.customers.service.command.OrderServiceRemoteInvokerCommand;
import pe.gob.sunat.microservices.curso.orders.client.OrderServiceClient;

public class OrderService {
	private final OrderServiceClient orderServiceClient;

	  public OrderService(OrderServiceClient orderServiceClient) {
	    this.orderServiceClient = orderServiceClient;
	  }

	  public Boolean validateOrder(Long id) {
	    return
	      new OrderServiceRemoteInvokerCommand(orderServiceClient, id)
	        .execute();
	  }

}
