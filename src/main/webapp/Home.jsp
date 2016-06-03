<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sample.iBank.DataObjects.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>iBank</title>
<style type="text/css">
	.TFtable{
		width:100%; 
		border-collapse:collapse; 
	}
	.TFtable td{ 
		padding:7px; border:#4e95f4 1px solid;
	}
	/* provide some minimal visual accomodation for IE8 and below */
	.TFtable tr{
		background: #b8d1f3;
	}
	/*  Define the background color for all the ODD background rows  */
	.TFtable tr:nth-child(odd){ 
		background: #b8d1f3;
	}
	/*  Define the background color for all the EVEN background rows  */
	.TFtable tr:nth-child(even){
		background: #dae5f4;
	}
</style>
</head>
<body>
	<div style="width: 97%; float: left; padding: 10px;">
		<div
			style="width: 100%; float: left; height: 15%; background-color: #87AFC7; padding-left: 10px; padding-top: 5px">
			<form action="summaryDetails.action" method="post">
				<font color="white" size="4">Account summary as on <%=new Date()%>.
					<input type="Submit" Value="Get Summary" size="30px" /> </font>
			</form>
		</div>
		<div
			style="width: 100%; float: left; height: 5%; padding-left: 10px; padding-top: 2px">
			<s:actionerror />
		</div>
		<div
			style="width: 100%; float: left; height: 400px; padding-left: 10px; padding-top: 5px; overflow:scroll;">
			<%
				if (session.getAttribute("userAccount") != null) {
					ArrayList<Account> accAl = (ArrayList<Account>) session.getAttribute("userAccount");

					for (int i = 0; i < accAl.size(); i++) {
						Account accdet = accAl.get(i);
			%>
				<div style="width: 90%; float: left;border: 1px solid; border-color: black; margin-top:5px;margin-bottom:5px">
				<font color="#87AFC7" size="4"><b>Account summary for account number : <%=accdet.getAccNo()%></b></font>
				<table class="TFtable">
					<tr><td width="30%">Withdrawable Balance </td><td width="20%">USD</td> <td><%=accdet.getBalance()%></td><tr>
					<tr>	<td  width="30%">Closing Balance </td><td width="20%">USD</td> <td><%=accdet.getBalance()%></td></tr>
					<tr>	<td  width="30%">Line Amount </td><td width="20%">USD</td> <td>0.00</td></tr>
					<tr>	<td width="30%">Funds in clearing </td width="20%"><td>USD</td> <td>0.00</td></tr>
					<tr>	<td width="30%">Multi Deposit Balance </td><td width="20%">USD</td> <td>0.00</td></tr>				
				
				</table> 
				</div>
			<%
				}

				}
			%>
		</div>
	</div>

</body>
</html>