package com.taskExecP1.demop1.service;

import com.taskExecP1.demop1.model.Student;
import com.taskExecP1.demop1.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class LargeObjectProcessor {
    private final StudentRepository studentRepository;
    private static final int BATCH_SIZE = 100;

    public LargeObjectProcessor(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void processLargeObjects(List<Student> students) {
        int threadPoolSize = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);

        try {
            for (int i = 0; i < students.size(); i += BATCH_SIZE) {
                List<Student> batch = students.subList(i, Math.min(i + BATCH_SIZE, students.size()));

                Future<?> future = executor.submit(() -> {
                    saveBatch(batch);
                });

//                future.get(); // Wait for batch to complete (optional)
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    private void saveBatch(List<Student> students) {
        studentRepository.saveAll(students); // Use batch insert/update
        System.out.println("Saved batch of size: " + students.size()+" by "+Thread.currentThread().getName());
    }
}
