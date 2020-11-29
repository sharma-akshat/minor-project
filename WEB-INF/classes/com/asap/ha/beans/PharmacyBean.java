package com.asap.ha.beans;
public class PharmacyBean implements java.io.Serializable
{
private String pharmacyId;
private String name;
private String address;
private long contactNumber;
private String aboutPharmacy;
public PharmacyBean()
{
this.pharmacyId="";
this.name="";
this.address="";
this.contactNumber=0;
this.aboutPharmacy="";
}
public void setPharmacyId(String pharmacyId)
{
this.pharmacyId=pharmacyId;
}
public String getPharmacyId()
{
return this.pharmacyId;
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
public void setAboutPharmacy(String aboutPharmacy)
{
this.aboutPharmacy=aboutPharmacy;
}
public String getAboutPharmacy()
{
return this.aboutPharmacy;
}
}