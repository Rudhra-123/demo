package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;

import java.util.*;

@RestController
@RequestMapping("api/students")

public class StudentController {

    private  List<Student> students =new ArrayList<>();
    

    public StudentController()
    {
        students.add(new Student(1L,"Rudhra","rudhra@gmail.com"));
        students.add(new Student(2L,"Aman","aman@gmail.com"));
    }

    @GetMapping
    public List<Student> getAllStudents()
    {
        return students;
    }



    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // POST new student
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }

    // PUT update student
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                s.setName(updatedStudent.getName());
                s.setEmail(updatedStudent.getEmail());
                return s;
            }
        }
        return null;
    }

    // DELETE student
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        students.removeIf(s -> s.getId().equals(id));
        return "Deleted student with ID: " + id;
    }
}






