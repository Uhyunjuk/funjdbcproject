package com.kh.jdbc.day01.student.controller;

import java.util.List;

import com.kh.jdbc.day01.studen.model.dao.StudentDAO;
import com.kh.jdbc.day01.studen.model.vo.Student;

public class StudentController {

	public List<Student> printAll() {
		StudentDAO sDao = new StudentDAO();
		List<Student> sList = sDao.selectAll();
		return sList;
	}

	public int registerStudent(Student student) {
		StudentDAO sDao = new StudentDAO();
		int result = sDao.insertMemeber(student);
		return result;
	}
}
