package mslearning.hrpayroll.feignClients;

import mslearning.hrpayroll.config.AppConfig;
import mslearning.hrpayroll.dto.WorkerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hr-worker", path = "/workers", configuration = AppConfig.class)
public interface WorkerFeignClient {

    @GetMapping(value = "/{id}")
    ResponseEntity<WorkerDTO> findById(@PathVariable Long id);

}
