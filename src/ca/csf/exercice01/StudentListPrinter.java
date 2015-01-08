package ca.csf.exercice01;

import java.util.List;

public class StudentListPrinter {

	private StudentDAO studentDAO;
	private DocumentFactory documentFactory;
	private Printer printer;
	
	public StudentListPrinter(StudentDAO studentDAO, DocumentFactory documentFactory, Printer printer) {
		this.studentDAO = studentDAO;
		this.documentFactory = documentFactory;
		this.printer = printer;
	}
	
	public void printAllStudents() {
		List<Student> students = studentDAO.getAllStudents();
		Document document = documentFactory.createDocument();
		for(Student student : students) {
			document.append(String.format("ID : %d, Name : %s, Age : %d, Country : %s", student.id, student.name, student.age, student.country));
		}
		printer.print(document);
	}
	
}
