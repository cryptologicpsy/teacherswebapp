package com.example.teachersapp.dao;

import com.example.teachersapp.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TeacherDAOImpl implements ITeacherDAO {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherDAOImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void insert(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void delete(Teacher teacher) {
        teacherRepository.delete(teacher);
    }

    @Override
    public void update(Teacher oldTeacher, Teacher newTeacher) {
        oldTeacher.setSname(newTeacher.getSname());
        oldTeacher.setFname(newTeacher.getFname());
        teacherRepository.save(oldTeacher);
    }

    @Override
    public List<Teacher> getTeachersBySurname(String surname) {
        return teacherRepository.findBySnameStartingWith(surname);
    }

    @Override
    public Teacher getTeacherById(int id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        return optionalTeacher.orElse(null);
    }
}