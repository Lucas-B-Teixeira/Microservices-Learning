package mslearning.hrwork.service;

import lombok.AllArgsConstructor;
import mslearning.hrwork.dto.WorkerDTO;
import mslearning.hrwork.mapper.WorkerMapper;
import mslearning.hrwork.model.WorkerModel;
import mslearning.hrwork.repository.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkerService {

    private final WorkerRepository workerRepository;

    public List<WorkerDTO> findAll(){
        List<WorkerDTO> listWorderDTO = WorkerMapper.listModelToListDTO(workerRepository.findAll());
        return listWorderDTO;
    }

    public WorkerDTO findByID(Long id){
        WorkerDTO worderDTO = WorkerMapper.modelToDTO(workerRepository.findById(id).get());
        return worderDTO;
    }
}
