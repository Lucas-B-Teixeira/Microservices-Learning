package mslearning.hrpayroll.services;

import lombok.AllArgsConstructor;
import mslearning.hrpayroll.dto.WorkerDTO;
import mslearning.hrpayroll.feignClients.WorkerFeignClient;
import mslearning.hrpayroll.model.PaymentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PaymentService {

    private final WorkerFeignClient workerFeignClient;

    public PaymentModel getPayment(long workerId, int days){

        WorkerDTO worker = workerFeignClient.findById(workerId).getBody();

        return new PaymentModel(worker.getName(), worker.getDailyIncome(), days);
    }

}
