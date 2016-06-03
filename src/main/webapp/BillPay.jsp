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
<script src="jquery.js"></script>
<style type="text/css">
	.TFtable{
		width:100%; 
		border-collapse:collapse; 
	}
	.TFtable td{ 
		padding:7px; 
		text-align:left;
	}
	.TFtable th{ 
		padding:7px; 
		text-align:left;
		font-weight: bold;
	}
	
</style>
<script type="text/javascript">
	
	function updateBalance() {
		var answer = document.getElementById('accDropDownID2');
		//alert(answer[answer.selectedIndex].value);
		if (answer[answer.selectedIndex].value == "dropdown") {
			alert("Select Account number");
			document.getElementById('balanceid').innerHTML = '';
			return false;
		} else {
			var str = answer[answer.selectedIndex].value;			
			var split = str.split('|');
			document.getElementById('balanceid').innerHTML = split[1];
			return true;
		}

	}
	</script>

<script type="text/javascript">
	//$(document).ready(function(){
	function get_action2() {
		var answer1 = document.getElementById('accDropDownID2');
		var answer2 = document.getElementById('billerID');
		//alert(answer1[answer1.selectedIndex].value);
		if (answer1[answer1.selectedIndex].value == "dropdown") {
			alert("Select From Account number");
			return false;
		} 
		if (answer2[answer2.selectedIndex].value == "dropdown") {
			alert("Select Biller");
			return false;
		} 
		if (document.getElementById('txfrAmountID').value == "") {
			alert("Enter bill amount");
			return false;
		} 
		var str = answer1[answer1.selectedIndex].value;			
		var split = str.split('|');
		
		
			doAjaxPost(split[0], answer2[answer2.selectedIndex].value, document.getElementById('txfrAmountID').value);
			return true;
		

	}

	function doAjaxPost(fromacc, billerName, amt) {
		// get the form values  
		//var answer=document.getElementById('accDropDownID');
		//var name = val;
		//alert("ajax" + val);
		var isBill ="Yes";
		$.ajax({
			type : "POST",
			url : "transferFunds.action",
			data : {
				fromAccNumber : fromacc,
				txfrAmount : amt,
				isBillPay : isBill, 
				BillerName : billerName
			},
			dataType : 'html',
			success : function(html) {
				//('#statementResultDiv').html(html); //set result.jsp output to leftDiv 
				$('#transferResultDiv').html(html);
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
			style="border:1px solid;width: 90%; float: left; height: 30%; background-color: #EBF4FA; padding-left: 10px; padding-top: 5px">
			<table class="TFtable">
			<tr><th width="20%" >From Account</th>
			<td>
			<select id="accDropDownID2" name="accNumberSelect2" onchange="updateBalance()">
			<option value="dropdown">--Select--</option>
			<s:iterator value="actDetails"> 
				<option value="<s:property value="accNo"/>|<s:property value="balance"/>"><s:property value="accNo"/></option>
			</s:iterator>
			</select>
			</td>
			
			</tr>
			
			<tr><th width="20%" >Available Balance</th>
			<td><label id="balanceid"></label></td>
			
			</tr>
			</table>
		</div>
		
		<div
			style="border:1px solid;margin-top:20px;width: 90%; float: left; height: 30%; background-color: #EBF4FA; padding-left: 10px; padding-top: 25px">
			
			<table class="TFtable" >
			<tr><th width="20%" >Biller </th>
			<td>
			<select id="billerID" name="BillerSelect2" >
			<option value="dropdown">--Select--</option>
			<s:iterator value="billerDetails"> 
				<option value="<s:property value="name"/>"><s:property value="name"/></option>
			</s:iterator>
			</select>
			</td>
			
			</tr>
			
			<tr><th width="20%" >Bill Amount</th>
			<td><input type="text" name="tfrAmount"  size="30" id="txfrAmountID"/></td>
			
			</tr>
			</table>
			<input type="button" id="actionButton" value="Submit"
					onclick="get_action2()" />
			</div>
			
			<div id="transferResultDiv"
			style="width: 95%; float: left; height: 100px; padding-left: 10px; padding-top: 5px; overflow-y: scroll;margin-top:30px">
			<s:include value="/empty.jsp"></s:include>
		</div>

	</div>
</body>
</html>