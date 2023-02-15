package com.sms.studmansys.service;

import com.sms.studmansys.dao.DAO;
import com.sms.studmansys.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements ServiceInterface<Student> {
    private DAO dao;

    @Autowired
    public StudentService(DAO dao) {
        this.dao = dao;
    }


    @Override
    public List<Student> getAllStudents() {
        return dao.list();
    }

    @Override
    public Student saveStudent(Student student) {
        return (Student) dao.create(student);
    }
}
