package com.example.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestJpa extends JpaRepository<Test, Long> {
}
