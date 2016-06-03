<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
 
<font size="3"> <b>Welcome <font color="#98AFC7"><s:property value="#session['username']" /></font></b></font>,&nbsp;&nbsp;<span> <label id="Label1">aa</label></span>
<script type="text/javascript">

        function show() {
            var Digital = new Date();
            var hours = Digital.getHours();
            var minutes = Digital.getMinutes();
            var seconds = Digital.getSeconds();
            var day = Digital.getDay();
            var date = Digital.getDate();
            var mon = Digital.getMonth();
            var year = Digital.getFullYear();

            var dn = "AM"
            if (hours > 12) {
                dn = "PM"
                hours = hours - 12;
            }
            if (hours == 0)
                hours = 12;
            if (minutes <= 9)
                minutes = "0" + minutes;
            if (seconds <= 9)
                seconds = "0" + seconds;

            var txtDay = "Sunday";
            if (day == 1)
                txtDay = "Monday";
            if (day == 2)
                txtDay = "Tuesday";
            if (day == 3)
                txtDay = "Wednesday";
            if (day == 4)
                txtDay = "Thursday";
            if (day == 5)
                txtDay = "Friday";
            if (day == 6)
                txtDay = "Saturday";
            if (day == 7)
                txtDay = "Sunday";

            document.getElementById("Label1").innerHTML = txtDay + ", " + (mon + 1) + "-" + date + "-" + year + "   " + hours + ":" + minutes + ":" + seconds + " " + dn;
            setTimeout("show()", 1000);
        }
        show();

    </script>