package pe.gob.sunat.microservices.curso.orders.client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OrderServiceClient {

	@GET("v1/orders/_customer/")
	Call<List<Order>> get(@Query("id") Long id);
}
