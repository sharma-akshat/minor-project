<jsp:useBean id='administrationBean' scope='request' class='com.thinking.machines.hr.beans.AdministrationBean' />
<jsp:setProperty name='administrationBean' property='*' />
<jsp:forward page='/login' />