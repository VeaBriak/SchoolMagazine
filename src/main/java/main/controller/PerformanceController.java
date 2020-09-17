package main.controller;

import main.model.SchoolPerformance;
import main.model.Student;
import main.repository.PerformanceRepository;
import main.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PerformanceController {

    @Autowired
    private PerformanceRepository performanceRepository;

    @GetMapping("/school_performance/")
    public List<SchoolPerformance> findAllPerform() {
        Iterable<SchoolPerformance> iterable = performanceRepository.findAll();
        ArrayList<SchoolPerformance> performances = new ArrayList<>();
        for (SchoolPerformance performance : iterable) {
            performances.add(performance);
        }
        return performances;
    }

    @PostMapping("/school_performance/")
    public int addPerform(SchoolPerformance performance) {
        SchoolPerformance newPerform = performanceRepository.save(performance);
        return newPerform.getId();
    }

    @GetMapping("/school_performance/{id}")
    public ResponseEntity getPerform(@PathVariable int id) {
        Optional<SchoolPerformance> optional= performanceRepository.findById(id);
        if (optional.isPresent()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optional.get(), HttpStatus.OK);
    }
}
