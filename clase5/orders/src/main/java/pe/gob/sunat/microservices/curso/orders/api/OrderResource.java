package pe.gob.sunat.microservices.curso.orders.api;

import pe.gob.sunat.microservices.curso.orders.model.Order;
import pe.gob.sunat.microservices.curso.orders.service.OrderService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RolesAllowed("ADMIN")
@Path("/v1/orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {
  private final OrderService orderService;

  public OrderResource(OrderService orderService) {
    this.orderService = orderService;
  }


  @POST
  public Order create(@Valid Order customer) {
    return orderService.create(customer);
  }

  @DELETE
  @Path("/{id}")
  public void delete(@PathParam("id") Long id) {
    orderService.delete(id);
  }

  @GET
  @Path("/_customer")
  public List<Order> ordersList(@QueryParam("id") Long id) {
    return orderService.ordersByCustomer(id);
  }
}
