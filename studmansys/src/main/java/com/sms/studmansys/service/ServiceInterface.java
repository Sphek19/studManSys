package com.sms.studmansys.service;

import com.sms.studmansys.model.Student;

import java.util.List;

public interface ServiceInterface<T> {
    List<T> getAllStudents();
    T saveStudent(T t);
}
