package com.asap.ha.dl;
import java.util.*;
import java.sql.*;
public class DonateBloodDAO
{
public void add(DonateBloodDTO donateBloodDTO) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("insert into donate_blood (name,age,gender,address,blood_group,date_of_birth,medical_history,about_medical_history) value (?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,donateBloodDTO.getName());
preparedStatement.setInt(2,donateBloodDTO.getAge());
preparedStatement.setString(3,donateBloodDTO.getGender());
preparedStatement.setString(4,donateBloodDTO.getAddress());
preparedStatement.setString(5,donateBloodDTO.getBloodGroup());
java.util.Date dateOfBirth=donateBloodDTO.getDateOfBirth();
java.sql.Date sqlDate=new java.sql.Date(dateOfBirth.getYear(),dateOfBirth.getMonth(),dateOfBirth.getDate());
preparedStatement.setDate(6,sqlDate);
preparedStatement.setBoolean(7,donateBloodDTO.getMedicalHistory());
preparedStatement.setString(8,donateBloodDTO.getAboutMedicalHistory());
preparedStatement.executeQuery();
ResultSet resultSet;
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int patientId=resultSet.getInt(1);
donateBloodDTO.setDonorId("DB"+donateBloodDTO.getDonorId());
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}

}