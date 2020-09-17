package main.controller;

import main.model.Subject;
import main.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/subject/")
    public List<Subject> findAllSubject() {
        Iterable<Subject> subjectIterable = subjectRepository.findAll();
        ArrayList<Subject> subjects = new ArrayList<>();
        for (Subject subject: subjectIterable) {
            subjects.add(subject);
        }
        return subjects;
    }

    @PostMapping("/subject/")
    public int addSubject(Subject subject) {
        Subject newSubject = subjectRepository.save(subject);
        return newSubject.getId();
    }

    @GetMapping("/subject/{id}")
    public ResponseEntity getSubject(@PathVariable int id) {
        Optional<Subject> optional= subjectRepository.findById(id);
        if (optional.isPresent()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optional.get(), HttpStatus.OK);
    }
}
