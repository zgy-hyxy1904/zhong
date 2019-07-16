<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>物资采购与产品整合管理系统</title>
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet" type="text/css" media="all" />
<script src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript">
$(function(){
	$("#submit").click(function(){
		$.ajax({
			url:"${pageContext.request.contextPath}/contorller/OrderContorller?flag=save",
			data:$("#saveForm").serialize(), 
			type:"post",
			success:function(data){
				if(data=='Y'){
					location.href = "${pageContext.request.contextPath }/contorller/OrderContorller?flag=queryGuanLi"
				}else if(data=='N'){
					alert("库存数量不足！!!")
				}
			},
			dataType:"text"
			});
	
	
	});
	$("#save").click(function(){
		$.ajax({
			url:"${pageContext.request.contextPath}/contorller/OrderContorller?flag=save1",
			data:$("#saveForm").serialize(), 
			type:"post",
			success:function(data){
				if(data=='Y'){
					location.href = "${pageContext.request.contextPath }/contorller/OrderContorller?flag=queryGuanLi"
				}else if(data=='N'){
					alert("库存数量不足！!!")
				}
			},
			dataType:"text"
			});
	
	
	});
});
function showMaterials(){
	var date = new Date();
	var time = date.getTime();
	var obj = new Array('INSERT');
	//时间戳
	var url = "${pageContext.request.contextPath }/contorller/OrderContorller?flag=queryOrder";
	var resultValue = window.showModalDialog(url,obj,'dialogWidth:800px;dialogHeight:400px');

	//获取已经存在明细中的原料ID集合
	var materIds = document.getElementsByName('materId');
	if(resultValue!=null && resultValue!=undefined){
		for(var i=0;i<resultValue.length;i++){
			var tempAry = resultValue[i].split(',');
			var flag = false;
			//判断列表中是否已经选择了某种原料
			for(var j=0;j<materIds.length;j++){
				if(tempAry[0]==materIds[j].value){
					flag = true;
				}
			}
			if(!flag){
				insertMaterialMsg(tempAry);
			}
			
		}
		
	}
}


function insertMaterialMsg(tempAry){
	//原料名ID
	var id = tempAry[0];
	//原料名称
	var materName = tempAry[1];
	//原料库存
	var storage = tempAry[2];
	
	var trObj = attachmentList.insertRow();
	trObj.setAttribute("align","center");

	var tdObj = trObj.insertCell();
	tdObj.setAttribute("align","left");
	tdObj.innerHTML = "<input type='hidden' name='materId' value='"+id+"'>"+materName;
	
	var tdObj = trObj.insertCell();
	tdObj.innerHTML = "<input type='text' name='count' value='' class='inputTextNormal'>";
	
	var tdObj = trObj.insertCell();
	tdObj.setAttribute("align","left");
	tdObj.innerHTML = storage;
	
	var tdObj = trObj.insertCell();
	tdObj.setAttribute("align","left");
	tdObj.innerHTML = "<button onclick=\"deleteRow('attachmentList',this);\" class=\"btnIconDel\" title=\"删除\"></button>";
}


//删除行
function deleteRow(tableID,t){
	var tIndex = t.parentNode.parentNode.rowIndex;
		if(confirm('确定要执行此操作吗?')) {
			 t.parentNode.parentNode.parentNode.deleteRow(tIndex);
		}
		return false; 		

}

</script>
</head>

<body class="content-pages-body">
<div class="content-pages-wrap">
    <div class="commonTitle">
        <h2>&gt;&gt; 订单信息修改</h2>
  </div>
        <form id="saveForm" name="saveForm" method="post">
		  <table border="0" cellspacing="1" cellpadding="0" class="commonTable">
			  <tr>
				<td width="10%" align="right" class="title"><span class="required">*</span>订单编码：</td>
				<td width="15%" align="left"><input type="text" style="width:150px" name = "orderCode"></td>
				<td width="10%" align="right" class="title"><span class="required">*</span>订单保存日期：</td>
				<td width="15%" align="left"><input type="text" style="width:150px" name = "orderDate"></td>
				<td width="10%" align="right" class="title"><span class="required">*</span>订单状态：</td>
				<td width="15%" align="left">
					<select id="orderstatus" name = "orderFlag">
						<option value="0">未提交</option>
						<option value="1">待审核</option>
					</select>
				</td>
			  </tr>

		 </table>
	    <div align="left" style="margin-top:15px;margin-bottom:5px">
	    	<a href="javascript:showMaterials();" title="选择原料" class="btnShort">选择配件</a>
	    </div>
		<table width="90%" border="0" cellpadding="0" cellspacing="1" id="attachmentList" class="commonTable marginTopM">
		  <tr>
	          <th>配件名称</th>
	          <th>出货数量</th>
			  <th>库存数量</th>
	          <th class="editColXS">操作</th>
	      </tr>
		</table>
		</form>
	 </div>
    <!--//commonTable-->
    <div id="formPageButton">
    	<ul>
			<li><a href="#" title="保存" class="btnShort" id = "save">保存</a></li>
			<li><a href="#" title="提交" class="btnShort" id = "submit">提交</a></li>
        	<li><a href="javascript:window.history.go(-1)" title="返回" class="btnShort">返回</a></li>
        </ul>
    </div>
    <!--//commonToolBar-->
</div>
<!--//content pages wrap-->
</body>
</html>
