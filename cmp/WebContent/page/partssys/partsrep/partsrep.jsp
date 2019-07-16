<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>物资采购与产品整合管理系统</title>
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet" type="text/css" media="all" />
<script src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		$("#billFlag").change(function(){
			$.ajax({
				url:"${pageContext.request.contextPath}/controller/AjaxController?flag=queryType",
				data:{id:$("#billFlag").val()}, 
				type:"post",
				success:function(data){
					var billType = $("#billType")[0];
					for(var i = billType.options.length-1;i>0;i--){
						billType.remove(i);
					}
					$(data).each(function(){
						var option = new Option();
						option.value = this.id;
						option.text = this.name;
						billType.add(option);
					});
				},
				dataType:"json"
				});
		});
		$("#save").click(function(){
				$.ajax({
					url:"${pageContext.request.contextPath}/controller/PartsRepBillController?flag=save",
					data:$("#saveForm").serialize(), 
					type:"post",
					success:function(data){
						if(data=='Y'){
							location.href = "${pageContext.request.contextPath}/controller/PartsRepBillController?flag=query"
						}else if(data=='N'){
							alert("库存数量不足！!!")
						}
					},
					dataType:"text"
					});
			
			
		});
	});
</script>
</head>

<body class="content-pages-body">
<div class="content-pages-wrap">
    <div class="commonTitle">
        <h2>&gt;&gt; 配件出入库</h2>
  </div>
        <form id="saveForm" name="saveForm" action="${pageContext.request.contextPath}/controller/PartsRepBillController?flag=save" method="post">
		  <table border="0" cellspacing="1" cellpadding="0" height="70" class="commonTable">
			  <tr>
				<td width="10%" align="right" class="title"><span class="required">*</span>类型：</td>
				<td width="15%" align="left">
					<select style="width:150px;" id = "billFlag" name = "billFlag">
						<option value="">请选择</option>
						<option value="O">出</option>
						<option value="I">入</option>
					</select>
				</td>
				<td width="10%" align="right" class="title"><span class="required">*</span>出/入库类型：</td>
				<td width="15%" align="left">
					<select style="width:150px;" id = "billType" name = "billType">
						<option value="">请选择</option>
					</select>
				</td>
				<td width="10%" align="right" class="title"><span class="required">*</span>出/入库日期：</td>
				<td width="15%" align="left">
					<input type="text" style="width:150px;height:20px" id = "billTime" name = "billTime">
				</td>
				<td width="10%" align="right" class="title"><span class="required">*</span>操作员：</td>
				<td width="15%" align="left">
				<input type = "hidden" value = "${user.userId }" name = "userId">
				<input type = "text" value = "${user.loginName }" id = "user">
					<%-- <select style="width:150px;" name = "userId" id = "user">
						<option value="">请选择</option>
					<c:forEach items = "${listUser }" var = "user">
						<option value="${user.userId }">${user.loginName }</option>
						</c:forEach>
					</select> --%>
				</td>
			  </tr>
			  <tr>
				<td width="10%" align="right" class="title"><span class="required">*</span>配件：</td>
				<td width="15%" align="left">
					<select style="width:150px;" name = "partsId" id = "partsId">
						<option value="">请选择</option>
					<c:forEach items = "${list }" var = "parts">
						<option value="${parts.partsId }">${parts.partsName }</option>
						</c:forEach>
					</select>
				</td>
				<td width="10%" align="right" class="title"><span class="required">*</span>出/入库数量：</td>
				<td width="15%" align="left">
					<input type="text" style="width:150px;height:20px" id = "billCount" name = "billCount">
				</td>
				<td width="10%" align="right" class="title"><span class="required">*</span>说明：</td>
				<td width="15%" align="left" colspan="3"><input type="text" style="width:470px;height:20px" name = "billExplain"></td>
			  </tr>
		 </table>
		</form>
	 </div>
    <!--//commonTable-->
    <div id="formPageButton">
    	<ul>
			<li><a href="#" title="保存" class="btnShort" id = "save">保存</a></li>
        	<li><a href="javascript:window.history.go(-1)" title="返回" class="btnShort">返回</a></li>
        </ul> 
    </div>
    <!--//commonToolBar-->
</div>
<!--//content pages wrap-->
</body>
</html>
