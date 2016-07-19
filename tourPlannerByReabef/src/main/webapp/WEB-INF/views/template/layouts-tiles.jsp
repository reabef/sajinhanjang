<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

	<head>
	
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">
	
	    <title>TourPlanner</title>
	    
	    <!-- JQuery-ui CSS -->
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
	    <!-- Bootstrap Core CSS -->
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	    <%-- <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"> --%>
	
	    <!-- Custom Fonts -->
	    <link href="${pageContext.request.contextPath}/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    <link href="http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css">
	    <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
	
	    <!-- Theme CSS -->
	    <link href="${pageContext.request.contextPath}/resources/css/grayscale.css" rel="stylesheet">
	
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
		<!-- jQuery -->
	    <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.js"></script>
		<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
		
	    <!-- Bootstrap Core JavaScript -->
	    <!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
	    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
		
	    <!-- Plugin JavaScript -->
	    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	
	    <!-- Google Maps API Key - Use your own API key to enable the map feature. More information on the Google Maps API can be found at https://developers.google.com/maps/ -->
	    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCRngKslUGJTlibkQ3FkfTxj3Xss1UlZDA&sensor=false"></script>
	
	    <!-- Theme JavaScript -->
	    <script src="${pageContext.request.contextPath}/resources/js/grayscale.js" type="text/javascript" ></script>
	    
	    <!-- Custormize JavaScript -->
	    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js<tiles:getAsString name="js"/>"></script>
	</head>

	<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
		<tiles:insertAttribute name="nav"/>
	    
	    <tiles:insertAttribute name="content"/>
	    
		<tiles:insertAttribute name="login"/>
		
		<tiles:insertAttribute name="join"/>
		
	    <tiles:insertAttribute name="message"/>
	   
		<tiles:insertAttribute name="footer"/>
		
	</body>
</html>