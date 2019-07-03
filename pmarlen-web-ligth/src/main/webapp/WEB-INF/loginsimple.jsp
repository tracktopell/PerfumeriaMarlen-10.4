<%-- 
    Document   : loginsimple
    Created on : 02-jul-2019, 13:26:48
    Author     : alfredo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.pmarlen.web.security.managedbean.SystemInfoMB"%>
<%@page import="com.pmarlen.model.Constants"%>
<%
	String browserDetails = request.getHeader("User-Agent");
	String userAgent = browserDetails;
	String user = userAgent.toLowerCase();

	String os = "";
	String browser = "";

	//=================OS=======================
	if (userAgent.toLowerCase().indexOf("windows") >= 0) {
		os = "Windows";
	} else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
		os = "Mac";
	} else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
		os = "Unix";
	} else if (userAgent.toLowerCase().indexOf("android") >= 0) {
		os = "Android";
	} else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
		os = "IPhone";
	} else {
		os = "UnKnown, More-Info: " + userAgent;
	}
	//===============Browser===========================
	if (user.contains("msie")) {
		String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
		browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
	} else if (user.contains("safari") && user.contains("version")) {
		browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
	} else if (user.contains("opr") || user.contains("opera")) {
		if (user.contains("opera")) {
			browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
		} else if (user.contains("opr")) {
			browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
		}
	} else if (user.contains("chrome")) {
		browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
	} else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
		//browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
		browser = "Netscape-?";

	} else if (user.contains("firefox")) {
		browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
	} else if (user.contains("rv")) {
		browser = "IE";
	} else {
		browser = "UnKnown, More-Info: " + userAgent;
	}
	long to = request.getSession().getMaxInactiveInterval() * 1000 + 3000;
	//System.out.println("login.jsp->[" + request.getSession().getId() + "]: timeOut=" + (to / 1000) + " secs. =" + ((to / 1000 / 60.0)) + " mins.");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>PMARLEN:ENTRADA</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/login_css/loginsimple.css"     type='text/css'/>
		<script type="text/JavaScript">
			<!--
			var redirectTime = "<%=to%>";
			var redirectURL  = "<%=request.getContextPath()%>/";
			function timedRedirect() {
				setTimeout("location.href = redirectURL;",redirectTime);
			}

			function autoCompletteEmail(txtUserMail){
			var txt=txtUserMail.value.toLowerCase();
			//alert("->txt="+txt);
			if(txt.indexOf("@")<0 && txt.indexOf("@perfumeriamarlen.com.mx")<0){
				txtUserMail.value = txtUserMail.value+ "@perfumeriamarlen.com.mx"; 
				return true;
			}
			return false;
			}	
			//   -->
		</script>

	</head>

	<body onload="timedRedirect();">


		<div id="container">
			<form action="j_security_check" method="post" id="loginForm">
				<p class="cont-center">
					<img src="<%=request.getContextPath()%>/images/PM_NewLogo_2.png"/>
				</p>
				<h3 class="cont-center">L30</h3>
				<p class="cont-center">Introdusca su informaci&oacute;n</p>

				<label for="username">Correo electrónico:</label>
				<input type="text" id="username" name="j_username"/>
				<label for="password">Contraseña:</label>
				<input type="password" id="password" name="j_password"/>
				<div id="lower">
					<input type="submit" value="ENTRAR"/>
				</div><!--/ lower-->
			</form>
		</div><!--/ container-->
		<div class="footer-bar-<%=SystemInfoMB.getEnvironmentStaticlay()%>">
			<a href="#" title="Sistema de Adminisraci&oacute;n de Almacen Ver. <%=Constants.getServerVersion()%> (<%=SystemInfoMB.getEnvironmentStaticlay()%>) Git RevID: <%=Constants.getGitSHA1()%>">
				Perfumeria Marlen - L30 @ AWS / Construido: <%=Constants.getBuildTimestamp()%></a> | 
			<a href="<%=request.getContextPath()%>/pmcajaupdate/pmarlen-caja-installer.jar" title="Instaador Java de Sistema de Punto de Venta L30">L30 Caja</a> | 
			<a href="<%=request.getContextPath()%>/pmcajaupdate/version.properties" title="Versi&oacute;n del Sistema de Punto de Venta L30">ver.</a> | 
			<a href="#" title="[<%=os%>,<%=browser%>]">UA</a>
		</div>

	</body>
</html>
