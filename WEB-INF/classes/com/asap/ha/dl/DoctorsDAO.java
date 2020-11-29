package com.asap.ha.dl;
import java.sql.*;
import java.util.*;
public class DoctorsDAO
{
public void add(DoctorsDTO doctors) throws DAOException
{
try
{
long contactNumber=doctors.getContactNumber();
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select doctor_name from doctors where doctor_contact_number=?");
preparedStatement.setLong(1,contactNumber);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Doctor's contact number "+contactNumber+" already exists");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into doctors (doctor_name,working_place,specialization,doctor_contact_number,about_doctor,working_hours) value (?,?,?,?,?,?),Statement.RETURN_GENERATED_KEYS");
preparedStatement.setString(1,doctors.getName());
preparedStatement.setString(2,doctors.getWorkingPlace());
preparedStatement.setString(3,doctors.getSpecialization());
preparedStatement.setLong(4,contactNumber);
preparedStatement.setString(5,doctors.getAboutDoctor());
preparedStatement.setString(6,doctors.getWorkingHours());
preparedStatement.executeQuery();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int doctorsId=resultSet.getInt(1);
doctors.setDoctorsId("D"+doctorsId);
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}


public void update(DoctorsDTO doctors) throws DAOException
{
try
{
int actualDoctorsId=0;
String doctorsId=doctors.getDoctorsId();
long contactNumber=doctors.getContactNumber();
try
{
actualDoctorsId=Integer.parseInt(doctorsId.substring(1));
}catch(Exception exception)
{
throw new DAOException("Invalid doctor's id : "+doctorsId);
}
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select doctor_name from doctors where doctor_contact_number=? and doctor_id<>?");
preparedStatement.setLong(1,contactNumber);
preparedStatement.setInt(2,actualDoctorsId);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Contact number "+contactNumber+" already exists");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select doctor_name from doctors where doctor_id=?");
preparedStatement.setInt(1,actualDoctorsId);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid doctor's id : "+doctorsId);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("update doctors set doctor_name=?,working_place=?,specialization=?,doctor_contact_number=?,about_doctor=?,working_hours=?");
preparedStatement.setString(1,doctors.getName());
preparedStatement.setString(2,doctors.getWorkingPlace());
preparedStatement.setString(3,doctors.getSpecialization());
preparedStatement.setLong(4,doctors.getContactNumber());
preparedStatement.setString(5,doctors.getAboutDoctor());
preparedStatement.setString(6,doctors.getWorkingHours());
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}


public List<DoctorsDTO> getAll() throws DAOException
{
List<DoctorsDTO> doctors;
doctors=new LinkedList<>();
try
{
Connection connection=DAOConnection.getConnection();
Statement statement;
statement=connection.createStatement();
ResultSet resultSet;
resultSet=statement.executeQuery("select * from doctors");
int doctorsId;
String name;
String workingPlace;
long contactNumber;
String aboutDoctor;
DoctorsDTO doctor;
while(resultSet.next())
{
doctorsId=resultSet.getInt("doctor_id");
name=resultSet.getString("doctor_name").trim();
workingPlace=resultSet.getString("working_place").trim();
contactNumber=resultSet.getLong("doctor_contact_number");
aboutDoctor=resultSet.getString("about_doctor").trim();
doctor=new DoctorsDTO();
doctor.setDoctorsId("D"+doctorsId);
doctor.setName(name);
doctor.setWorkingPlace(workingPlace);
doctor.setContactNumber(contactNumber);
doctor.setAboutDoctor(aboutDoctor);
doctors.add(doctor);
}
resultSet.close();
statement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return doctors;
}


public void deleteByDoctorsId(String doctorsId) throws DAOException
{
try
{
int actualDoctorsId=0;
try
{
actualDoctorsId=Integer.parseInt(doctorsId.substring(1));
}catch(Exception exception)
{
throw new DAOException("Invalid doctor's id : "+doctorsId);
}
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select doctor_name from doctors where doctor_id=?");
preparedStatement.setInt(1,actualDoctorsId);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid doctor's id : "+doctorsId);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("delete from doctors where doctor_id=?");
preparedStatement.setInt(1,actualDoctorsId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}


public DoctorsDTO getByDoctorsId(String doctorsId) throws DAOException
{
DoctorsDTO doctorDTO=null;
try
{
int actualDoctorsId=0;
try
{
actualDoctorsId=Integer.parseInt(doctorsId.substring(1));
}catch(Exception exception)
{
throw new DAOException("Invalid doctor's id : "+doctorsId);
}
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select doctor_name,working_place,specialization,doctor_contact_number,about_doctor,working_hours from doctors where doctor_id=?");
preparedStatement.setInt(1,actualDoctorsId);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid doctor's id : "+doctorsId);
}
int doctorId;
String name;
String workingPlace;
String specialization;
long contactNumber;
String aboutDoctor;
String workingHours;
doctorId=resultSet.getInt("doctor_id");
name=resultSet.getString("name").trim();
workingPlace=resultSet.getString("working_place").trim();
specialization=resultSet.getString("specialization").trim();
contactNumber=resultSet.getLong("doctor_contact_number");
aboutDoctor=resultSet.getString("working_place");
workingHours=resultSet.getString("working_place").trim();
doctorDTO=new DoctorsDTO();
doctorDTO.setDoctorsId("D"+doctorId);
doctorDTO.setName(name);
doctorDTO.setWorkingPlace(workingPlace);
doctorDTO.setSpecialization(specialization);
doctorDTO.setContactNumber(contactNumber);
doctorDTO.setAboutDoctor(aboutDoctor);
doctorDTO.setWorkingHours(workingHours);
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
return doctorDTO;
}


public boolean doctorsContactNumberExists(long contactNumber) throws DAOException
{
boolean exists=false;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select doctor_name from doctors where doctor_contact_number=?");
preparedStatement.setLong(1,contactNumber);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
exists=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return exists;
}


public boolean doctorsIdExists(String doctorsId) throws DAOException
{
boolean exists=false;
try
{
int actualDoctorsId=0;
try
{
actualDoctorsId=Integer.parseInt(doctorsId.substring(1));
}catch(Exception exception)
{
throw new DAOException("Invalid doctor's id : "+doctorsId);
}
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select doctor_name from doctors where doctor_id=?");
preparedStatement.setInt(1,actualDoctorsId);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
exists=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return exists;
}


}