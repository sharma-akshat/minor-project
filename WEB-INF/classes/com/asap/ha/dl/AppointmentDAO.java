package com.asap.ha.dl;
import java.sql.*;
import java.util.*;
public class AppointmentDAO
{
public void add(AppointmentDTO appointmentDTO) throws DAOException
{
try
{
String address=appointmentDTO.getAddress().trim();
long contactNumber=appointmentDTO.getContactNumber();
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select patient_id from appointment where patient_contact_number=?");
preparedStatement.setLong(1,contactNumber);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Patient's contact number : "+contactNumber+" already exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select patient_id from appointment where patient_address=?");
preparedStatement.setString(1,address);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Patient Address : "+address+" already exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into appointment (patient_name,patient_age,gender,patient_contact_number,appointment_date,patient_address,is_covid_patient,any_disease) value (?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,appointmentDTO.getName());
preparedStatement.setInt(2,appointmentDTO.getAge());
preparedStatement.setString(3,appointmentDTO.getGender());
preparedStatement.setLong(4,contactNumber);
java.util.Date date=appointmentDTO.getAppointmentDate();
java.sql.Date sqlDate=new java.sql.Date(date.getYear(),date.getMonth(),date.getDate());
preparedStatement.setDate(5,sqlDate);
preparedStatement.setString(6,address.trim());
preparedStatement.setBoolean(7,appointmentDTO.getIsCovidPatient());
preparedStatement.setString(8,appointmentDTO.getAnyDisease());
preparedStatement.executeQuery();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int patientId=resultSet.getInt(1);
appointmentDTO.setPatientId("A"+patientId);
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
}