package mslearning.hrwork.mapper;

import lombok.RequiredArgsConstructor;
import mslearning.hrwork.dto.WorkerDTO;
import mslearning.hrwork.model.WorkerModel;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class WorkerMapper {

    public static WorkerDTO modelToDTO(WorkerModel w){
        return WorkerDTO.builder()
                .name(w.getName())
                .dailyIncome(w.getDailyIncome())
                .build();
    }

    public static List<WorkerDTO> listModelToListDTO(List<WorkerModel> listW){
        List<WorkerDTO> dtoList = new ArrayList<>();

        for(WorkerModel w : listW){
            dtoList.add(modelToDTO(w));
        }

        return dtoList;
    }

}
