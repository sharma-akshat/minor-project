package com.asap.ha.servlets;
import com.asap.ha.dl.*;
import com.asap.ha.beans.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class AddDoctor extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
DoctorBean doctorBean;
doctorBean=(DoctorBean)request.getAttribute("doctorBean");
String name;
String workingPlace;
long contactNumber;
String specialization;
String aboutDoctor;
String workingHours;
name=doctorBean.getName();
workingPlace=doctorBean.getWorkingPlace();
contactNumber=doctorBean.getContactNumber();
aboutDoctor=doctorBean.getAboutDoctor();
DoctorsDTO doctor=new DoctorsDTO();
doctor.setName(name);
doctor.setWorkingPlace(workingPlace);
doctor.setContactNumber(contactNumber);
doctor.setAboutDoctor(aboutDoctor);
DoctorsDAO doctorDAO=new DoctorsDAO();
try
{
doctorDAO.add(doctor);
doctorBean.setDoctorId(doctor.getDoctorsId());
MessageBean messageBean;
messageBean=new MessageBean();
messageBean.setHeading("Doctor (Add Module)");
messageBean.setMessage("Doctor Added. Add more?");
messageBean.setGenerateButtons(true);
messageBean.setGenerateTwoButtons(true);
messageBean.setButtonOneText("Yes");
messageBean.setButtonOneAction("DoctorAddForm.jsp");
messageBean.setButtonTwoText("No");
messageBean.setButtonTwoAction("Doctors.jsp");
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
requestDispatcher=request.getRequestDispatcher("/DoctorAddForm.jsp");
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