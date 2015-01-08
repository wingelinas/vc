package ca.csf.exercice01;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class StudentListPrinterTest {
	
	@Mock
	private StudentDAO studentDAO;
	@Mock
	private DocumentFactory documentFactory;
	@Mock
	private Printer printer;

	
	private StudentListPrinter studentListPrinter;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		studentListPrinter = new StudentListPrinter(studentDAO, documentFactory, printer);
	}
	
	@Test
	public void givenEmptyStudentList_whenPrintStudentList_thenEmptyDocumentIsPrinted() {
		List<Student> studentList = new ArrayList<Student>();
		Document document = mock(Document.class);
		
		when(documentFactory.createDocument()).thenReturn(document);
		when(studentDAO.getAllStudents()).thenReturn(studentList);
		
		studentListPrinter.printAllStudents();
		
		verify(documentFactory).createDocument();
		verify(document, times(0)).append(anyString());		
		verify(printer).print(document);
	}
	
	@Test
	public void givenStudentList_whenPrintStudentList_thenStudentListIsPrinted() {
		List<Student> studentList = studentList();
		Document document = mock(Document.class);
		
		when(documentFactory.createDocument()).thenReturn(document);
		when(studentDAO.getAllStudents()).thenReturn(studentList);
		
		studentListPrinter.printAllStudents();
		
		verify(documentFactory).createDocument();
		for(Student student : studentList) {
			verify(document).append("ID : " + student.id + 
			                        ", Name : " + student.name + 
			                        ", Age : " + student.age + 
			                        ", Country : " + student.country);		
		}
		verify(printer).print(document);
	}
	
	@Test
	public void givenStudentInformations_whenPrintStudent_thenStudentInformationtIsPrintedCorrectly() {
		List<Student> studentList = new ArrayList<Student>();
		Student student1 = new Student();
		student1.id = 1;
		student1.name = "John Smith";
		student1.age = 18;
		student1.country = "Canada";
		studentList.add(student1);
		Document document = mock(Document.class);
		
		when(documentFactory.createDocument()).thenReturn(document);
		when(studentDAO.getAllStudents()).thenReturn(studentList);
		
		studentListPrinter.printAllStudents();
		
		verify(documentFactory).createDocument();
		verify(document).append("ID : 1, Name : John Smith, Age : 18, Country : Canada");		
		verify(printer).print(document);
	}
	
	@Test
	public void givenEmptyStudentInformations_whenPrintStudent_thenStudentInformationtIsPrintedCorrectly() {
		List<Student> studentList = new ArrayList<Student>();
		Student student = new Student();
		student.id = 0;
		student.name = null;
		student.age = 0;
		student.country = null;
		studentList.add(student);
		Document document = mock(Document.class);
		
		when(documentFactory.createDocument()).thenReturn(document);
		when(studentDAO.getAllStudents()).thenReturn(studentList);
		
		studentListPrinter.printAllStudents();
		
		verify(documentFactory).createDocument();
		verify(document).append("ID : 0, Name : null, Age : 0, Country : null");		
		verify(printer).print(document);
	}
	
	private List<Student> studentList() {
		List<Student> students = new ArrayList<Student>();
		Student student1 = new Student();
		student1.id = 1;
		student1.name = "Bob The Builder";
		student1.age = -1;
		student1.country = "Murica";
		Student student2 = new Student();
		student2.id = 2;
		student2.name = "Alain Therieur";
		student2.age = 15;
		student2.country = "Canada";
		Student student3 = new Student();
		student3.id = 3;
		student3.name = "Alex Therieur";
		student3.age = 16;
		student3.country = "Australie";
		
		students.add(student1);
		students.add(student2);
		students.add(student3);
		
		return students;
	}

}
