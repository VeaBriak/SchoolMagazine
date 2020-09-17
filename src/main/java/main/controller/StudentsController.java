package main.controller;

import main.model.Student;
import main.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentsController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/student/")
    public List<Student> findAllStudent() {
        Iterable<Student> studentIterable = studentRepository.findAll();
        ArrayList<Student> students = new ArrayList<>();
        for (Student student : studentIterable) {
            students.add(student);
        }
        return students;
    }

    @PostMapping("/student/")
    public int addStudent(Student student) {
        Student newStudent = studentRepository.save(student);
        return newStudent.getId();
    }

    @GetMapping("/student/{id}")
    public ResponseEntity getStudent(@PathVariable int id) {
        Optional<Student> optionalStudent= studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalStudent.get(), HttpStatus.OK);
    }

}
