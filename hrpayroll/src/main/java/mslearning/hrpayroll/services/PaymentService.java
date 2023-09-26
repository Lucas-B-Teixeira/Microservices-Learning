package mslearning.hrpayroll.services;

import mslearning.hrpayroll.model.PaymentModel;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public PaymentModel getPayment(long workerId, int days){
        return new PaymentModel("bob", 200.0, days);
    }
}
