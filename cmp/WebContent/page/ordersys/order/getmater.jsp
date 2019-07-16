<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		$("#queryBtn").click(function(){
			queryForm.action="${pageContext.request.contextPath }/contorller/OrderContorller?flag=queryOrder";
			queryForm.submit();
		});
		var count = ${sum};
		$("#down").click(function(){			
			if($("#pageNo").val()>=count){
				alert("已到达末页");
				return;
			}
			$("#pageNo").val(parseInt($("#pageNo").val())+1);
			alert("*****");
			pageForm.partsName.value = queryForm.partsName.value;
			pageForm.submit();
		});
		$("#up").click(function(){			
			if($("#pageNo").val()<=1){
				alert("已到达首页");
				return;
			}
			$("#pageNo").val(parseInt($("#pageNo").val()-1));
			pageForm.partsName.value = queryForm.partsName.value;
			pageForm.submit();
		});
		$("#first").click(function(){			
			$("#pageNo").val(1);
			pageForm.partsName.value = queryForm.partsName.value;
			pageForm.submit();
		});
		$("#last").click(function(){			
			$("#pageNo").val(count);
			pageForm.partsName.value = queryForm.partsName.value;
			pageForm.submit();
		});
		$("#go").click(function(){			
			if($("#pageNo").val()>=count || $("#pageNo").val()<=1){
				alert("输入的页数不正确");
				return;
			}
			pageForm.partsName.value = queryForm.partsName.value;
			pageForm.submit();
		});
	})
</script>
</head>
<script type="text/javascript">

var obj = window.dialogArguments;

function loadForm()
{
	queryForm.CZType.value = obj[0];
}


function checkAll(){
	var form = queryForm;
	var checkObj = form.ids;
	var ids = form.id;
	for(var i=0;i<ids.length;i++){
		ids[i].checked = checkObj.checked;
	}
}

function catchValues(){
	var form = queryForm;
	var ids = form.id;
	var flag = false;
	var ary = [];
	for(var i=0;i<ids.length;i++){
		if(ids[i].checked){
			flag = true;
			ary.push(ids[i].value);
		}
	}

	if(!flag){
		alert("请选择原料！");
		return ;
	}
	window.returnValue = ary;
	window.close();
}

</script>
<body class="content-pages-body">
<div class="content-pages-wrap">
	<div class="commonTitle">
	  <h2>&gt;&gt; 订单管理&nbsp;&gt;&gt;&nbsp;配件选择列表</h2>
	</div>
	<form name="queryForm" method="post">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="commonTableSearch">
	        <tr>
	            <th align="right">配件名称：</th>
	            <td ><input name="partsName" type="text" class="inputTextNormal" id="textfield" /></td>
				<td width="150" align="" >
					<input type="button" class="btnShort" value="检索" id = "queryBtn"/>
				</td>
				<td></td>
	        </tr>
    	</table>
 
    <!--//commonTableSearch-->
    <div class="btnBar">
    	<ul class="clearfix">
        	<li><a href="javascript:catchValues();" title="确定" class="btnLong">确定</a></li>
        	<li><a href="javascript:window.close();" title="关闭" class="btnLong">关闭</a></li>
        </ul>
    </div>
	    <table width="101%" border="0" cellpadding="0" cellspacing="1" class="commonTable">
	        <tr>
	            <th>
	            	<input type="checkbox" name="ids" onclick="checkAll();">
	            </th>
	            <th>配件名称</th>
	            <th>配件库存</th>
	        </tr>
			<c:forEach var = "partsRepertory" items = "${list }">
		        <tr>
		            <td align="center" style="width:5%">
		            	<input type="checkbox" name="id" value="${partsRepertory.parts.partsId },${partsRepertory.parts.partsName },${partsRepertory.partsRepCount }">
		            </td>
		            <td align="center">${partsRepertory.parts.partsName }</td>
		            <td align="center">${partsRepertory.partsRepCount }</td>
		        </tr>
		      </c:forEach>
	  </table>
  </form>
    <!--//commonTable-->
    <form name="pageForm" action="${pageContext.request.contextPath }/contorller/OrderContorller?flag=queryOrder" method="post">
    <input type="hidden" name="partsName">
    <div id="pagelist">
    	<ul class="clearfix">
        	<li><a href="#" id="first">首页</a></li>
            <li ><a href="#" id="up">上页</a></li>
            <li><a href="#" id="down">下页</a></li>
            <li class="current"><input type="text" id="pageNo" name="pageNo" value="${pageNo }" style="text-align:right" size="1"></li>
            <li><a href="#" id="go">跳转</a></li>
            <li><a href="#" id="last">尾页</a></li>
            <li class="pageinfo">第${pageNo }页</li>
            <li class="pageinfo">共${sum }页</li>
        </ul>
    </div>
	</form>
</div>
<!--//content pages wrap-->
</body>
</html>
