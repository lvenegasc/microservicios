package pe.gob.sunat.microservices.curso.customers.service;

import pe.gob.sunat.microservices.curso.customers.dao.AddressDaoImpl;
import pe.gob.sunat.microservices.curso.customers.model.Address;

import java.util.List;

public class AddressService {
  private final AddressDaoImpl dao;

  public AddressService(AddressDaoImpl dao) {
    this.dao = dao;
  }

  public Address create(Long customerId, Address address) {
    return dao.create(customerId, address);
  }

  public List<Address> addressesByCustomer(Long id) {
    return dao.findByCustomer(id);
  }

  public void delete(Long id) {
    dao.delete(id);
  }
}
