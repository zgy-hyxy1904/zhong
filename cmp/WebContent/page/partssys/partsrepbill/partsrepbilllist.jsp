<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
		$("#queryBtn").click(function(){
			queryForm.action="${pageContext.request.contextPath}/controller/PartsRepBillController?flag=query";
			queryForm.submit();
		});
		var count = ${sum};
		$("#down").click(function(){			
			if($("#pageNo").val()>=count){
				alert("已到达末页");
				return;
			}
			$("#pageNo").val(parseInt($("#pageNo").val())+1);
			pageForm.partsName.value = queryForm.partsName.value;
			pageForm.billFlag.value = queryForm.billFlag.value;
			pageForm.billType.value = queryForm.billType.value;
			pageForm.billTime.value = queryForm.billTime1.value;
			pageForm.submit();
		});
		$("#up").click(function(){			
			if($("#pageNo").val()<=1){
				alert("已到达首页");
				return;
			}
			$("#pageNo").val(parseInt($("#pageNo").val()-1));
			pageForm.partsName.value = queryForm.partsName.value;
			pageForm.billFlag.value = queryForm.billFlag.value;
			pageForm.billType.value = queryForm.billType.value;
			pageForm.billTime.value = queryForm.billTime1.value;
			pageForm.submit();
		});
		$("#first").click(function(){			
			$("#pageNo").val(1);
			pageForm.partsName.value = queryForm.partsName.value;
			pageForm.billFlag.value = queryForm.billFlag.value;
			pageForm.billType.value = queryForm.billType.value;
			pageForm.billTime.value = queryForm.billTime1.value;
			pageForm.submit();
		});
		$("#last").click(function(){			
			$("#pageNo").val(count);
			pageForm.partsName.value = queryForm.partsName.value;
			pageForm.billFlag.value = queryForm.billFlag.value;
			pageForm.billType.value = queryForm.billType.value;
			pageForm.billTime.value = queryForm.billTime1.value;
			pageForm.submit();
		});
		$("#go").click(function(){			
			var regex=/^[0-9]+$/;
			if(!regex.test($("#pageNo").val())){
				alert("输入的页数不正确");
				return;
			}
			if($("#pageNo").val()><%=request.getAttribute("sum")%>||$("#pageNo").val()<1){
				alert("输入的页数不正确");
				return;
			}
			pageForm.partsName.value = queryForm.partsName.value;
			pageForm.billFlag.value = queryForm.billFlag.value;
			pageForm.billType.value = queryForm.billType.value;
			pageForm.billTime1.value = queryForm.billTime.value;
			pageForm.submit();
		});
	})
</script>
</head>

<body class="content-pages-body">
<div class="content-pages-wrap">
	<div class="commonTitle">
	  <h2>&gt;&gt; 配件库存流水账查询</h2>
	</div>
	<form name="queryForm" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="commonTableSearch">
       	<form id="form-search" name="form-search" action="" method="post">
        <tr>
            <th align="right">配件名称：</th>
            <td><input name="partsName" type="text" class="inputTextNormal" id="textfield2"  value ="${partsName }"/></td>
            <th align="right">出/入库：</th>
            <td>
            	<select style="width:150px;" name ="billFlag" >
						<option value="">请选择</option>
						<option value="I">入库</option>
						<option value="O">出库</option>
				</select>
			</td>
            <th align="right">出入库类型：</th>
            <td>
            	<select style="width:150px;" name ="billType">
						<option value="">请选择</option>
						<option value = "in1">采购入库</option>
						<option value = "in2">调拨入库</option>
						<option value = "in3">其他入库</option>
						<option value = "out1">订单出库</option>
						<option value = "out2">调拨出库</option>
						<option value = "out3">其他出库</option>
						
				</select>
            </td>
            <th align="right">出入库日期：</th>
            <td>
            	<input name="billTime1" type="text" class="inputTextNormal" id="textfield2" value = "${billTime1 }"/>
            </td>
            <th align="right">
				<input type="button" class="btnShort" value="检索"  id = "queryBtn"/>
			</th>
        </tr>
       	</form>
    </table>
    </form>
	<br>

    <!--//commonTableSearch-->

    <table width="101%" border="0" cellpadding="0" cellspacing="1" class="commonTable">
        <tr>
            <th>序号</th>
            <th>出/入库</th>
            <th>出入库类别</th>
            <th>配件名称</th>
            <th>数量</th>
            <th>时间</th>
            <th>操作人</th>
        </tr>
         <c:forEach items ="${list }" var = "bill" varStatus="a">
        <tr>
            <td align="center">${a.count}</td>
            <td align="center">${bill.billflag=='I'?'入库':'出库' }</td>
            <td align="center">${bill.code.name}</td>
			<td align="center">${bill.parts.partsName}</td>
			<td align="center">${bill.billcount}</td>
			<td align="center"><fmt:formatDate value="${bill.billtime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td align="center">${bill.user.loginName}</td>
        </tr>
        </c:forEach>
  </table>
    <form name="pageForm" action="${pageContext.request.contextPath }/controller/PartsRepBillController?flag=query" method="post">
    <input type="hidden" name="partsName">
    <input type="hidden" name="billFlag">
    <input type="hidden" name="billType">
    <input type="hidden" name="billTime">
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