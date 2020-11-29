package com.asap.ha.dl;
import java.util.*;
public class AppointmentDTO implements java.io.Serializable,Comparable<AppointmentDTO>
{
private String patientId;
private String name;
private int age;
private String gender;
private long contactNumber;
private Date appointmentDate;
private String address;
private boolean isCovidPatient;
private String anyDisease;
public AppointmentDTO()
{
this.patientId="";
this.name="";
this.age=0;
this.gender="";
this.contactNumber=0;
this.appointmentDate=null;
this.address="";
this.isCovidPatient=false;
this.anyDisease="";
}
public void setPatientId(String patientId)
{
this.patientId=patientId;
}
public String getPatientId()
{
return this.patientId;
}
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
public void setAge(int age)
{
this.age=age;
}
public int getAge()
{
return this.age;
}
public void setGender(String gender)
{
this.gender=gender;
}
public String getGender()
{
return this.gender;
}
public void setContactNumber(long contactNumber)
{
this.contactNumber=contactNumber;
}
public long getContactNumber()
{
return this.contactNumber;
}
public void setDate(java.util.Date appointmentDate)
{
this.appointmentDate=appointmentDate;
}
public java.util.Date getAppointmentDate()
{
return this.appointmentDate;
}
public void setAddress(String address)
{
this.address=address;
}
public String getAddress()
{
return this.address;
}
public void setIsCovidPatient(boolean isCovidPatient)
{
this.isCovidPatient=isCovidPatient;
}
public boolean getIsCovidPatient()
{
return this.isCovidPatient;
}
public void setAnyDisease(String anyDisease)
{
this.anyDisease=anyDisease;
}
public String getAnyDisease()
{
return this.anyDisease;
}
public boolean equals(Object object)
{
if(!(object instanceof AppointmentDTO)) return false;
AppointmentDTO appointment=(AppointmentDTO)object;
return this.patientId.equalsIgnoreCase(appointment.patientId);
}
public int compareTo(AppointmentDTO appointment)
{
return this.patientId.compareTo(appointment.patientId);
}
}