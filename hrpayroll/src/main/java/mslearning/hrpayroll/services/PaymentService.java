package mslearning.hrpayroll.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import mslearning.hrpayroll.dto.WorkerDTO;
import mslearning.hrpayroll.feignClients.WorkerFeignClient;
import mslearning.hrpayroll.model.PaymentModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


@Service
@AllArgsConstructor
public class PaymentService {


    private final WorkerFeignClient workerFeignClient;

    //@CircuitBreaker(name = "hr-worker", fallbackMethod = "getPaumentAlternative")
    public PaymentModel getPayment(long workerId, int days){

        WorkerDTO worker = workerFeignClient.findById(workerId).getBody();

        return new PaymentModel(worker.getName(), worker.getDailyIncome(), days);
    }

//    public PaymentModel getPaumentAlternative(Long workerId, Integer days){
////        PaymentModel paymentModel = new PaymentModel("Brann", 400.0, days);
//        return new PaymentModel("Brann", 400.0, days);
//    }

}
