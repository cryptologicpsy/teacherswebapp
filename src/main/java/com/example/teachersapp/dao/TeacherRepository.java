package com.example.teachersapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.teachersapp.model.Teacher;




public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    List<Teacher> findBySnameStartingWith(String surname);
    
}
