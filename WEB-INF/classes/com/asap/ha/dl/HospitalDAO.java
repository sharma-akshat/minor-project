package com.asap.ha.dl;
import java.sql.*;
import java.util.*;
public class HospitalDAO
{
public void add(HospitalDTO hospitalDTO) throws DAOException
{
try
{
String name=hospitalDTO.getName().trim();
String address=hospitalDTO.getAddress().trim();
long contactNumber=hospitalDTO.getContactNumber();
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select hospital_id from hospitals where hospital_name=?");
preparedStatement.setString(1,name);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Hospital Name : "+name+" already exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select hospital_id from hospitals where hospital_address=?");
preparedStatement.setString(1,address);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Hospital Address : "+address+" already exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select hospital_id from hospitals where hospital_contact_number=?");
preparedStatement.setLong(1,contactNumber);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Hospital Contact Number : "+contactNumber+" already exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into hospitals (hospital_name,hospital_address,hopsital_contact_number,total_beds,is_covid_patient,category,about_hospital) value (?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,name);
preparedStatement.setString(2,address);
preparedStatement.setLong(3,contactNumber);
preparedStatement.setInt(4,hospitalDTO.getTotalBeds());
preparedStatement.setBoolean(5,hospitalDTO.getIsCovidPatient());
preparedStatement.setString(6,hospitalDTO.getCategory().trim());
preparedStatement.setString(7,hospitalDTO.getAboutHospital().trim());
preparedStatement.executeQuery();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int hospitalId=resultSet.getInt(1);
hospitalDTO.setHospitalId("H"+hospitalId);
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}

public void update(HospitalDTO hospitalDTO) throws DAOException
{
try
{
String hospitalId=hospitalDTO.getHospitalId();
if(hospitalId.trim()==null) throw new DAOException("Hospital Id should not be null");
int actualHospitalId=Integer.parseInt(hospitalId.substring(1));
String name=hospitalDTO.getName().trim();
String address=hospitalDTO.getAddress().trim();
long contactNumber=hospitalDTO.getContactNumber();
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select category from hospitals where hospital_id=?");
preparedStatement.setInt(1,actualHospitalId);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid hospital id : "+hospitalId);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select hopsital_id from hospitals where hospital_name=? and hospital_id=?");
preparedStatement.setString(1,name);
preparedStatement.setInt(2,actualHospitalId);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Hospital name : "+name+" already exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select hopsital_id from hospitals where hospital_address=? and hospital_id=?");
preparedStatement.setString(1,address);
preparedStatement.setInt(2,actualHospitalId);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Hospital address : "+address+" already exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select hopsital_id from hospitals where hospital_contact_number=? and hospital_id=?");
preparedStatement.setLong(1,contactNumber);
preparedStatement.setInt(2,actualHospitalId);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Hospital contact number : "+contactNumber+" already exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("update hospitals set hospital_name=?,hospital_address=?,hopsital_contact_number=?,total_beds=?,is_covid_patient=?,category=?,about_hospital=? where hopsital_id=?");
preparedStatement.setString(1,name);
preparedStatement.setString(2,address);
preparedStatement.setLong(3,contactNumber);
preparedStatement.setInt(4,hospitalDTO.getTotalBeds());
preparedStatement.setBoolean(5,hospitalDTO.getIsCovidPatient());
preparedStatement.setString(6,hospitalDTO.getCategory().trim());
preparedStatement.setString(7,hospitalDTO.getAboutHospital().trim());
preparedStatement.setInt(8,actualHospitalId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}

public void deleteByHospitalId(String hospitalId) throws DAOException
{
try
{
if(hospitalId.trim()==null) throw new DAOException("Hospital Id should not be null");
int actualHospitalId=Integer.parseInt(hospitalId.substring(1));
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select category from hospitals where hospital_id=?");
preparedStatement.setInt(1,actualHospitalId);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid hospital id : "+hospitalId);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("delete from hospitals where hospital_id=?");
preparedStatement.setInt(1,actualHospitalId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}

public HospitalDTO getByHospitalId(String hospitalId) throws DAOException
{
if(hospitalId.trim()==null)throw new DAOException("Hospital Id should not be null");
int actualHospitalId=Integer.parseInt(hospitalId.substring(1));
Connection connection;
connection=DAOConnection.getConnection();
HospitalDTO hospitalDTO;
try
{
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select hospitals.hospital_id,hospitals.hospital_name,hospitals.hospital_address,hospitals.hospital_contact_number,link.facilities,hospitals.total_beds,hospitals.is_covid_patient,hospitals.category,hospitals.about_hospital from hospitals inner join link on hospitals.hospital_id=link.hospital_code and hospital_id=?");
preparedStatement.setInt(1,actualHospitalId);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Hospital Id does not exists : "+hospitalId);
}
hospitalDTO=new HospitalDTO();
String name=resultSet.getString("hospital_name").trim();
hospitalDTO.setName(name);
String address=resultSet.getString("hospital_address").trim();
hospitalDTO.setAddress(address);
Long contactNumber=resultSet.getLong("hospital_contact_number");
hospitalDTO.setContactNumber(contactNumber);
while(resultSet.next())
{
String facilities=resultSet.getString("facilities").trim();
hospitalDTO.setFacilities(facilities);
}
int totalBeds=resultSet.getInt("total_beds");
hospitalDTO.setTotalBeds(totalBeds);
Boolean isCovidPatient=resultSet.getBoolean("is_covid_patient");
hospitalDTO.setIsCovidPatient(isCovidPatient);
String category=resultSet.getString("category").trim();
hospitalDTO.setCategory(category);
String aboutHospital=resultSet.getString("about_hospital").trim();
hospitalDTO.setAboutHospital(aboutHospital);
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
return hospitalDTO;
}

public List<HospitalDTO> getAll() throws DAOException
{
List<HospitalDTO> hospitals;
try
{
hospitals=new LinkedList<>();
Connection connection=DAOConnection.getConnection();
Statement statement=connection.createStatement();
ResultSet resultSet;
resultSet=statement.executeQuery("select hospitals.hospital_id,hospitals.hospital_name,hospitals.hospital_address,hospitals.hospital_contact_number,link.facilities,hospitals.total_beds,hospitals.is_covid_patient,hospitals.category,hospitals.about_hospital from hospitals inner join link on hospitals.hospital_id=link.hospital_code order by hospital_name");
while(resultSet.next())
{
int hospitalId=resultSet.getInt("hospital_id");
String name=resultSet.getString("hospital_name").trim();
String address=resultSet.getString("hospital_address").trim();
Long contactNumber=resultSet.getLong("hospital_contact_number");
String facilities=resultSet.getString("facilities").trim();
int totalBeds=resultSet.getInt("total_beds");
Boolean isCovidPatient=resultSet.getBoolean("is_covid_patient");
String category=resultSet.getString("category").trim();
String aboutHospital=resultSet.getString("about_hospital").trim();
HospitalDTO hospitalDTO=new HospitalDTO();
hospitalDTO.setHospitalId("H"+hospitalId);
hospitalDTO.setName(name);
hospitalDTO.setAddress(address);
hospitalDTO.setContactNumber(contactNumber);
hospitalDTO.setFacilities(facilities);
hospitalDTO.setTotalBeds(totalBeds);
hospitalDTO.setIsCovidPatient(isCovidPatient);
hospitalDTO.setCategory(category);
hospitalDTO.setAboutHospital(aboutHospital);
hospitals.add(hospitalDTO);
}
resultSet.close();
statement.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
return hospitals;
}

public boolean hospitalIdExists(String hospitalId) throws DAOException
{
if(hospitalId.trim()==null) throw new DAOException("Hospital Id should not be null");
boolean exists=false;
int actualHospitalId=Integer.parseInt(hospitalId.substring(1));
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select category from hospitals where hospital_id=?");
preparedStatement.setInt(1,actualHospitalId);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
exists=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
return exists;
}

public boolean hospitalAddressExists(String address) throws DAOException
{
if(address.trim()==null) throw new DAOException("Hospital Address should not be null");
boolean exists=false;
HospitalDTO hospital=new HospitalDTO();
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select category from hospital where hospital_address=?");
preparedStatement.setString(1,address);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
exists=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
return exists;
}

public boolean contactNumberExists(long contactNumber) throws DAOException
{
if(contactNumber==0) throw new DAOException("Hospital contact number should not be 0");
boolean exists=false;
HospitalDTO hospital=new HospitalDTO();
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select category from hospitals where hospital_contact_number=?");
preparedStatement.setLong(1,contactNumber);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
exists=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
return exists;
}
}