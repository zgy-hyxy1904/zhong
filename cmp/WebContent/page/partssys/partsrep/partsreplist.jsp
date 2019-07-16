<%@ page contentType="text/html; charset=UTF-8"%>
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
			queryForm.action="${pageContext.request.contextPath }/controller/PartsRepertoryController?flag=query";
			queryForm.submit();
		});
		var count = ${sum};
		$("#down").click(function(){			
			if($("#pageNo").val()>=count){
				alert("已到达末页");
				return;
			}
			$("#pageNo").val(parseInt($("#pageNo").val())+1);
			pageForm.partsId.value = queryForm.partsId.value;
			pageForm.partsName.value = queryForm.partsName.value;
			pageForm.submit();
		});
		$("#up").click(function(){			
			if($("#pageNo").val()<=1){
				alert("已到达首页");
				return;
			}
			$("#pageNo").val(parseInt($("#pageNo").val()-1));
			pageForm.partsId.value = queryForm.partsId.value;
			pageForm.partsName.value = queryForm.partsName.value;
			pageForm.submit();
		});
		$("#first").click(function(){			
			$("#pageNo").val(1);
			pageForm.partsId.value = queryForm.partsId.value;
			pageForm.partsName.value = queryForm.partsName.value;
			pageForm.submit();
		});
		$("#last").click(function(){			
			$("#pageNo").val(count);
			pageForm.partsId.value = queryForm.partsId.value;
			pageForm.partsName.value = queryForm.partsName.value;
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
			pageForm.partsId.value = queryForm.partsId.value;
			pageForm.partsName.value = queryForm.partsName.value;
			pageForm.submit();
		});
	})
</script>
</head>

<body class="content-pages-body">
<div class="content-pages-wrap">
	<div class="commonTitle">
	  <h2>&gt;&gt; 配件管理</h2>
	</div>
	<form  name="queryForm" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="commonTableSearch">
    	<form id="form-search" name="form-search" action="" method="post">
        <tr>
            <th align="right">配件编码：</th>
            <td><input name="partsId" type="text" class="inputTextNormal" id="textfield2"  value ="${partsId }"/></td>
             <th align="right">配件名称：</th>
            <td><input name="partsName" type="text" class="inputTextNormal" id="textfield2" value ="${partsName }"/></td>
            <th align="right">
				<input type="button" class="btnShort" value="检索"  id = "queryBtn"/>
			</th>
        </tr>
        </form>
    </table>


    <!--//commonTableSearch-->
    
	<input type="button" class="btnNormal" value="配件出入库" onclick="location.href='${pageContext.request.contextPath }/controller/PartsRepBillController?flag=add'"/>	

    <table width="101%" border="0" cellpadding="0" cellspacing="1" class="commonTable">
        <tr>
            <th>序号</th>
            <th>配件编码</th>
            <th>配件名称</th>
            <th>库存数量</th>
        </tr>
        <c:forEach items="${list }" var="partsrep" varStatus="a">
        <tr>
            <td align="center">${a.count }</td>
            <td align="center">${partsrep.parts.partsId }</td>
            <td align="center">${partsrep.parts.partsName }</td>
            <td align="center">${partsrep.partsRepCount }</td>
        </tr>
        </c:forEach>
  </table>
  
  </form>
    <!--//commonTable-->
    <form name="pageForm" action="${pageContext.request.contextPath }/controller/PartsRepertoryController?flag=query" method="post">
    <input type="hidden" name="partsName">
     <input type="hidden" name="partsId">
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