package ca.csf.exercice01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySQLStudentDAO implements StudentDAO {

		private static final String STUDENTS_ID = "ID";
		private static final String STUDENTS_NAME = "NAME";
		private static final String STUDENTS_AGE = "AGE";
		private static final String STUDENTS_COUNTRY = "COUNTRY";
		private static final String SELECT_ALL_STUDENTS = "SELECT " + STUDENTS_ID + ", " 
																	+ STUDENTS_NAME + ", "
																	+ STUDENTS_AGE + ", " 
																	+ STUDENTS_COUNTRY + " FROM STUDENTS;";
		private String databaseURL;
		private String databaseAdminUserName;
		private String databaseAdminUserPassword;
		
		public MySQLStudentDAO(String databaseURL, String databaseAdminUserName, String databaseAdminUserPassword) {
			this.databaseURL = databaseURL;
			this.databaseAdminUserName = databaseAdminUserName;
			this.databaseAdminUserPassword = databaseAdminUserPassword;
		}

		public List<Student> getAllStudents() throws RuntimeException {
			Connection connection = null;
			try {

				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://" + databaseURL , databaseAdminUserName, databaseAdminUserPassword);

			
    			List<Student> students = new ArrayList<Student>();
    			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);
    			ResultSet resultSet = preparedStatement.executeQuery();
    
    			while (resultSet.next()) {
    				Student student = new Student();
    				student.id = resultSet.getInt(STUDENTS_ID);
    				student.name = resultSet.getString(STUDENTS_NAME);
    				student.age = resultSet.getInt(STUDENTS_AGE);
    				student.country = resultSet.getString(STUDENTS_COUNTRY);
    				students.add(student);
    			}
    			return students;
    			
			} catch (Exception ex) {
				throw new RuntimeException("Cannot read from the Database. Please contact the DBA for help.", ex);
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}