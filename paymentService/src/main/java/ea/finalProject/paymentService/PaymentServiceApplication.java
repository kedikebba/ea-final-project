package ea.finalProject.paymentService;

import ea.finalProject.paymentService.model.Payment;
import ea.finalProject.paymentService.model.PaymentType;
import ea.finalProject.paymentService.model.PaymentTypeBuilder;
import ea.finalProject.paymentService.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableCircuitBreaker
@SpringBootApplication
public class PaymentServiceApplication implements CommandLineRunner {

	@Autowired
	private PaymentRepository paymentRepository;

	@Bean
	public RestTemplate rest(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PaymentType pt = new PaymentTypeBuilder().setAccountNumber("1234567891011").setBankName("MIDWEST FUCKERS").buildPaymentType();
		System.out.print(pt);
	}
}
