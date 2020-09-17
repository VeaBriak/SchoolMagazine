package main.repository;

import main.model.SchoolPerformance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceRepository extends CrudRepository<SchoolPerformance, Integer> {
}
