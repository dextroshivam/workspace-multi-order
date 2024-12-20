package com.taskExecP1.demop1.service;

import com.taskExecP1.demop1.model.Student;
import com.taskExecP1.demop1.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    private void saveStudent(Student student) {
        studentRepository.save(student);
        System.out.println("Saved student: " + student.getName() + " by " + Thread.currentThread().getName());
    }


    public void iterateAllStudents(List<Student> students) {
        int threadPoolSize = 10; // Number of threads in the pool
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);

        try {
            List<? extends Future<?>> futures = students.stream()
                    .map(student -> executor.submit(() -> {
                        saveStudent(student);
                    }))
                    .toList();

            // Wait for all tasks to complete
            for (Future<?> future : futures) {
                future.get(); // This blocks until the task is done
            }

            System.out.println("All students saved successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown(); // Shut down the executor to release resources
        }
    }


}
