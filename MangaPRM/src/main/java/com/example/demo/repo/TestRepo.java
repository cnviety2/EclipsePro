package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TestEntity;

@Repository
public interface TestRepo extends JpaRepository<TestEntity, String> {

}
