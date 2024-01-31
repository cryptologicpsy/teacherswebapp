package com.example.teachersapp.service.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TeacherIdAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TeacherIdAlreadyExistsException(String message) {
        super(message);
    }

    // Προσθήκη ενός επιπλέον κατασκευαστή για ακέραιους
    public TeacherIdAlreadyExistsException(int teacherId) {
        super("Teacher with id = " + teacherId + " already exists");
    }
}