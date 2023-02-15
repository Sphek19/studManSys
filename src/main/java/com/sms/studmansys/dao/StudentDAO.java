package com.sms.studmansys.dao;

import com.sms.studmansys.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentDAO implements DAO<Student> {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Student> rowMapper = (rs, rowNum) -> {
        Student student = new Student();
        student.setStu_id(rs.getLong("stud_id"));
        student.setFirstName(rs.getString("first_name"));
        student.setSurname(rs.getString("surname"));
        student.setEmail(rs.getString("email"));

        return student;
    };

    public StudentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> list() {
        String sql = "SELECT * FROM stud_table ORDER BY stud_id desc";
        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public Optional<Student> get(int id) {
        return Optional.empty();
    }

    @Override
    public Student create(Student student) {
        String sql = "INSERT INTO stud_table(first_name, surname, email) values (?, ?, ?)";
        int rowInsert = jdbcTemplate.update(sql, student.getFirstName(), student.getSurname(), student.getEmail());

        // Checking if row/course was inserted successfully into the database.
        if(rowInsert == 1) {
            System.out.println("Row with student name : " + student.getFirstName() + " was inserted.");
        }
        return student;
    }

    @Override
    public void update(Student student, int id) {
        String sql = "update stud_table set first_name = ?, surname = ?, email = ? where stud_id = ?";
        int update = jdbcTemplate.update(sql,student.getFirstName(),student.getSurname(),student.getEmail(),id);
        if(update == 1) {
            System.out.println("Student Info Updated: " + student.getStu_id());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from stud_table where stud_id = ?";
        int delete = jdbcTemplate.update(sql,id);
        if(delete == 1) {
            System.out.println("Student Deleted: " + id);
        }
    }
}
