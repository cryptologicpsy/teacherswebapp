package com.example.teachersapp.service.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeacherNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TeacherNotFoundException(String message) {
        super(message);
    }

    // Προσθήκη ενός επιπλέον κατασκευαστή για ακέραιους
    public TeacherNotFoundException(int teacherId) {
        super("Teacher with id = " + teacherId + " does not exist");
    }
}