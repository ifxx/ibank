<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>iBank</title>
</head>
<body>
	<div
		style="width: 1400px; height: 650px; margin-top: 20px; margin-left: 60px; margin-bottom: 50px; border: 2px solid; border-color: black;">
		<div style="width: 100%; float: left">
			<div
				style="position: relative; height: 120px; width: 380px; padding-right: 0px; margin-right: 0px; float: left; height: 50%;">
					<img src="images/banner34.jpg" width="100%" />
			</div>
			<div
				style="position: relative; width: 1020px; padding-left: 0px; margin-left: 0px; height: 126px; background-image: url('images/banner38.jpg'); float: right; background-size: cover;">
				<div style="padding-top: 20px; padding-left: 20px">
					<img src="images/logo.JPG" height="80px" width="600px" />
				</div>
			</div>
		</div>

		<div style="width: 100%; padding-top: 10px; float: left; ">

			<div
				style="float: left; width: 70%; padding-left: 20px; padding-top: 40px;">
				<s:form action="login.action" method="post">
					<font face="verdana" color="#659EC7" size="4"><b>Customer NIU
							Login</b> </font>
					<br />
					
					<table>
						
						<tr>
							<td style="height: 10px"><s:textfield name="username"
									key="label.username" size="20" /></td>
						</tr>
						<tr>
							<td style="height: 10px"><s:password name="password"
									key="label.password" size="20" /></td>
						</tr>
						<tr>
							<td style="height: 10px"><s:submit method="execute"
									key="label.login" align="center" /></td>
						</tr>
						
					</table>
					<table>
					<tr>
							<td style="height: 10px; color:red"><s:actionerror /></td>
						</tr>
					</table>
					
				</s:form>
			</div>
			<div
				style="float: right; width: 25%; padding-right: 5px; padding-top: 30px; ">
				<div>
					<img src="images/Offers3.jpg" />
				</div>
				<div>
					<img src="images/offer2.jpg" />
				</div>
				<div>
					<img src="images/offers.jpg" />
				</div>
			</div>

		</div>

	</div>
</body>
</html>
