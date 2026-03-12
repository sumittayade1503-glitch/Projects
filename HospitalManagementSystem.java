package hospitalManagmentSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalManagementSystem {
    // Make sure to update your MySQL password here!
    private static final String url = "jdbc:mysql://localhost:3306/hospital"; 
    private static final String username = "root";
    private static final String password = "Civil@sumit03";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Patient patient = new Patient(connection, scanner);
            Doctor doctor = new Doctor(connection);
            Appointment appointment = new Appointment(connection);

            while (true) {
                System.out.println("====== HOSPITAL MANAGEMENT SYSTEM ======");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patients");
                System.out.println("3. View Doctors");
                System.out.println("4. Book Appointment");
                System.out.println("5. View Appointments");
                System.out.println("6. Update Appointment Date");
                System.out.println("7. Cancel Appointment");
                System.out.println("8. Exit");
                System.out.println("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        patient.addPatient();
                        break;
                    case 2:
                        patient.viewPatients();
                        break;
                    case 3:
                        doctor.viewDoctors();
                        break;
                    case 4:
                        System.out.println("Enter Patient ID:");
                        int patientId = scanner.nextInt();
                        System.out.println("Enter Doctor ID:");
                        int doctorId = scanner.nextInt();
                        System.out.println("Enter Appointment Date (YYYY-MM-DD):");
                        String date = scanner.next();
                        if (patient.getPatientById(patientId) && doctor.getDoctorById(doctorId)) {
                            appointment.bookAppointment(patientId, doctorId, date);
                        } else {
                            System.out.println("Invalid Patient or Doctor ID!");
                        }
                        break;
                    case 5:
                        appointment.viewAppointments();
                        break;
                    case 6:
                        System.out.println("Enter Appointment ID to Update:");
                        int updateId = scanner.nextInt();
                        System.out.println("Enter New Date (YYYY-MM-DD):");
                        String newDate = scanner.next();
                        appointment.updateAppointmentDate(updateId, newDate);
                        break;
                    case 7:
                        System.out.println("Enter Appointment ID to Cancel:");
                        int cancelId = scanner.nextInt();
                        appointment.cancelAppointment(cancelId);
                        break;
                    case 8:
                        System.out.println("Exiting System. Goodbye!");
                        scanner.close();
                        connection.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}