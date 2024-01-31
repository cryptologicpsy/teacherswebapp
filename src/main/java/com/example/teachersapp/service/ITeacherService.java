package com.example.teachersapp.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.teachersapp.dto.TeacherDTO;
import com.example.teachersapp.model.Teacher;
import com.example.teachersapp.service.exceptions.TeacherIdAlreadyExistsException;
import com.example.teachersapp.service.exceptions.TeacherNotFoundException;




public interface ITeacherService {
    void insertTeacher(TeacherDTO teacherDTO) throws TeacherIdAlreadyExistsException, SQLException;

    void deleteTeacher(TeacherDTO teacherDTO) throws TeacherNotFoundException, SQLException;

    void updateTeacher(TeacherDTO oldTeacherDTO, TeacherDTO newTeacherDTO) throws TeacherNotFoundException, SQLException;

    List<Teacher> getTeachersBySurname(String surname) throws SQLException;

    Teacher getTeacherById(int id) throws SQLException;
}