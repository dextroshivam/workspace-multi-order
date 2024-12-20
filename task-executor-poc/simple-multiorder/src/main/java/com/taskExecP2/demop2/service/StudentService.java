package com.taskExecP2.demop2.service;

import com.taskExecP2.demop2.model.Student;
import com.taskExecP2.demop2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void saveAllStudents(List<Student> students) {
        int count=0;
        for (Student student : students) {
            System.out.println("Saved student: " + student.getName()+" count "+count++);
            studentRepository.save(student);
        }
    }

//    public void saveAllStudents(List<Student> students) {
//        studentRepository.saveAll(students);
//    }
}