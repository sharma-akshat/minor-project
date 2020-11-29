package com.asap.ha.beans;
public class DoctorBean implements java.io.Serializable
{
private String doctorId;
private String name;
private String workingPlace;
private String specialization;
private long contactNumber;
private String aboutDoctor;
private String workingHours;
public DoctorBean()
{
this.doctorId="";
this.name="";
this.workingPlace="";
this.specialization="";
this.contactNumber=0;
this.aboutDoctor="";
this.workingHours="";
}
public void setDoctorId(String doctorId)
{
this.doctorId=doctorId;
}
public String getDoctorId()
{
return this.doctorId;
}
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
public void setWorkingPlace(String workingPlace)
{
this.workingPlace=workingPlace;
}
public String getWorkingPlace()
{
return this.workingPlace;
}
public void setSpecialization(String specialization)
{
this.specialization=specialization;
}
public String getSpecialization()
{
return this.specialization;
}
public void setContactNumber(long contactNumber)
{
this.contactNumber=contactNumber;
}
public long getContactNumber()
{
return this.contactNumber;
}
public void setAboutDoctor(String aboutDoctor)
{
this.aboutDoctor=aboutDoctor;
}
public String getAboutDoctor()
{
return this.aboutDoctor;
}
public void setWorkingHours(String workingHours)
{
this.workingHours=workingHours;
}
public String getWorkingHours()
{
return this.workingHours;
}
}