<%@page import="java.util.ArrayList"%>
<%@page import="com.agilet.model.TestEntity"%>
<%@page import="java.util.List"%>
<%@page import="com.agilet.util.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bootstrap后台模板</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%> /admin/css/index.css"
	type="text/css" media="screen">

<script
	src="<%=request.getContextPath()%> /admin/bootstrap-3.3.5/js/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%> /admin/bootstrap-3.3.5/js/jquery.form.min.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%> /admin/js/tendina.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%> /admin/js/common.js"></script>

<!-- 模态框引入 -->
<link
	href="<%=request.getContextPath()%> /admin/bootstrap-3.3.5/dist/css/bootstrap.min.css"
	rel="stylesheet">

<script
	src="<%=request.getContextPath()%> /admin/bootstrap-3.3.5/dist/js/bootstrap.min.js"></script>


<!-- DataTables-1.10.7 引入-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%> /admin/DataTables-1.10.7/css/jquery.dataTables.css">
<!-- jQuery -->

<!-- DataTables -->
<script type="text/javascript" charset="utf8"
	src="<%=request.getContextPath()%> /admin/DataTables-1.10.7/js/jquery.dataTables.js"></script>

<!-- 日期选择器引入  -->


<link
	href="<%=request.getContextPath()%> /admin/bootstrap-datetimepicker-master/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link
	href="<%=request.getContextPath()%> /admin/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">


<script type="text/javascript" charset="utf8"
	src="<%=request.getContextPath()%> /admin/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>

<script type="text/javascript" charset="utf8"
	src="<%=request.getContextPath()%> /admin/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.ja.js"></script>


<script type="text/javascript">
	$(document).ready(function() {
		//初始化table
		$('#table_id').dataTable({
			//第一列不排序
			"aoColumnDefs" : [ {
				"bSortable" : false,
				"aTargets" : [ 0 ]
			} ]
		});

		//移除排序小图标
		$(".hehe").removeClass("sorting_asc");

		$(".heihei:checkbox").click(function() {
			if ($(".heihei:checkbox").prop('checked')) {
				$(".test:checkbox").prop("checked", true);
			} else {
				$(".test:checkbox").prop('checked', false);

			}

		})

		$(".form_datetime2").datetimepicker({
			format : "yyyy-mm-dd",
			autoclose : true,
			todayBtn : true,
			minuteStep : 10,
			language : 'ja',
			startView : 2,
			minView : 2
		});

		//初始化datetimepicker
		$(".form_datetime1").datetimepicker({
			format : "yyyy-mm-dd",
			autoclose : true,
			todayBtn : true,
			minuteStep : 10,
			language : 'ja',
			startView : 2,
			minView : 2
		}).on('changeDate', function(ev) {
			var starttime = $('#dtp_input1').val();
			$('.form_datetime2').datetimepicker('setStartDate', starttime);
		});

		//提交增加考试表单 
		$("#addTestSubmit").click(function() {
			$("#addTest").modal('hide');
			$("#addSubmitForm").ajaxSubmit({
				type : "post",
				// 				async : false,
				url : "/admin/test?action=add&&time=" + TimeRanges,
				success : function(data) {
					window.location.reload();
				}
			})
		})

		//提交删除考试表单 
		$("#delTestSubmit").click(function() {
			$("#delTest").modal('hide');
			$("#deleteTest").ajaxSubmit({
				type : "post",
				// 				async : false,
				url : "/admin/test?action=delete&&time=" + TimeRanges,
				success : function(data) {
					window.location.reload();
				}
			})
		})
 
	});
</script>

