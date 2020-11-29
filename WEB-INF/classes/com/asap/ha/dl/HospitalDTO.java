package com.asap.ha.dl;
public class HospitalDTO implements java.io.Serializable,Comparable<HospitalDTO>
{
private String hospitalId;
private String name;
private String address;
private long contactNumber;
private int totalBeds;
private boolean isCovidPatient;
private String category;
private String aboutHospital;
private String facilities;
public HospitalDTO()
{
this.hospitalId="";
this.name="";
this.address="";
this.contactNumber=0;
this.totalBeds=0;
this.isCovidPatient=false;
this.category="";
this.aboutHospital="";
this.facilities="";
}
public void setHospitalId(String hospitalId)
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
public void setFacilities(String facilities)
{
this.facilities=facilities;
}
public String getFacilities()
{
return this.facilities;
}
public int hashCode()
{
return hospitalId.hashCode();
}
public boolean equals(Object object)
{
if(!(object instanceof HospitalDTO)) return false;
HospitalDTO hospital=(HospitalDTO)object;
return this.hospitalId.equalsIgnoreCase(hospital.hospitalId);
}
public int compareTo(HospitalDTO hospital)
{
return this.hospitalId.compareTo(hospital.hospitalId);
}
}