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
			queryForm.action="${pageContext.request.contextPath }/contorller/OrderContorller?flag=queryGuanLi";
			queryForm.submit();
		});
		var count = ${sum};
		$("#down").click(function(){			
			if($("#pageNo").val()>=count){
				alert("已到达末页");
				return;
			}
			$("#pageNo").val(parseInt($("#pageNo").val())+1);
			pageForm.orderCode.value = queryForm.orderCode.value;
			pageForm.orderDate.value = queryForm.orderDate.value;
			pageForm.orderFlag.value = queryForm.orderFlag.value;
			pageForm.submit();
		});
		$("#up").click(function(){			
			if($("#pageNo").val()<=1){
				alert("已到达首页");
				return;
			}
			$("#pageNo").val(parseInt($("#pageNo").val()-1));
			pageForm.orderCode.value = queryForm.orderCode.value;
			pageForm.orderDate.value = queryForm.orderDate.value;
			pageForm.orderFlag.value = queryForm.orderFlag.value;
			pageForm.submit();
		});
		$("#first").click(function(){			
			$("#pageNo").val(1);
			pageForm.orderCode.value = queryForm.orderCode.value;
			pageForm.orderDate.value = queryForm.orderDate.value;
			pageForm.orderFlag.value = queryForm.orderFlag.value;
			pageForm.submit();
		});
		$("#last").click(function(){			
			$("#pageNo").val(count);
			pageForm.orderCode.value = queryForm.orderCode.value;
			pageForm.orderDate.value = queryForm.orderDate.value;
			pageForm.orderFlag.value = queryForm.orderFlag.value;
			pageForm.submit();
		});
		$("#go").click(function(){			
			if($("#pageNo").val()>=count || $("#pageNo").val()<=1){
				alert("输入的页数不正确");
				return;
			}
			pageForm.orderCode.value = queryForm.orderCode.value;
			pageForm.orderDate.value = queryForm.orderDate.value;
			pageForm.orderFlag.value = queryForm.orderFlag.value;
			pageForm.submit();
		});
	})
</script>
</head>

<body class="content-pages-body">
<div class="content-pages-wrap">
	<div class="commonTitle">
	  <h2>&gt;&gt; 订单管理</h2>
	</div>
	<form name="queryForm" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="commonTableSearch">
       	<form id="form-search" name="form-search" action="" method="post">
        <tr>
            <th align="right">订单编码：</th>
            <td><input name="orderCode" type="text" class="inputTextNormal" id="textfield1" value = "${orderCode}"/></td>
            <th align="right">订单保存时间：</th>
            <td><input name="orderDate" type="text" class="inputTextNormal" id="textfield2" value = "${orderDate}"/></td>
            <td align="right">订单状态：</td>
            <td>
				<select style="width:150px;" name ="orderFlag" >
						<option value="">请选择</option>
						<option value="0">未提交</option>
						<option value="1">待审核</option>
				</select>
			</td>
            <th align="right">
				<input type="button" class="btnShort" value="检索" id = "queryBtn"/>
			</th>
        </tr>
        </form>
        <tr>

          </tr>
       	
    </table>


    <!--//commonTableSearch-->
    
	<input type="button" class="btnNormal" value="创建订单" onclick="location.href='${pageContext.request.contextPath }/page/ordersys/order/orderadd.jsp'"/>	

	<br>

    <table width="101%" border="0" cellpadding="0" cellspacing="1" class="commonTable">
        <tr>
            <th>序号</th>
            <th>订单编码</th>
            <th>订单保存时间</th>
            <th>订单状态</th>
            <th class="editColDefault">操作</th>
        </tr>
        <c:forEach items = "${list }" var = "order">
        <tr>
            <td align="center">${order.orderId }</td>
            <td align="center">${order.orderCode }</td>
            <td align="center"><fmt:formatDate value="${order.orderDate }" pattern="yyyy-MM-dd"/></td>
			<td align="center">
				<c:if test="${order.orderFlag=='0' }">
					<font color="YELLOW">未提交</font>
				</c:if>
				<c:if test="${order.orderFlag=='1' }">
					<font color="BLUE">待审核</font>
				</c:if>
				<c:if test="${order.orderFlag=='2' }">
					<font color="GREEN">已通过</font>
				</c:if>
				<c:if test="${order.orderFlag=='3' }">
					<font color="RED">不通过</font>
				</c:if>
			</td>
			<c:if test="${order.orderFlag=='1'}">
            <td align="center">
            	<a href="${pageContext.request.contextPath }/contorller/OrderContorller?flag=view&orderId=${order.orderId }" class="btnIconView" title="查看详情"></a>
            	<a href="${pageContext.request.contextPath }/contorller/OrderContorller?flag=edit&orderId=${order.orderId }" class="btnIconEdit" title="更新"></a>
                <a href="${pageContext.request.contextPath }/contorller/OrderContorller?flag=delete&orderId=${order.orderId }&orderFlag1=${order.orderFlag } " class="btnIconDel" title="删除"></a>
            </td>
            </c:if>
            <c:if test="${order.orderFlag=='0'}">
            <td align="center">
            	<a href="${pageContext.request.contextPath }/contorller/OrderContorller?flag=view&orderId=${order.orderId }" class="btnIconView" title="查看详情"></a>
            	<a href="${pageContext.request.contextPath }/contorller/OrderContorller?flag=edit&orderId=${order.orderId }" class="btnIconEdit" title="更新"></a>
                <a href="${pageContext.request.contextPath }/contorller/OrderContorller?flag=delete&orderId=${order.orderId }&orderFlag1=${order.orderFlag } " class="btnIconDel" title="删除"></a>
            </td>
            </c:if>
        </tr>
        </c:forEach>
  </table>
  </form>
    <!--//commonTable-->
    <form name="pageForm" action="${pageContext.request.contextPath }/contorller/OrderContorller?flag=queryGuanLi" method="post">
    <input type="hidden" name="orderCode">
    <input type="hidden" name="orderDate">
    <input type="hidden" name="orderFlag">
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
