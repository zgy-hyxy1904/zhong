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
			queryForm.action="${pageContext.request.contextPath}/controller/user?flag=query";
			queryForm.submit();
		});
		var count = ${sum};
		$("#down").click(function(){			
			if($("#pageNo").val()>=count){
				alert("已到达末页");
				return;
			}
			$("#pageNo").val(parseInt($("#pageNo").val())+1);
			pageForm.loginName.value = queryForm.loginName.value;
			pageForm.name.value = queryForm.name.value;
			pageForm.submit();
		});
		$("#up").click(function(){			
			if($("#pageNo").val()<=1){
				alert("已到达首页");
				return;
			}
			$("#pageNo").val(parseInt($("#pageNo").val()-1));
			pageForm.loginName.value = queryForm.loginName.value;
			pageForm.name.value = queryForm.name.value;
			pageForm.submit();
		});
		$("#first").click(function(){			
			$("#pageNo").val(1);
			pageForm.loginName.value = queryForm.loginName.value;
			pageForm.name.value = queryForm.name.value;
			pageForm.submit();
		});
		$("#last").click(function(){			
			$("#pageNo").val(count);
			pageForm.loginName.value = queryForm.loginName.value;
			pageForm.name.value = queryForm.name.value;
			pageForm.submit();
		});
		$("#go").click(function(){			
			if($("#pageNo").val()>=count || $("#pageNo").val()<=1){
				alert("输入的页数不正确");
				return;
			}
			pageForm.loginName.value = queryForm.loginName.value;
			pageForm.name.value = queryForm.name.value;
			pageForm.submit();
		});
	})
</script>
</head>

<body class="content-pages-body">
<div class="content-pages-wrap">
	<div class="commonTitle">
	  <h2>&gt;&gt; 用户管理</h2>
	</div>
	<form name="queryForm" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="commonTableSearch">
       
        <tr>
            <th align="right">用户名：</th>
            <td><input name="loginName" type="text" class="inputTextNormal" value="${loginName }" id="textfield1" /></td>
            <th align="right">姓名：</th>
            <td><input name="name" type="text" value="${name }" class="inputTextNormal" id="textfield2" /></td>
            <th align="right">
				<input type="button" id="queryBtn" class="btnShort" value="检索" />
			</th>
        </tr>
       	
    </table>


    <!--//commonTableSearch-->
    
	<input type="button" class="btnNormal" value="新增用户" onclick="location.href='${pageContext.request.contextPath }/controller/user?flag=add'"/>	

	<br>

    <table width="101%" border="0" cellpadding="0" cellspacing="1" class="commonTable">
        <tr>
            <th>序号</th>
            <th>用户姓名</th>
            <th>用户名</th>
            <th>最近登录时间</th>
            <th class="editColDefault">操作</th>
        </tr>
        <c:forEach items="${list }" var="user">
        <tr>
            <td align="center">${user.userId }</td>
            <td align="center">${user.e.name }</td>
            <td align="center">${user.loginName }</td>
			<td align="center"><fmt:formatDate value="${user.loginTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td align="center">
            	<a href="${pageContext.request.contextPath }/controller/user?flag=edit&userId=${user.userId}" class="btnIconEdit" title="更新"></a>
                <a href="${pageContext.request.contextPath }/controller/user?flag=delete&userId=${user.userId}" class="btnIconDel" title="删除"></a>
            </td>
        </tr>
        </c:forEach>
              
  </table>
  </form>    
	<form name="pageForm" action="${pageContext.request.contextPath }/controller/user?flag=query" method="post">
    <input type="hidden" name="loginName">
    <input type="hidden" name="name">
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
