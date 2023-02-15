package com.sms.studmansys.controller;

import com.sms.studmansys.model.Student;
import com.sms.studmansys.service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {
    private final ServiceInterface serviceInterface;

    @Autowired
    public StudentController(ServiceInterface serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    /**
     * This is the controller method that is responsible for displaying all the students in the
     * database.
     * @param model Is used to store and transfer objects between the controller and the view.
     * @return A String with the name of the HTML page(view)
     */
    @GetMapping(value = "/students")
    public String getAllStudents(Model model) {
        model.addAttribute("students", serviceInterface.getAllStudents());
        return "students";
    }

    /**
     * This brings up the form which is used to collect information about a new student to be added
     * to the database.
     * @param model Is used to store and transfer objects between the controller and the view.
     * @return A String with the name of the HTML page(view)
     */
    @GetMapping(value = "/students/new")
    public String createStudentForm(Model model) {
        // To hold student form data
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";
    }

    @PostMapping(value="/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        serviceInterface.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping(value = "/delete")
    @ResponseBody
    public String delete() {
        return "TO BE IMPLEMENTED";
    }

}