</head>
<body>
	<!--顶部-->
	<div class="layout_top_header">
		<div style="float: left">
			<span
				style="font-size: 16px; line-height: 45px; padding-left: 20px; color: #8d8d8d">XXXX管理后台</span>
		</div>
		<div id="ad_setting" class="ad_setting">
			<a class="ad_setting_a" href="javascript:;"> <i
				class="icon-user glyph-icon" style="font-size: 20px"></i> <span>管理员</span>
				<i class="icon-chevron-down glyph-icon"></i>
			</a>
			<ul class="dropdown-menu-uu" style="display: none" id="ad_setting_ul">
				<li class="ad_setting_ul_li"><a href="javascript:;"><i
						class="icon-user glyph-icon"></i> 个人中心 </a></li>
				<li class="ad_setting_ul_li"><a href="javascript:;"><i
						class="icon-cog glyph-icon"></i> 设置 </a></li>
				<li class="ad_setting_ul_li"><a href="javascript:;"><i
						class="icon-signout glyph-icon"></i> <span class="font-bold">退出</span>
				</a></li>
			</ul>
		</div>
	</div>
	<!--顶部结束-->
	<!--菜单-->
	<div class="layout_left_menu">
		<ul id="menu">
			<li class="childUlLi"><a href="#"><i
					class="glyph-icon icon-home"></i>首页</a></li>


			<li class="childUlLi"><a href="#"> <i
					class="glyph-icon icon-reorder"></i>考试管理
			</a></li>

			<li class="childUlLi"><a href="#"> <i
					class="glyph-icon icon-reorder"></i>用户管理
			</a></li>

		</ul>
	</div>
	<!--菜单-->
	<div id="layout_right_content" class="layout_right_content">
		<div class="route_bg">
			<a href="#">主页</a><i class="glyph-icon icon-chevron-right"></i> <a
				href="#">菜单管理</a>
		</div>
		<div class="mian_content">

			<form id="deleteTest">
				<table id="table_id" class="display">
					<thead>
						<tr>
							<th class="hehe"><input type="checkbox" class="heihei" /></th>
							<th>考试名称</th>
							<th>考试开始日期</th>
							<th>考试结束日期</th>
							<th>考试总时间</th>
						</tr>
					</thead>

					<tbody>

						<%
							List<TestEntity> testEntities = (ArrayList) session
									.getAttribute("testEntities");
							for (TestEntity test : testEntities) {
						%>

						<tr>
							<td><input type="checkbox" value="<%=test.getKey()%>"
								name="testid" class="test" /></td>
							<td><a href="/admin/test?action=edit&&testid=<%=test.getKey()%>&&time="+TimeRanges+><%=test.getName()%></td>
							<td><%=test.getBeginDate()%></td>
							<td><%=test.getEndDate()%></td>
							<td><%=test.getTotalTime()%></td>
						</tr>

						<%
							}
						%>

					</tbody>

				</table>
			</form>
			<div>
				<button class="btn btn-primary btn-lg" data-toggle="modal"
					data-target="#addTest">添加考试</button>


				<button class="btn btn-primary btn-lg" data-toggle="modal"
					data-target="#delTest">删除考试</button>


				<!--添加考试 模态框（Modal） -->
				<div class="modal fade" id="addTest" tabindex="-1" role="dialog"
					aria-labelledby="addTestLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="addTestLabel">添加考试</h4>
							</div>
							<div class="modal-body">



								<form class="form-horizontal" id="addSubmitForm">
									<fieldset>





										<div class="control-group">

											<!-- Text input-->
											<label class="control-label" for="input01">考试名称</label> <input
												name="name" type="text" class="form-control">
										</div>



										<!-- Text input-->


										<label for="dtp_input1" class="control-label">考试开始日期</label>
										<div class="input-group date form_datetime1 col-md-5"
											data-link-field="dtp_input1">
											<input class="form-control" size="16" type="text" value=""
												readonly> <span class="input-group-addon"><span
												class="glyphicon glyphicon-remove"></span></span> <span
												class="input-group-addon"><span
												class="glyphicon glyphicon-th"></span></span>
										</div>
										<input type="hidden" name="begintime" id="dtp_input1" value="" /><br />




										<!-- Text input-->

										<label class="control-label" for="input2">考试结束日期</label>
										<div class="input-group date form_datetime2 col-md-5"
											data-link-field="dtp_input2">
											<input class="form-control" size="16" type="text" value=""
												readonly> <span class="input-group-addon"><span
												class="glyphicon glyphicon-remove"></span></span> <span
												class="input-group-addon"><span
												class="glyphicon glyphicon-th"></span></span>
										</div>
										<input type="hidden" name="endtime" id="dtp_input2" value="" /><br />



										<!-- Text input-->
										<label class="control-label" for="input01">考试总时间(分)</label>
										<div class="controls">
											<input type="text" name="totaltime"
												onkeyup="value=value.replace(/[^1234567890-]+/g,'')"
												class="input-xlarge" value="">
										</div>
									</fieldset>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
								<button type="button" id="addTestSubmit" class="btn btn-primary">添加</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal -->

				</div>



				<!--删除考试 模态框（Modal） -->
				<div class="modal fade" id="delTest" tabindex="-1" role="dialog"
					aria-labelledby="delTestLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="delTestLabel">删除考试</h4>
							</div>
							<div class="modal-body">



								<form class="form-horizontal" id="delSubmitform">
									<fieldset>

										<div class="control-group">确定删除？</div>


									</fieldset>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
								<button type="button" id="delTestSubmit" class="btn btn-primary">確定</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal -->

				</div>



			</div>
		</div>
		<div class="layout_footer">
			<p>Copyright © 2014 - XXXX科技</p>
		</div>
</body>
</html>