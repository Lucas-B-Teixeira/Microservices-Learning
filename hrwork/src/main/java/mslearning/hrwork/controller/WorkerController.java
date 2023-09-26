package mslearning.hrwork.controller;

import lombok.AllArgsConstructor;
import mslearning.hrwork.dto.WorkerDTO;
import mslearning.hrwork.model.WorkerModel;
import mslearning.hrwork.repository.WorkerRepository;
import mslearning.hrwork.service.WorkerService;
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

    @GetMapping()
    public ResponseEntity<List<WorkerDTO>> findAll(){
        return ResponseEntity.ok(workerService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WorkerDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(workerService.findByID(id));
    }

}
