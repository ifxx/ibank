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
<script src="jquery.js"></script>
<script type="text/javascript">
	//$(document).ready(function(){
	function get_action() {
		var answer = document.getElementById('accDropDownID');
		//alert(answer[answer.selectedIndex].value);
		if (answer[answer.selectedIndex].value == "dropdown") {
			alert("Select Account number");
			return false;
		} else {
			doAjaxPost(answer[answer.selectedIndex].value);
			return true;
		}

	}

	function doAjaxPost(val) {
		// get the form values  
		//var answer=document.getElementById('accDropDownID');
		//var name = val;
		//alert("ajax" + val);
		$.ajax({
			type : "POST",
			url : "statementdetails.action",
			data : {
				accNumber : val
			},
			dataType : 'html',
			success : function(html) {
				//('#statementResultDiv').html(html); //set result.jsp output to leftDiv 
				$('#statementResultDiv').html(html);
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert('An error occurred! ' + thrownError);
			}

		});
	}

	//   });
</script>

</head>
<body>



	<div style="width: 97%; float: left; padding: 10px;">
		<div
			style="width: 100%; float: left; height: 15%; background-color: #87AFC7; padding-left: 10px; padding-top: 5px">
			<s:form action="statementdetails.action" method="post" name="accForm"
				id="form_id">
				<s:hidden name="test" id="accNo" />
				<font color="white" size="4">Select the account number : </font>
				<select id="accDropDownID" name="accNumberSelect"
					onchange="get_action()">
					<option value="dropdown">--Select--</option>
					<%
						if (session.getAttribute("userAccount") != null) {
								ArrayList<Account> accAl = (ArrayList<Account>) session
										.getAttribute("userAccount");

								for (int i = 0; i < accAl.size(); i++) {
									Account accdet = accAl.get(i);
					%>
					<option value="<%=accdet.getAccNo()%>"><%=accdet.getAccNo()%></option>
					<%
						}

							}
					%>
				</select>
				<input type="button" id="actionButton" value="Get Statement"
					onclick="get_action()" />
			</s:form>
		</div>
		
		<div id="statementResultDiv"
			style="width: 95%; float: left; height: 400px; padding-left: 10px; padding-top: 5px; overflow-y: scroll;">
			<s:include value="/empty.jsp"></s:include>
		</div>
	</div>

</body>
</html>