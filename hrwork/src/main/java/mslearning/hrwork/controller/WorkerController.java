package mslearning.hrwork.controller;

import lombok.AllArgsConstructor;
import mslearning.hrwork.dto.WorkerDTO;
import mslearning.hrwork.model.WorkerModel;
import mslearning.hrwork.repository.WorkerRepository;
import mslearning.hrwork.service.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/workers")
@AllArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

    private final Environment env;

    private static Logger logger = LoggerFactory.getLogger(WorkerController.class);

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
