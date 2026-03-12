package hospitalManagmentSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
private Connection connection;
private Scanner scanner;
public Patient(Connection connection, Scanner scanner)
{
	this.connection= connection;
	this.scanner= scanner;
}
	public void addPatient() {
		System.out.println("Enter Patient Name: ");
		String name= scanner.next();
		System.out.println("Enter patient Age: ");
		int age =  scanner.nextInt();
		System.out.println("Enter Patient Gender: ");
		String gender= scanner.next();
		System.out.println("Enter Patient Mobile Number: ");
		String mobile_number= scanner.next();
		try {
			String query="INSERT INTO patients(name,age,gender,mobile_number) VALUES(?,?,?,?)";
			PreparedStatement preparedStatment =connection.prepareStatement(query);
			preparedStatment.setString(1, name);
			preparedStatment.setInt(2, age);
			preparedStatment.setString(3, gender);
			preparedStatment.setString(4, mobile_number);
            int affectedRows =  preparedStatment.executeUpdate();			 
			if(affectedRows>0)
			{
				System.out.println("Patient Data Apdated Sucessfuly!!");
			}
			else
			{
				System.out.println("Failed to add Patient!!");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
	}
	
	public void viewPatients(){
		String query = " select *  from patients";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet= preparedStatement.executeQuery();
			System.out.println("Patients: ");
			System.out.println("+------------+--------------------+------+---------------+----------------------+");
			System.out.println("| PATIENT ID | NAME               | AGE  | GENDER        | MOBILE NUMBER        |");
			System.out.println("+------------+--------------------+------+---------------+----------------------+");
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name  = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				String mobile_number= resultSet.getString("mobile_number");
				System.out.printf("| %-10d | %-18s | %-4d | %-14s| %-20s |\n",id,name,age,gender,mobile_number);
				System.out.println("+------------+--------------------+------+---------------+----------------------+");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean getPatientById(int id) {
		String query="select * from patients where id = ?";
		try {
			PreparedStatement preparedStatement= connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet= preparedStatement.executeQuery();
			if(resultSet.next())
				return true;
			else
				return false;
		    
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}


 

