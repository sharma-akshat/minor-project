package com.asap.ha.dl;
import java.sql.*;
import java.util.*;
public class PharmacyDAO
{
public void add(PharmacyDTO pharmacyDTO) throws DAOException
{
try
{
String name=pharmacyDTO.getName().trim();
String address=pharmacyDTO.getAddress().trim();
long contactNumber=pharmacyDTO.getContactNumber();
String aboutPharmacy=pharmacyDTO.getAboutPharmacy().trim();
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select pharmacy_id from pharmacies where pharmacy_name=?");
preparedStatement.setString(1,name);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Pharmacy name "+name+" already exists");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select pharmacy_id from pharmacies where pharmacy_address=?");
preparedStatement.setString(1,address);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Pharmacy address : "+address+" already exists");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select pharmacy_id from pharmacies where pharmacy_contact_number=?");
preparedStatement.setLong(1,contactNumber);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Pharmacy contact number : "+contactNumber+" already exists");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into pharmacies (pharmacy_name,pharmacy_address,pharmacy_contact_number,about_pharmacy) values (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,name);
preparedStatement.setString(2,address);
preparedStatement.setLong(3,contactNumber);
preparedStatement.setString(4,aboutPharmacy);
preparedStatement.executeQuery();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int pharmacyId=resultSet.getInt(1);
pharmacyDTO.setPharmacyId("P"+pharmacyId);
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}


public void update(PharmacyDTO pharmacyDTO) throws DAOException
{
try
{
String pharmacyId=pharmacyDTO.getPharmacyId();
if(pharmacyId.trim()==null) throw new DAOException("Invalid pharmacy id : "+pharmacyId);
int actualPharmacyId=Integer.parseInt(pharmacyId.substring(1));
String name=pharmacyDTO.getName().trim();
String address=pharmacyDTO.getAddress().trim();
long contactNumber=pharmacyDTO.getContactNumber();
String aboutPharmacy=pharmacyDTO.getAboutPharmacy().trim();
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select name from pharmacies where pharmacy_id=?");
preparedStatement.setInt(1,actualPharmacyId);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("PharmacyId does not exists : "+pharmacyId);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select pharmacy_id from pharmacies where pharmacy_name=? and pharmacy_id=?");
preparedStatement.setString(1,name);
preparedStatement.setInt(2,actualPharmacyId);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Pharmacy name : "+name+" already exists");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select pharmacy_id from pharmacies where pharmacy_address=? and pharmacy_id=?");
preparedStatement.setString(1,address);
preparedStatement.setInt(2,actualPharmacyId);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Pharmacy address : "+address+" already exists");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select pharmacy_id from pharmacies where pharmacy_contact_number=? and pharmacy_id=?");
preparedStatement.setLong(1,contactNumber);
preparedStatement.setInt(2,actualPharmacyId);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Pharmacy contact number : "+contactNumber+" already exists");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("update pharmacies set pharmacy_name=?,pharmacy_address=?,pharmacy_contact_number=?,about_pharmacy=? where pharmacy_id=?");
preparedStatement.setString(1,name);
preparedStatement.setString(2,address);
preparedStatement.setLong(3,contactNumber);
preparedStatement.setString(4,aboutPharmacy);
preparedStatement.setInt(5,actualPharmacyId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}


public void deleteByPharmacyId(String pharmacyId) throws DAOException
{
try
{
if(pharmacyId.trim()==null) throw new DAOException("Invalid pharmacy id : "+pharmacyId);
int actualPharmacyId=Integer.parseInt(pharmacyId.substring(1));
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select name from pharmacies where pharmacy_id=?");
preparedStatement.setInt(1,actualPharmacyId);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Pharmacy Id does not exists : "+pharmacyId);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("delete from pharmacies where pharmacy_id=?");
preparedStatement.setInt(1,actualPharmacyId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}


public PharmacyDTO getByPharmacyId(String pharmacyId) throws DAOException
{
if(pharmacyId.trim()==null) throw new DAOException("Invalid pharmacy id : "+pharmacyId);
int actualPharmacyId=Integer.parseInt(pharmacyId.substring(1));
Connection connection;
connection=DAOConnection.getConnection();
PharmacyDTO pharmacyDTO;
try
{
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from pharmacies where pharmacy_id");
preparedStatement.setInt(1,actualPharmacyId);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Pharmacy Id does not exists : "+pharmacyId);
}
pharmacyDTO=new PharmacyDTO();
String name=resultSet.getString("pharmacy_name").trim();
String address=resultSet.getString("pharmacy_address").trim();
Long contactNumber=resultSet.getLong("pharmacy_contact_number");
String aboutHospital=resultSet.getString("about_pharmacy").trim();
pharmacyDTO.setName(name);
pharmacyDTO.setAddress(address);
pharmacyDTO.setContactNumber(contactNumber);
pharmacyDTO.setAboutPharmacy(aboutHospital);
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
return pharmacyDTO;
}

public List<PharmacyDTO> getAll() throws DAOException
{
List<PharmacyDTO> pharmacies;
pharmacies=new LinkedList<>();
try
{
Connection connection=DAOConnection.getConnection();
Statement statement;
statement=connection.createStatement();
ResultSet resultSet;
resultSet=statement.executeQuery("select * from pharmacies");
PharmacyDTO pharmacyDTO;
while(resultSet.next())
{
int pharmacyId=resultSet.getInt("pharmacy_id");
String name=resultSet.getString("pharmacy_name").trim();
String address=resultSet.getString("pharmacy_address").trim();
Long contactNumber=resultSet.getLong("pharmacy_contact_number");
String aboutPharmacy=resultSet.getString("about_pharmacy").trim();
pharmacyDTO=new PharmacyDTO();
pharmacyDTO.setPharmacyId("P"+pharmacyId);
pharmacyDTO.setName(name);
pharmacyDTO.setAddress(address);
pharmacyDTO.setContactNumber(contactNumber);
pharmacyDTO.setAboutPharmacy(aboutPharmacy);
pharmacies.add(pharmacyDTO);
}
resultSet.close();
statement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return pharmacies;
}


public boolean pharmacyIdExists(String pharmacyId) throws DAOException
{
boolean exists=false;
try
{
if(pharmacyId.trim()==null) throw new DAOException("Invalid pharmacy id : "+pharmacyId);
int actualPharmacyId=Integer.parseInt(pharmacyId.substring(1));
Connection connection;
connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select name from pharmacies where pharmacy_id=?");
preparedStatement.setInt(1,actualPharmacyId);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
exists=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
return exists;
}

public boolean contactNumberExists(long contactNumber) throws DAOException
{
boolean exists=false;
PharmacyDTO pharmacy=new PharmacyDTO();
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select name from pharmacies where pharmacy_contact_name=?");
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
