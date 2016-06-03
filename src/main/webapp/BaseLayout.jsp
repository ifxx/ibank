<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" />
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

		<div style="width: 100%;  pading-left:5px; float: left; height:500px">
			<div style="width: 12%; float: left;height:100%; padding-top: 10px;">
				<tiles:insertAttribute name="menu" />
			</div>
			
			<div style="width: 85%; float: left;">
				<div style="width: 100%; float: left;height:10%;text-align:right;padding-top:0px;"><tiles:insertAttribute name="header" /></div>
				<div style="width: 100%; float: left;height:90%;border: 1px solid; border-color: #98AFC7;border-bottom: 0px; border-left: 0px; border-right: 0px"><tiles:insertAttribute name="body" /></div>
				
			</div>
		</div>
	</div>




</body>
</html>