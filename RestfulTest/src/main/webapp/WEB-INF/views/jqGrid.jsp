<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<c:set var="pathForImages" value="${pageContext.request.contextPath}/resources/images/" />
<c:set var="pathForJs" value="${pageContext.request.contextPath}/resources/js/" />
<c:set var="pathForCss" value="${pageContext.request.contextPath}/resources/css/" />
<!doctype html>
<html>
<link rel="stylesheet" type="text/css" media="screen" href="${pathForCss }jquery-ui.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${pathForCss }ui.jqgrid.css" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="${pathForJs }grid.locale-en.js" type="text/javascript"></script>
<script src="${pathForJs }jquery.jqGrid.min.js" type="text/javascript"></script>
<script>
$(document).ready(function() {
	jQuery("#list2").jqGrid({
	   	url:'${pageContext.request.contextPath}jqGrid/lists/1',
		datatype: "json",
		mtype;"POST",
	   	colNames:['Inv No','Date', 'Client', 'Amount','Tax','Total','Notes'],
	   	colModel:[
	   		{name:'id',index:'id', width:55, editable:false, editoptions:{readonly:true,size:10}},
	   		{name:'invdate',index:'invdate', width:90, editable:true, editoptions:{size:10}},
	   		{name:'name',index:'name asc, invdate', width:100, editable:true, editoptions:{size:25}},
	   		{name:'amount',index:'amount', width:80, align:"right", editable:true, editoptions:{size:10}},
	   		{name:'tax',index:'tax', width:80, align:"right", editable:true, editoptions:{size:10}},		
	   		{name:'total',index:'total', width:80,align:"right", editable:true, editoptions:{size:10}},		
	   		{name:'note',index:'note', width:150, sortable:false, edittype:"textarea", editoptions:{rows:"2",cols:"20"}}		
	   	],
	   	rowNum:10,
	   	rowList:[10,20,30],
	   	pager: '#pager2',
	   	sortname: 'id',
	    viewrecords: true,
	    sortorder: "desc",
	    caption:"JSON Example",
	    editurl:"${pageContext.request.contextPath}jqGrid/save",
		height:210
	});
	jQuery("#list2").jqGrid('navGrid','#pager2',
			{}, //options
			{height:280,reloadAfterSubmit:true}, // edit options
			{height:280,reloadAfterSubmit:false}, // add options
			{reloadAfterSubmit:false}, // del options
			{} // search options
	);
});	

function search() {
	var url = '<c:url value="/lists/1" />';
	var form = 'frm';
	function callback(result) {
		switch (result.status) {
			case "SUCCESS":
				$("#content").append(result.data);
				break;
			case "FAIL":
				alert(result.msg);
				break;
		}
	}
	uf_sendRequest_j(url, form, callback);	
}

/*****************************************
* Ajax 통신
*****************************************/
function uf_sendRequest_j(url, form, callback, f_before, f_after) {
	$.ajax({
        type: 'post'
        , async: true
        , url: url
        , dataType: "json"
        , data: $("#" + form).serialize() 
        , beforeSend: f_before
        , success: callback
        , error: function(data, status, err) {
        	alert(data.result + " , " + data.status);
        	if (data.status == '200') {
        		//location.href = "./sessionTimeOut.do";	
        	} else if (data.status == '500') {
        		location.href = "./500.do";	
        	} else if (data.status == '404') {
        		location.href = "./404.do";	
        	} else if (data.status == '403') {
        		location.href = "./403.do";	
        	} else if (data.status == '405') {
        		location.href = "./405.do";	
        	} else {
        		alert('서버와의 통신이 실패했습니다.' + data.status + ", " + data.responseText);
        	}
          }
        , complete: f_after
    });
}

</script>
<head>
	<title>jqGrid Test</title>
</head>
<body>
<form id="frm">
</form>
<h1>
	jqGrid Test!  
</h1>

<table id="list2"></table>
<div id="pager2"></div>
</body>
</html>
