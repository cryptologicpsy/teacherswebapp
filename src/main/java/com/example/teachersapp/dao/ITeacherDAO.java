package com.example.teachersapp.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.teachersapp.model.Teacher;


@Repository
public interface ITeacherDAO {
	void insert(Teacher teacher) throws SQLException;
	void delete(Teacher teacher) throws SQLException;
	void update(Teacher oldTeacher, Teacher newTeacher) throws SQLException;
	List<Teacher> getTeachersBySurname(String surname) throws SQLException;
	Teacher getTeacherById(int id) throws SQLException;
}
