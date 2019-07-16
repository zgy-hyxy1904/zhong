<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>物资采购与产品整合管理系统</title>
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet" type="text/css" media="all" />
<script src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script>
	$(function(){
		$("#saveBtn").click(function(){
			saveForm.action="${pageContext.request.contextPath}/controller/user?flag=save";
			saveForm.submit();
		});
	})
</script>
</head>

<body class="content-pages-body">
<div class="content-pages-wrap">
    <div class="commonTitle">
        <h2>&gt;&gt; 更改用户密码</h2>
  </div>
        <form  name="saveForm" action="" method="post">
		  <table border="0" cellspacing="1" cellpadding="0" class="commonTable">
			  <tr>
				<td width="10%" align="right" class="title"><span class="required">*</span>用户姓名：</td>
				<td width="15%" align="left">
				<select name="eid" style="width:150px;">
						<option value="">请选择</option>
						<c:forEach items="${empList }" var="e">
							<option value="${e.id }">${e.name }</option>
						</c:forEach>
				</select>
				</td>
				<td width="10%" align="right" class="title"><span class="required">*</span>用户名：</td>
				<td width="15%" align="left">
				<input name="loginName" type="text" class="inputTextNormal" id="textfield2" />
				</td>
				<td width="10%" align="right" class="title"><span class="required">*</span>密码：</td>
				<td width="15%" align="left"><input name="loginPwd" type="password" class="inputTextNormal" id="textfield2" /></td>
				<td width="10%" align="right" class="title"><span class="required">*</span>确认密码：</td>
				<td width="15%" align="left">
					<input  type="password" class="inputTextNormal" id="textfield2" />
				</td>
			  </tr>

		 </table>
	    
		</form>
	 </div>
    <!--//commonTable-->
    <div id="formPageButton">
    	<ul>
			<li><a href="#" title="保存" id="saveBtn" class="btnShort">保存</a></li>
        	<li><a href="javascript:window.history.go(-1)" title="返回" class="btnShort">返回</a></li>
        </ul>
    </div>
    <!--//commonToolBar-->
</div>
<!--//content pages wrap-->
</body>
</html>
