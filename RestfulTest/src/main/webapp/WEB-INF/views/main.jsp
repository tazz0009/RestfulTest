<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!doctype html>
<html>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
	search();
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
	<title>Main</title>
</head>
<body>
<form id="frm">
</form>
<h1>
	MAIN PAGE!  
</h1>

<P id="content"></P>
</body>
</html>
