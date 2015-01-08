package ca.csf.exercice01;

import java.util.List;

public interface StudentDAO {

	public List<Student> getAllStudents() throws RuntimeException;

}