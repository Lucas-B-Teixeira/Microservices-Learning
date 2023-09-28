package mslearning.hrpayroll.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import mslearning.hrpayroll.model.PaymentModel;
import mslearning.hrpayroll.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payments")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @CircuitBreaker(name = "hr-worker", fallbackMethod = "getPaymentAlternative")
    @GetMapping(value = "/{workerId}/days/{days}")
    public ResponseEntity<PaymentModel> getPayment(@PathVariable Long workerId, @PathVariable Integer days){
        PaymentModel paymentModel = paymentService.getPayment(workerId, days);
        return ResponseEntity.ok(paymentModel);
    }

    public ResponseEntity<PaymentModel> getPaymentAlternative(Long workerId, Integer days, Throwable throwable){
        PaymentModel paymentModel = new PaymentModel("Brann", 400.0, days);
        return ResponseEntity.ok(paymentModel);
    }
}
