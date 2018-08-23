package pe.gob.sunat.microservices.curso.orders.service;

import pe.gob.sunat.microservices.curso.customers.client.AddressServiceClient;
import pe.gob.sunat.microservices.curso.orders.service.command.AddressServiceRemoteInvokerCommand;

public class AddressService {
  private final AddressServiceClient addressServiceClient;

  public AddressService(AddressServiceClient addressServiceClient) {
    this.addressServiceClient = addressServiceClient;
  }

  public Boolean validateAddress(Long customerId, Long deliveryAddressId) {
    return
      new AddressServiceRemoteInvokerCommand(addressServiceClient, customerId, deliveryAddressId)
        .execute();
  }
}
