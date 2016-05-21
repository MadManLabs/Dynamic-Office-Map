<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" ng-app="dynamicOfficeMapApp">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Dynamic Office Map</title>

	<link href="<c:url value="/resources/library/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/library/font-awesome/css/font-awesome.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/style/main.css" />" rel="stylesheet">

	<script>var HOST = "http://localhost:8080/api/";</script>

	<script src="<c:url value="/resources/library/jquery-1.12.2.min.js" />"></script>
	<script src="<c:url value="/resources/library/fabric.min.js" />"></script>
	<script src="<c:url value="/resources/library/fabricjs_viewport.js" />"></script>
	<script src="<c:url value="/resources/library/angular/angular.min.js" />"></script>
	<script src="<c:url value="/resources/library/bootstrap/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/library/angular/angular-route.min.js" />"></script>
	<script src="<c:url value="/resources/library/angular/ui-bootstrap-tpls-1.2.5.min.js" />"></script>
	<script src="<c:url value="/resources/library/notify.min.js" />"></script>
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="content" ng-view></div>

	<!-- Angular Imports -->

	<script src="<c:url value="/resources/app/DynamicOfficeMapDashboard.js" />"></script>

	<script src="<c:url value="/resources/app/service/NotificationService.js" />"></script>
	<script src="<c:url value="/resources/app/service/AssetService.js" />"></script>

	<script src="<c:url value="/resources/app/controllers/UpdateFloorController.js" />"></script>
	<script src="<c:url value="/resources/app/controllers/FloorsController.js" />"></script>
	<script src="<c:url value="/resources/app/controllers/AddFloorController.js" />"></script>
	<script src="<c:url value="/resources/app/controllers/AddItemController.js" />"></script>
	<script src="<c:url value="/resources/app/controllers/UpdateAssetController.js" />"></script>
	<script src="<c:url value="/resources/app/controllers/UpdateZoneController.js" />"></script>
	<script src="<c:url value="/resources/app/controllers/ViewFloorController.js" />"></script>
	<script src="<c:url value="/resources/app/controllers/ViewPersonController.js" />"></script>
	<script src="<c:url value="/resources/app/controllers/ViewPersonMapController.js" />"></script>
</body>
</html>