package pe.gob.sunat.microservices.curso.orders.service.command;

import java.util.List;
import java.util.Optional;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import pe.gob.sunat.microservices.curso.customers.client.Address;
import pe.gob.sunat.microservices.curso.customers.client.AddressServiceClient;
import retrofit2.Response;

public class AddressServiceRemoteInvokerCommand extends HystrixCommand<Boolean> {
	public static final int CONCURRENT_REQUESTS = 20;
	private final AddressServiceClient addressServiceClient;
	private final Long customerId;
	private final Long deliveryAddressId;

	public AddressServiceRemoteInvokerCommand(AddressServiceClient addressServiceClient, Long customerId,
			Long deliveryAddressId) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GroupComandoLatencia"))
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
						.withExecutionIsolationSemaphoreMaxConcurrentRequests(CONCURRENT_REQUESTS)));
		this.addressServiceClient = addressServiceClient;
		this.customerId = customerId;
		this.deliveryAddressId = deliveryAddressId;
	}

	@Override
	protected Boolean run() throws Exception {
		//Response<List<Address>> addresses = addressServiceClient.getByCustomer(customerId).execute();
		Response<Address> address = addressServiceClient.getById(deliveryAddressId).execute();
		//boolean resultado = addresses.body().stream().anyMatch(s -> s.getId().equals(deliveryAddressId));
		Optional<Address> addressOptional = Optional.ofNullable(address.body());
		return addressOptional.isPresent();
	}

	@Override
	protected Boolean getFallback() {
		return false;
	}
}
