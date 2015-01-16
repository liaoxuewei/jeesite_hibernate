<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate();
		});
		
		//jAmEs_ add
		$(document).ready(function() {
			$("#tabhead1 a").mousemove(function (e) {
                $(this).tab('show');
            });
            $("#tabhead2 a").mousemove(function (e) {
                $(this).tab('show');
            });
		});
		//jAmEs_ add
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/demo/product/">产品列表</a></li>
		<li class="active"><a href="${ctx}/demo/product/form?id=${product.id}">产品<shiro:hasPermission name="demo:product:edit">${not empty product.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="demo:product:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="product" action="${ctx}/demo/product/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<!-- jAmEs_ add -->
		<ul class="nav nav-tabs">
			<li class="active" id="tabhead1"><a href="#tab1" data-toggle="tab">一般信息</a></li>
			<li id="tabhead2"><a href="#tab2" data-toggle="tab">扩展信息</a></li>
		</ul>
		<!-- jAmEs_ add -->
		<div id="TabContent" class="tab-content"><!-- jAmEs_ add -->
			<div class="tab-pane fade in active" id="tab1"><!-- jAmEs_ add -->
				<div class="control-group">
					<label class="control-label" for="name">名称:</label>
					<div class="controls">
						<form:input path="name" htmlEscape="false" maxlength="200" class="required"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="price">单价:</label>
					<div class="controls">
						<form:input path="price" htmlEscape="false" maxlength="200" class="required number" range="0,1000"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="type">类型:</label>
					<div class="controls">
						<form:select path="type">
							<form:option value="" label="请选择"/>
							<form:options items="${fns:getDictList('gl_product_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
			</div><!-- jAmEs_ add -->
			<div class="tab-pane fade" id="tab2"><!-- jAmEs_ add -->
				<div class="control-group">
					<label class="control-label" for="remarks">备注:</label>
					<div class="controls">
						<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
					</div>
				</div>
			</div><!-- jAmEs_ add -->
		</div><!-- jAmEs_ add -->
		<div class="form-actions">
			<shiro:hasPermission name="demo:product:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
