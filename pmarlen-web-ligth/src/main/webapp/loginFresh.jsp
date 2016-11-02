<%@page import="com.pmarlen.web.security.managedbean.SystemInfoMB"%>
<%@page import="com.pmarlen.model.Constants"%>
<%@ page pageEncoding="UTF-8" %>
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
<head>
	<title>PMARLEN:ENTRADA</title>
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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/login_css/login_bg.css"     type='text/css'/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/login_css/login.css"     type='text/css'/>
</head>
<body  onload="timedRedirect();">
	<br/>
	<br/>
	<br/>
	<div class="ribbon"></div>
	<div class="login">
		<br/>
		<p>
			<img src="<%=request.getContextPath()%>/images/PM_NewLogo_2.png"/>
		</p>
		<h3>Entrada al Sistema.</h3>
		<p>Introdusca su informaci&oacute;n</p>
		<form action="j_security_check" method="post" id="loginForm">
			<div class="input">
				<div class="blockinput">
					<i class="icon-envelope-alt"></i><input type="mail" name="j_username" placeholder="Correo Electronico" onblur="autoCompletteEmail(this);">
				</div>
				<div class="blockinput">
					<i class="icon-unlock"></i><input type="password"  name="j_password" placeholder="Contraseña">
				</div>		
			</div>
			<button>ENTRAR</button>
			<%
				if (request.getParameter("error") != null) {
			%>
			<br/>
			<p class="error_login">ERROR EN CORREO O CONTRASE&Ntilde;A</p>
			<%
			} else {
			%>
			<%
				}
			%>									
		</form>
	</div>
	<div class="footer-bar-<%=SystemInfoMB.getEnvironmentStaticlay()%>">
		<a href="#" title="Sistema de Adminisraci&oacute;n de Almacen Ver. <%=Constants.getServerVersion()%> (<%=SystemInfoMB.getEnvironmentStaticlay()%>) Git RevID: <%=Constants.getGitSHA1()%>">
			Perfumeria Marlen - L30 / Construido: <%=Constants.getBuildTimestamp()%></a> | 
		<a href="<%=request.getContextPath()%>/pmcajaupdate/pmarlen-caja-installer.jar" title="Instaador Java de Sistema de Punto de Venta L30">L30 Caja</a> | 
		<a href="<%=request.getContextPath()%>/pmcajaupdate/version.properties" title="Versi&oacute;n del Sistema de Punto de Venta L30">ver.</a> | 
		<a href="#" title="[<%=os%>,<%=browser%>]">UA</a>
	</div>

</body>