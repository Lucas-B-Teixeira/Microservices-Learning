package mslearning.hrwork.repository;

import mslearning.hrwork.model.WorkerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<WorkerModel, Long> {
}
