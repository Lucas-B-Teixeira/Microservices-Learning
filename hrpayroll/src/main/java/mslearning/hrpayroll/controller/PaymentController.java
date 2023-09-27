package mslearning.hrpayroll.controller;

import lombok.AllArgsConstructor;
import mslearning.hrpayroll.model.PaymentModel;
import mslearning.hrpayroll.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/{workerId}/days/{days}")
    public ResponseEntity<PaymentModel> getPayment(@PathVariable Long workerId, @PathVariable Integer days){
        PaymentModel paymentModel = paymentService.getPayment(workerId, days);
        return ResponseEntity.ok(paymentModel);
    }
}
