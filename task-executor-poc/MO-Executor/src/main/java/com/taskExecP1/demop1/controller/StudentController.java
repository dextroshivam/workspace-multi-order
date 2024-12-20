package com.taskExecP1.demop1.controller;

import com.taskExecP1.demop1.model.Student;
import com.taskExecP1.demop1.service.LargeObjectProcessor;
import com.taskExecP1.demop1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private LargeObjectProcessor largeObjectProcessor;

    @PostMapping("/save")
    public String saveStudents(@RequestBody List<Student> students) {
        long startTime = System.currentTimeMillis();
//        studentService.iterateAllStudents(students);
        largeObjectProcessor.processLargeObjects(students);
        long endTime = System.currentTimeMillis();
        return "Time taken to save students: " + (endTime - startTime) + " ms. Processed : "+ students.size() +" students";
    }
}