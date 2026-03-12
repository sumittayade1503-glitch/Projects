package hospitalManagmentSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Appointment extends DatabaseEntity {

    public Appointment(Connection connection) {
        super(connection); 
    }

    public void bookAppointment(int patientId, int doctorId, String date) {
        String query = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES(?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, patientId);
            ps.setInt(2, doctorId);
            ps.setString(3, date);
            if (ps.executeUpdate() > 0) {
                System.out.println("Appointment Booked Successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewAppointments() {
        String query = "SELECT a.id, p.name AS patient_name, d.name AS doctor_name, a.appointment_date " +
                       "FROM appointments a " +
                       "INNER JOIN patients p ON a.patient_id = p.id " +
                       "INNER JOIN doctors d ON a.doctor_id = d.id";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            System.out.println("--- Appointment List ---");
            while (rs.next()) {
                System.out.printf("Appt ID: %d | Patient: %s | Doctor: %s | Date: %s\n",
                        rs.getInt("id"), 
                        rs.getString("patient_name"),
                        rs.getString("doctor_name"), 
                        rs.getString("appointment_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAppointmentDate(int appointmentId, String newDate) {
        String query = "UPDATE appointments SET appointment_date = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, newDate);
            ps.setInt(2, appointmentId);
            if (ps.executeUpdate() > 0) {
                System.out.println("Appointment Date Updated!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelAppointment(int appointmentId) {
        String query = "DELETE FROM appointments WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, appointmentId);
            if (ps.executeUpdate() > 0) {
                System.out.println("Appointment Cancelled!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}