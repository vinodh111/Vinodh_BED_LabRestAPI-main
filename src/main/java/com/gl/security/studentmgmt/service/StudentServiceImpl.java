package com.gl.security.studentmgmt.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gl.security.studentmgmt.entity.Student;
import com.gl.security.studentmgmt.repository.StudentRepository;

@Repository
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentRepository studentRepository;
	
	@Override
	@Transactional
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		List <Student> students =  studentRepository.findAll();
		return students;
	}

	@Override
	public Student findById(int id) {
		// TODO Auto-generated method stub
		return studentRepository.findById(id).get();
	}

	@Override
	public void save(Student student) {
		// TODO Auto-generated method stub
		studentRepository.save(student);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		studentRepository.deleteById(id);
	}

}

