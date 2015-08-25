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

<!--引入图标-->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">

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

<%
	TestEntity test = (TestEntity) session.getAttribute("test");
%>


<script type="text/javascript">
		var problems=new Array();
		 
	$(document).ready(function() { 


		function initProblems()
		{
		//通过后台传过来的problems来初始化
		
		}
	
		function initProblemContainer()
		{
//  			for(var i=0;i<problems.length;i++)
//  			{
// 			 	var str="";
// 			 	str+="<a>"+ problems[i].problemTitle +"</a>"
// 					$("#container1").html(str);
//  			}
		}
	 
		function getID()
		{
 			var max=0
				for(var i=0;i<problems.length;i++)
				{
					if(max<problems[i].id)
						max=problems[i].id;
				}
			return max+1;
		}
		

		function getObjectById(id)
		{
			var object=new Object();
			for(var i=0;i<problems.length;i++)
			{
				if(id==problems[i].id)
				{
					object=problems[i];
					break;
				}
			}
			return object;
		}

		 
		
		function updateProblemContainer()
		{
			var str="";
 			for(var i=0;i<problems.length;i++)
 			{
			 	str+="<a data-target='#addproblem' class=problemShow id="+problems[i].id+">"+problems[i].problemTitle+"</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a class='glyphicon glyphicon-minus'></a><br/>"
 			}
 			$("#container1").html(str);
 			
 				$(document).ready(function() {
 						 $(".problemShow").click(function(){
 						     var object=getObjectById($(this).attr("id"));
 						    resetModalForm(object);
						 	 $("#addproblem").modal();
						 })
		 
 				})
 				 
		}
		

		function clearModalForm()
		{
			 $("#problemID").val("");
			 $("#problemTitle").val("");
			 $("#answer1").val("");
			 $("#answer2").val("");
			 $("#answer3").val("");
			 $("#answer4").val("");
			 $("#rightAnswer1").prop("checked",false);
			 $("#rightAnswer2").prop("checked",false);
			 $("#rightAnswer3").prop("checked",false);
			 $("#rightAnswer4").prop("checked",false);
		}


		function resetModalForm(object)
		{
			 $("#problemTitle").val(object.problemTitle);
			 $("#answer1").val(object.answer1);
			 $("#answer2").val(object.answer2);
			 $("#answer3").val(object.answer3);
			 $("#answer4").val( object.answer4);
			 $("#rightAnswer1").prop("checked",object.rightAnswer1);
			 $("#rightAnswer2").prop("checked",object.rightAnswer2);
			 $("#rightAnswer3").prop("checked",object.rightAnswer3);
			 $("#rightAnswer4").prop("checked",object.rightAnswer4);
		}

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
			startDate : "2013-02-14 10:00",
			minuteStep : 10,
			language : 'ja',
			startView : 2,
			minView : 2
		}).on('changeDate', function(ev) {
			var starttime = $('#dtp_input1').val();
			$('.form_datetime2').datetimepicker('setStartDate', starttime);
		});

		//提交增加考题表单
		$("#addProblemSubmit").click(function() {
			$("#addproblem").modal('hide');
			$("#editProblemForm").ajaxSubmit({
				type : "post",
				//async : false,
				url : "/admin/test?action=update&&testid=<%=test.getId()%>&&time=" + TimeRanges,
							success : function(
									data) {
								window.location
										.reload();
										}
									})
		 })

						//单击"添加"按钮
						$("#addProblemButton").click(
								function() {
									var problemID=$("#problemID").val();
									 alert($("#problemTitle").val());

									
									//如果已经存在,则删除旧的
									 for(var i=0;i<problems.length;i++)
										 {
										 	if(problems[i].id==problemID)
										 		{
										 			problems.pop(problems[i]);
										 			break;
										 		}
										 }
									var problem = new Object();
									problem.problemTitle = $("#problemTitle")
											.val();
									problem.answer1 = $("#answer1").val();
									problem.answer2 = $("#answer2").val();
									problem.answer3 = $("#answer3").val();
									problem.answer4 = $("#answer4").val();
									problem.rightAnswer1 = $("#rightAnswer1")
											.prop("checked");
									problem.rightAnswer2 = $("#rightAnswer2")
											.prop("checked");
									problem.rightAnswer3 = $("#rightAnswer3")
											.prop("checked");
									problem.rightAnswer4 = $("#rightAnswer4")
											.prop("checked");
										problem.id = getID();
										problems.push(problem);
									clearModalForm();
									updateProblemContainer();
									$("#problemID").val(problem.id);
								})
								//单击加号
							$(".glyphicon-plus").click(function(){
								clearModalForm();
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





			<form class="form-horizontal" id="editProblemForm">
				<fieldset>
					<div id="legend" class="">
						<legend class="">编辑考试作息</legend>
					</div>

					<!--折叠框开始 -->

					<div class="panel-group" id="accordion">





						<!-- 添加考题开始-->
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseThree"> 添加考题 </a>
								</h4>
							</div>
							<div id="collapseThree" class="panel-collapse collapse in">
								<div class="panel-body" id="problemContentcontainer">
									<!-- 考題內容开始-->

									<!-- 考題第一道内容开始-->
									<div id="container1"></div>
									<div id="demo" class="collapse">heihei</div>
									<!-- 考題第一道結束-->

									<!-- 									考題第二道容开始 -->
									<!-- 									<div id="myCollapsibleExample"> -->
									<!-- 										<a href="#level2" data-toggle="collapse">Click me </a> -->
									<!-- 									</div> -->
									<!-- 									<div id="level2" class="collapse">hehe</div> -->
									<!-- 									考題第二道结束 -->
									<!-- 									考題內容結束 -->

									<!-- 按钮触发模态框 -->
									<a class="glyphicon glyphicon-plus" data-toggle="modal"
										data-target="#addproblem"></a>

									<!-- 考题模态框（Modal） -->
									<div class="modal fade" id="addproblem" tabindex="-1"
										role="dialog" aria-labelledby="addproblemLabel"
										aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h4 class="modal-title" id="addproblemLabel">添加考题</h4>
												</div>
												<div class="modal-body">
													<!--考题模态框内容开始-->




													<div id="legend" class="">
														<legend class="">表单名</legend>
													</div>
													<div class="control-group">

														<input type="hidden" id="problemID" />
														<!-- Text input-->
														<label class="control-label" for="input01">问题</label>
														<div class="controls">
															<input type="text" class="input-xlarge" id="problemTitle">
														</div>
													</div>

													<div class="control-group">

														<!-- Text input-->
														<label class="control-label" for="input01">选项一</label>
														<div class="controls">
															<input type="text" class="input-xlarge" id="answer1">
														</div>
													</div>
													<div class="control-group">

														<!-- Text input-->
														<label class="control-label" for="input01">选项二</label>
														<div class="controls">
															<input type="text" class="input-xlarge" id="answer2">
														</div>
													</div>

													<div class="control-group">

														<!-- Text input-->
														<label class="control-label" for="input01">选项三</label>
														<div class="controls">
															<input type="text" class="input-xlarge" id="answer3">
														</div>
													</div>

													<div class="control-group">

														<!-- Text input-->
														<label class="control-label" for="input01">选项四</label>
														<div class="controls">
															<input type="text" class="input-xlarge" id="answer4">
														</div>
													</div>

													<div class="control-group"></div>
													<div class="control-group">
														<label class="control-label">正确答案</label>
														<div class="controls">

															<!-- Multiple Checkboxes -->
															<label class="checkbox"> <input type="checkbox"
																id="rightAnswer1" value="Option one"> 选项一
															</label> <label class="checkbox"> <input type="checkbox"
																id="rightAnswer2" value="Option two"> 选项二
															</label>
														</div>
														<label class="checkbox"> <input type="checkbox"
															id="rightAnswer3" value="Option one"> 选项三
														</label> <label class="checkbox"> <input type="checkbox"
															id="rightAnswer4" value="Option one"> 选项四
														</label>
													</div>







													<!--考题模态框内容结束-->
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">关闭</button>
													<button type="button" class="btn btn-primary"
														id="addProblemButton">添加</button>
												</div>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal -->







									</div>
								</div>
							</div>
							<!--添加考题结束-->

							<!--       修改考試基本信息開始     -->
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseOne"> 修改考试基本信息 </a>
									</h4>
								</div>
								<div id="collapseOne" class="panel-collapse collapse">
									<div class="panel-body">


										<div class="modal-body">


											<div class="control-group">

												<!-- Text input-->
												<label class="control-label" for="input01">考试名称</label> <input
													name="name" type="text" class="form-control"
													value="<%=test.getName()%>">
											</div>



											<!-- Text input-->


											<label for="dtp_input1" class="control-label">考试开始日期</label>
											<div class="input-group date form_datetime1 col-md-5"
												data-link-field="dtp_input1">
												<input class="form-control" size="16" type="text"
													value="<%=DateTimeUtil.timeFormat(test.getBeginDate())%>"
													readonly> <span class="input-group-addon"><span
													class="glyphicon glyphicon-remove"></span></span> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-th"></span></span>
											</div>
											<input type="hidden" name="begintime" id="dtp_input1"
												value="<%=DateTimeUtil.timeFormat(test.getBeginDate())%>" /><br />




											<!-- Text input-->

											<label class="control-label" for="input2">考试结束日期</label>
											<div class="input-group date form_datetime2 col-md-5"
												data-link-field="dtp_input2">
												<input class="form-control" size="16" type="text"
													value="<%=DateTimeUtil.timeFormat(test.getEndDate())%>"
													readonly> <span class="input-group-addon"><span
													class="glyphicon glyphicon-remove"></span></span> <span
													class="input-group-addon"><span
													class="glyphicon glyphicon-th"></span></span>
											</div>
											<input type="hidden" name="endtime" id="dtp_input2"
												value="<%=DateTimeUtil.timeFormat(test.getEndDate())%>" /><br />



											<!-- Text input-->
											<label class="control-label" for="input01">考试总时间(分)</label>
											<div class="controls">
												<input type="text" name="totaltime"
													onkeyup="value=value.replace(/[^1234567890-]+/g,'')"
													class="input-xlarge" value="120">
											</div>


										</div>
										<div>
											<button type="reset" class="btn btn-default"
												data-dismiss="modal">重置</button>
											<button type="button" id="addTestSubmit"
												class="btn btn-primary">确定</button>
										</div>

									</div>
								</div>
							</div>
							<!-- 修改考试基本信息结束 -->
						</div>


						<!--折叠框结束 -->
				</fieldset>
			</form>


		</div>
	</div>
	<div class="layout_footer">
		<p>Copyright © 2014 - XXXX科技</p>
	</div>

</body>
</html>