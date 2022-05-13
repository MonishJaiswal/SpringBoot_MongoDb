package com.example.demo.reposatory;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.objects.Student;

public interface studentRepo extends MongoRepository<Student, Integer> {

}
