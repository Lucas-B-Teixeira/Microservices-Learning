package mslearning.hrwork.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import mslearning.hrwork.dto.WorkerDTO;
import mslearning.hrwork.service.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/workers")
@RequiredArgsConstructor
public class WorkerController {

    private static Logger logger = LoggerFactory.getLogger(WorkerController.class);

    @Value("${test.config}")
    private String TEST_CONFIG;

    private final WorkerService workerService;

    private final Environment env;

    @GetMapping(value = "/configs")
    public ResponseEntity<Void> getConfigs(){
        logger.info("CONFIG " + TEST_CONFIG);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<WorkerDTO>> findAll(){
        return ResponseEntity.ok(workerService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WorkerDTO> findById(@PathVariable Long id){
        logger.info("PORT = " + env.getProperty("local.server.port"));

        return ResponseEntity.ok(workerService.findByID(id));
    }

}
