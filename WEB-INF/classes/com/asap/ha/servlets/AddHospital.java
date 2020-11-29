package com.asap.ha.servlets;
import com.asap.ha.dl.*;
import com.asap.ha.beans.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class AddHospital extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
HospitalBean hospitalBean;
hospitalBean=(HospitalBean)request.getAttribute("hospitalBean");
String name;
String address;
long contactNumber;
int totalBeds;
boolean isCovidPatient;
String category;
String aboutHospital;
name=hospitalBean.getName();
address=hospitalBean.getAddress();
contactNumber=hospitalBean.getContactNumber();
totalBeds=hospitalBean.getTotalBeds();
isCovidPatient=hospitalBean.getIsCovidPatient();
category=hospitalBean.getCategory();
aboutHospital=hospitalBean.getAboutHospital();
HospitalDTO hospital=new HospitalDTO();
hospital.setName(name);
hospital.setAddress(address);
hospital.setContactNumber(contactNumber);
hospital.setTotalBeds(totalBeds);
hospital.setIsCovidPatient(isCovidPatient);
hospital.setCategory(category);
hospital.setAboutHospital(aboutHospital);
HospitalDAO hospitalDAO=new HospitalDAO();
try
{
hospitalDAO.add(hospital);
hospitalBean.setHospitalId(hospital.getHospitalId());
MessageBean messageBean;
messageBean=new MessageBean();
messageBean.setHeading("Hospital (Add Module)");
messageBean.setMessage("Hospital Added. Add more?");
messageBean.setGenerateButtons(true);
messageBean.setGenerateTwoButtons(true);
messageBean.setButtonOneText("Yes");
messageBean.setButtonOneAction("HospitalAddForm.jsp");
messageBean.setButtonTwoText("No");
messageBean.setButtonTwoAction("Hospitals.jsp");
request.setAttribute("messageBean",messageBean);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/Notification.jsp");
requestDispatcher.forward(request,response);
}catch(DAOException daoException)
{
ErrorBean errorBean;
errorBean=new ErrorBean();
errorBean.setError(daoException.getMessage());
request.setAttribute("errorBean",errorBean);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/HospitalAddForm.jsp");
requestDispatcher.forward(request,response);
}
}catch(Exception exception)
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("ErrorPage.jsp");
try
{
requestDispatcher.forward(request,response);
}catch(Exception e)
{
//do nothing
}
}
}
}