package com.taskExecP2.demop2.controller;

import com.taskExecP2.demop2.model.Student;
import com.taskExecP2.demop2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public String saveStudents(@RequestBody List<Student> students) {
        long startTime = System.currentTimeMillis();
        studentService.saveAllStudents(students);
        long endTime = System.currentTimeMillis();
        return "Time taken to save students: " + (endTime - startTime) + " ms. Processed : "+ students.size() +" students";
    }
}