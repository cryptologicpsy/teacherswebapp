package com.example.teachersapp.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.teachersapp.dao.TeacherRepository;
import com.example.teachersapp.dto.TeacherDTO;
import com.example.teachersapp.model.Teacher;
import com.example.teachersapp.service.exceptions.TeacherIdAlreadyExistsException;
import com.example.teachersapp.service.exceptions.TeacherNotFoundException;


@Service
public class TeacherServiceImpl implements ITeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void insertTeacher(TeacherDTO teacherDTO) throws TeacherIdAlreadyExistsException, SQLException {
        if (teacherRepository.existsById(teacherDTO.getId())) {
            throw new TeacherIdAlreadyExistsException("Teacher with ID " + teacherDTO.getId() + " already exists.");
        }

        Teacher newTeacher = new Teacher(teacherDTO.getId(), teacherDTO.getSname(), teacherDTO.getFname());
        teacherRepository.save(newTeacher);
    }

    @Override
    public void deleteTeacher(TeacherDTO teacherDTO) throws TeacherNotFoundException, SQLException {
        if (!teacherRepository.existsById(teacherDTO.getId())) {
            throw new TeacherNotFoundException("Teacher with ID " + teacherDTO.getId() + " not found.");
        }

        teacherRepository.deleteById(teacherDTO.getId());
    }

    @Override
    public void updateTeacher(TeacherDTO oldTeacherDTO, TeacherDTO newTeacherDTO)
            throws TeacherNotFoundException, SQLException {
        if (!teacherRepository.existsById(oldTeacherDTO.getId())) {
            throw new TeacherNotFoundException("Teacher with ID " + oldTeacherDTO.getId() + " not found.");
        }

        Teacher oldTeacher = teacherRepository.getById(oldTeacherDTO.getId());
        oldTeacher.setSname(newTeacherDTO.getSname());
        oldTeacher.setFname(newTeacherDTO.getFname());

        teacherRepository.save(oldTeacher);
    }

    @Override
    public List<Teacher> getTeachersBySurname(String surname) throws SQLException {
        return teacherRepository.findBySnameStartingWith(surname);
    }

    @Override
    public Teacher getTeacherById(int id) throws SQLException {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isPresent()) {
            return optionalTeacher.get();
        } else {
            throw new SQLException("Teacher with ID " + id + " not found.");
        }
    }
}