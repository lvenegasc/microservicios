package pe.gob.sunat.microservices.curso.customers.client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AddressServiceClient {
  
  @GET("v1/customers/{id}/addresses")
  Call<List<Address>> getByCustomer(@Path("id") Long id);
  
  @GET("v1/addresses/{id}")
  Call<Address> getById(@Path("id") Long id);
  
}


