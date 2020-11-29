package com.asap.ha.dl;
public class PharmacyDTO implements java.io.Serializable,Comparable<PharmacyDTO>
{
private String pharmacyId;
private String name;
private String address;
private long contactNumber;
private String aboutPharmacy;
public PharmacyDTO()
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
public boolean equals(Object object)
{
if(!(object instanceof PharmacyDTO)) return false;
PharmacyDTO pharmacy=(PharmacyDTO)object;
return this.pharmacyId.equalsIgnoreCase(pharmacy.pharmacyId);
}
public int compareTo(PharmacyDTO pharmacy)
{
return this.pharmacyId.compareTo(pharmacy.pharmacyId);
}


}