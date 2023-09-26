package mslearning.hrpayroll.services;

import lombok.AllArgsConstructor;
import mslearning.hrpayroll.model.PaymentModel;
import mslearning.hrpayroll.model.WorkerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${hr-worker.host}")
    private String WORKER_HOST;

    @Autowired
    private RestTemplate restTemplate;

    public PaymentModel getPayment(long workerId, int days){
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", String.valueOf(workerId));

        WorkerModel worker = restTemplate.getForObject(WORKER_HOST + "/workers/{id}", WorkerModel.class, uriVariables);

        return new PaymentModel(worker.getName(), worker.getDailyIncome(), days);
    }

}
