package com.asap.ha.servlets;
import com.asap.ha.dl.*;
import com.asap.ha.beans.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class AddPharmacy extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
PharmacyBean pharmacyBean;
pharmacyBean=(PharmacyBean)request.getAttribute("pharmacyBean");
String name;
String address;
long contactNumber;
String aboutPharmacy;
name=pharmacyBean.getName();
address=pharmacyBean.getAddress();
contactNumber=pharmacyBean.getContactNumber();
aboutPharmacy=pharmacyBean.getAboutPharmacy();
PharmacyDTO pharmacy=new PharmacyDTO();
pharmacy.setName(name);
pharmacy.setAddress(address);
pharmacy.setContactNumber(contactNumber);
pharmacy.setAboutPharmacy(aboutPharmacy);
PharmacyDAO pharmacyDAO=new PharmacyDAO();
try
{
pharmacyDAO.add(pharmacy);
pharmacyBean.setPharmacyId(pharmacy.getPharmacyId());
MessageBean messageBean;
messageBean=new MessageBean();
messageBean.setHeading("Pharmacy (Add Module)");
messageBean.setMessage("Pharmacy Added. Add more?");
messageBean.setGenerateButtons(true);
messageBean.setGenerateTwoButtons(true);
messageBean.setButtonOneText("Yes");
messageBean.setButtonOneAction("PharmacyAddForm.jsp");
messageBean.setButtonTwoText("No");
messageBean.setButtonTwoAction("Pharmacies.jsp");
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
requestDispatcher=request.getRequestDispatcher("/PharmacyAddForm.jsp");
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