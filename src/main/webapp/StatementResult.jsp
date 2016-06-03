<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="java.util.*"%>
<%@ page import="com.sample.iBank.DataObjects.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	.TFtable th{ 
		padding:7px; border:#4e95f4 1px solid;
		font-weight: bold;
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
	<div>
		<div
			style="width: 100%; float: left; height: 5%; padding-left: 10px; padding-top: 2px">
			<s:actionerror />
		</div>
		<div style="width: 90%; float: left; height: 5%; padding-left: 10px; padding-top: 2px">
		<table class="TFtable">
     	<tr><th>Date</th><th>Description</th><th>Withdrawals</th><th>Deposits</th><th>Balance</th></tr>
		<s:iterator value="txnDetails"> 
		<tr>
			<td><s:property value="txDate"/></td>
			<td><s:property value="details"/></td>
			<s:if test="txType == 'Debit'">
				<td><s:property value="txAmount"/></td>
   				  				
			</s:if>
			<s:else>
				<td>&nbsp;</td> 
			</s:else>
			<s:if test="txType == 'Credit'">
				<td><s:property value="txAmount"/></td>   				  				
			</s:if>
			<s:else>
				<td>&nbsp;</td> 
			</s:else>
			<td><s:property value="balance"/></td>   	
		</tr> 
    </s:iterator>
		</table>
		</div>

	</div>
</body>
</html>