package pe.gob.sunat.microservices.curso.customers.api;


import pe.gob.sunat.microservices.curso.customers.service.AddressService;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/addresses")
@Produces(MediaType.APPLICATION_JSON)
public class AddressResource {

  private final AddressService addressService;

  public AddressResource(AddressService addressService) {
    this.addressService = addressService;
  }

}
