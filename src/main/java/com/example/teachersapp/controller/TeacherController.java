package com.example.teachersapp.controller;

import com.example.teachersapp.dao.ITeacherDAO;
import com.example.teachersapp.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final ITeacherDAO teacherDAO;

    @Autowired
    public TeacherController(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @GetMapping("/list")
    public String listTeachers(Model model) throws SQLException {
        List<Teacher> teachers = teacherDAO.getTeachersBySurname("");
        model.addAttribute("teachers", teachers);
        return "teacher-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);  
        return "teacher-form";
    }

    @PostMapping("/saveTeacher")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher) throws SQLException {
        teacherDAO.insert(teacher);
        return "redirect:/teachers/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("teacherId") int teacherId, Model model) throws SQLException {
        Teacher teacher = teacherDAO.getTeacherById(teacherId);
        model.addAttribute("teacher", teacher);
        return "teacher-form";
    }

    @GetMapping("/delete")
    public String deleteTeacher(@RequestParam("teacherId") int teacherId) throws SQLException {
        Teacher teacher = teacherDAO.getTeacherById(teacherId);
        teacherDAO.delete(teacher);
        return "redirect:/teachers/list";
    }
    
    @PostMapping("/updateTeacher")
    public String updateTeacher(@ModelAttribute("oldTeacher") Teacher oldTeacher, @ModelAttribute("newTeacher") Teacher newTeacher) throws SQLException {
        teacherDAO.update(oldTeacher, newTeacher);
        return "redirect:/teachers/list";
    }

    @GetMapping("/searchTeachers")
    public String searchTeachers(@RequestParam("surname") String surname, Model model) throws SQLException {
        List<Teacher> teachers = teacherDAO.getTeachersBySurname(surname);
        model.addAttribute("teachers", teachers);
        return "teacher-list";
    }
}