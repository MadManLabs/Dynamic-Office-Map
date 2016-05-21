<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" ng-app="dynamicOfficeMapApp">
<head>
    <title>Dynamic Office Map</title>

    <style>
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }
        body {
            width: 1100px;
            height: 1000px;
        }
    </style>

    <script>var HOST = "http://192.168.168.107:8080";</script>

    <script src="<c:url value="/resources/library/jquery-1.12.2.min.js" />"></script>
    <script src="<c:url value="/resources/library/fabric.min.js" />"></script>
    <script src="<c:url value="/resources/library/angular/angular.min.js" />"></script>
    <script src="<c:url value="/resources/library/bootstrap/js/bootstrap.min.js" />"></script>
    <script src="<c:url value="/resources/library/angular/angular-route.min.js" />"></script>
    <script src="<c:url value="/resources/library/angular/ui-bootstrap-tpls-1.2.5.min.js" />"></script>
    <script src="<c:url value="/resources/library/notify.min.js" />"></script>
</head>
<body>
<div ng-view></div>

<!-- Angular Imports -->

<script src="<c:url value="/resources/app/DynamicOfficeMapDashboard.js" />"></script>

<script src="<c:url value="/resources/app/service/NotificationService.js" />"></script>

<script src="<c:url value="/resources/app/controllers/UpdateFloorController.js" />"></script>
<script src="<c:url value="/resources/app/controllers/ViewFloorsController.js" />"></script>
<script src="<c:url value="/resources/app/controllers/AddFloorController.js" />"></script>
<script src="<c:url value="/resources/app/controllers/ViewFloorController.js" />"></script>
<script src="<c:url value="/resources/app/controllers/ViewPersonController.js" />"></script>
<script src="<c:url value="/resources/app/controllers/ViewPersonMapController.js" />"></script>
</body>
</html>