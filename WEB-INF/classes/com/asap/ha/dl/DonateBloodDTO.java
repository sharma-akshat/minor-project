package com.asap.ha.dl;
import java.util.*;
public class DonateBloodDTO implements java.io.Serializable,Comparable<DonateBloodDTO>
{
private String donorId;
private String name;
private int age;
private String gender;
private String address;
private String bloodGroup;
private Date dateOfBirth;
private boolean medicalHistory;
private String aboutMedicalHistory;
public DonateBloodDTO()
{
this.donorId="";
this.name="";
this.age=0;
this.gender="";
this.address="";
this.bloodGroup="";
this.dateOfBirth=null;
this.medicalHistory=false;
this.aboutMedicalHistory="";
}
public void setDonorId(String donorId)
{
this.donorId=donorId;
}
public String getDonorId()
{
return this.donorId;
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
public void setAddress(String address)
{
this.address=address;
}
public String getAddress()
{
return this.address;
}
public void setBloodGroup(String bloodGroup)
{
this.bloodGroup=bloodGroup;
}
public String getBloodGroup()
{
return this.bloodGroup;
}
public void setDateOfBirth(java.util.Date dateOfBirth)
{
this.dateOfBirth=dateOfBirth;
}
public java.util.Date getDateOfBirth()
{
return this.dateOfBirth;
}
public void setMedicalHistory(boolean medicalHistory)
{
this.medicalHistory=medicalHistory;
}
public boolean getMedicalHistory()
{
return this.medicalHistory;
}
public void setAboutMedicalHistory(String aboutMedicalHistory)
{
this.aboutMedicalHistory=aboutMedicalHistory;
}
public String getAboutMedicalHistory()
{
return this.aboutMedicalHistory;
}

public boolean equals(Object object)
{
if(!(object instanceof DonateBloodDTO)) return false;
DonateBloodDTO donateBlood=(DonateBloodDTO)object;
return this.donorId.equalsIgnoreCase(donateBlood.donorId);
}
public int compareTo(DonateBloodDTO donateBlood)
{
return this.donorId.compareTo(donateBlood.donorId);
}

}