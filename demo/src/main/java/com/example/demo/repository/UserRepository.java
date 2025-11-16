package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepositoryを継承するだけで、save(), findById(), findAll() 
    // などの基本的なDB操作メソッドが自動的に利用可能になります。
}