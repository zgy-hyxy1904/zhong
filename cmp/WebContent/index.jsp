 <%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>一汽轿车核心业务平台</title>
<style>
html,body { overflow:hidden;}
</style>
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet" type="text/css" media="all" />
<script src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/jquery.onlyforindex.js" type="text/javascript"></script>
</head>

<body>
<div id="header-wrap">
	<iframe allowtransparency="true" frameborder="0" id="header-box" scrolling="no" src="${pageContext.request.contextPath }/frame/inc-header.jsp"></iframe>
</div>
<div id="main-wrap">
	<div id="main-nav">
    	<iframe frameborder="0" id="siderbar-box" scrolling="no" src="${pageContext.request.contextPath }/frame/inc-nav.jsp"></iframe>
    </div>
    <div id="main-content">
        <table border="0" cellpadding="0" cellspacing="0" id="main-content-box">
            <tr>
                <td class="toggle"></td>
                <td class="content-wrap"><iframe frameborder="0" id="content-box" src="${pageContext.request.contextPath }/frame/welcome.jsp" scrolling="auto"></iframe></td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>