package pe.gob.sunat.microservices.curso.customers.client;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CustomerServiceClient {
  @POST("v1/customers")
  Call<Customer> create(@Body Customer customer);

  @GET("v1/customers/{id}")
  Call<Customer> get(@Path("id") Long id);
}


