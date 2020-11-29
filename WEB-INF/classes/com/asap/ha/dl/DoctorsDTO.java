package com.asap.ha.dl;
import java.util.*;
public class DoctorsDTO implements java.io.Serializable,Comparable<DoctorsDTO>
{
private String doctorsId;
private String name;
private String workingPlace;
private String workingHours;
private String specialization;
private String aboutDoctor;
private long contactNumber;
public DoctorsDTO()
{
this.doctorsId="";
this.name="";
this.workingPlace="";
this.workingHours="";
this.specialization="";
this.aboutDoctor="";
this.contactNumber=0;
}
public void setDoctorsId(String doctorsId)
{
this.doctorsId=doctorsId;
}
public String getDoctorsId()
{
return this.doctorsId;
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
public void setAboutDoctor(String aboutDoctor)
{
this.aboutDoctor=aboutDoctor;
}
public String getAboutDoctor()
{
return this.aboutDoctor;
}
public void setContactNumber(long contactNumber)
{
this.contactNumber=contactNumber;
}
public long getContactNumber()
{
return this.contactNumber;
}
public void setWorkingHours(String workingHours)
{
this.workingHours=workingHours;
}
public String getWorkingHours()
{
return this.workingHours;
}
public boolean equals(Object object)
{
if(!(object instanceof DoctorsDTO)) return false;
DoctorsDTO doctors=(DoctorsDTO)object;
return this.doctorsId.equalsIgnoreCase(doctors.doctorsId);
}
public int compareTo(DoctorsDTO doctors)
{
return this.doctorsId.compareTo(doctors.doctorsId);
}
}