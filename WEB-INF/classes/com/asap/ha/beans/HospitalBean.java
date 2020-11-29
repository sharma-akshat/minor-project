package com.asap.ha.beans;
public class HospitalBean implements java.io.Serializable
{
private String hospitalId;
private String address;
private String name;
private long contactNumber;
private int totalBeds;
private boolean isCovidPatient;
private String category;
private String aboutHospital;
public HospitalBean()
{
this.hospitalId="";
this.name="";
this.address="";
this.contactNumber=0;
this.totalBeds=0;
this.isCovidPatient=false;
this.aboutHospital="";
this.category="";
}
public void setHospitalId(String hopsitalId)
{
this.hospitalId=hospitalId;
}
public String getHospitalId()
{
return this.hospitalId;
}
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
public void setAddress(String address)
{
this.address=address;
}
public String getAddress()
{
return this.address;
}
public void setContactNumber(long contactNumber)
{
this.contactNumber=contactNumber;
}
public long getContactNumber()
{
return this.contactNumber;
}
public void setTotalBeds(int totalBeds)
{
this.totalBeds=totalBeds;
}
public int getTotalBeds()
{
return this.totalBeds;
}
public void setIsCovidPatient(boolean isCovidPatient)
{
this.isCovidPatient=isCovidPatient;
}
public boolean getIsCovidPatient()
{
return this.isCovidPatient;
}
public void setCategory(String category)
{
this.category=category;
}
public String getCategory()
{
return this.category;
}
public void setAboutHospital(String aboutHospital)
{
this.aboutHospital=aboutHospital;
}
public String getAboutHospital()
{
return this.aboutHospital;
}
}