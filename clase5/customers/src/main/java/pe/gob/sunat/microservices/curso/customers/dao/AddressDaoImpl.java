package pe.gob.sunat.microservices.curso.customers.dao;

import org.jdbi.v3.core.Jdbi;
import pe.gob.sunat.microservices.curso.customers.model.Address;

import java.util.List;

public class AddressDaoImpl {
  private final Jdbi jdbi;

  public AddressDaoImpl(Jdbi jdbi) {
    this.jdbi = jdbi;
  }

  public Address create(Long customerId, Address address) {
    jdbi.inTransaction(handle -> {
      String insert = "INSERT INTO addresses " +
        "(street, name, country, owner_id) " +
        "VALUES (:street, :name, :country, :owner_id);";
      return handle.createUpdate(insert)
        .bind("street", address.getStreet())
        .bind("name", address.getName())
        .bind("country", address.getCountry())
        .bind("owner_id", customerId)
        .execute();
    });

    return address;
  }

  public void delete(Long id) {
    jdbi.inTransaction(handle -> {
      String delete = "DELETE FROM addresses where id=:id";
      return handle.createUpdate(delete)
        .bind("id", id)
        .execute();
    });
  }

  public List<Address> findByCustomer(Long id) {
    return jdbi.withHandle(handle ->
      handle.createQuery("SELECT * from addresses where owner_id=:id")
        .bind("id", id)
        .map(new AddressMapper())
        .list());
  }
}
