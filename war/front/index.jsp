<%@page import="com.agilet.dto.Question"%>
<%@page import="com.agilet.server.AnswerService"%>
<%@page import="com.agilet.server.ProblemService"%>
<%@page import="com.agilet.model.AnswerEntity"%>
<%@page import="com.agilet.model.ProblemEntity"%>
<%@page import="java.util.List"%>
<%@page import="com.agilet.model.TestEntity"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style>
*{ margin:0; padding:0; list-style:none;}
a{ text-decoration:none;}
a:hover{ text-decoration:none;}
.tcdPageCode{padding: 15px 20px;text-align: left;color: #ccc;text-align:center;}
.tcdPageCode a{display: inline-block;color: #428bca;display: inline-block;height: 25px;	line-height: 25px;	padding: 0 10px;border: 1px solid #ddd;	margin: 0 2px;border-radius: 4px;vertical-align: middle;}
.tcdPageCode a:hover{text-decoration: none;border: 1px solid #428bca;}
.tcdPageCode span.current{display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;color: #fff;background-color: #428bca;	border: 1px solid #428bca;border-radius: 4px;vertical-align: middle;}
.tcdPageCode span.disabled{	display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;	color: #bfbfbf;background: #f2f2f2;border: 1px solid #bfbfbf;border-radius: 4px;vertical-align: middle;}
</style>

  <script src="<%=request.getContextPath()%>/front/files/jquery-1.8.3.min.js"></script>
   
<script src="<%=request.getContextPath()%> /admin/bootstrap-3.3.5/js/jquery.form.min.js"></script>
 <script src="<%=request.getContextPath()%>/front/files/jquery.page.js"></script>
<script src="<%=request.getContextPath()%>/front/files/bootstrap-paginator-master/build/bootstrap-paginator.min.js"></script>
<script type="text/javascript">
<%
List<Question> problemEntities = (List<Question>) session.getAttribute("questions");
ProblemService problemService = new ProblemService();
AnswerService answerService = new AnswerService();
%>



$(document).ready(function() {
	$(".tcdPageCode").createPage({
		pageCount:<%=problemEntities.size()%>/5,
		current:1,
		backFn:function(p){
			//单击回调方法，p是当前页码
		}
	});
	

	$("#examination").click(function() {
		$("#form1").ajaxSubmit({
			type : "post",
			url : "/examination",
			dataType : 'json',
			success : function(data) {
			 
 				var json=eval(data);
				alert(json.totalScore+"  "+json.wrongList);
				
			}
		})
	})
 })
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考试系统</title>

<link href="<%=request.getContextPath()%>/front/files/index.css"
	rel="stylesheet" type="text/css">

<script src="<%=request.getContextPath()%>/front/files/check.js"></script>
</head>
<body>
		
	<table cellspacing="0" cellpadding="0" align="center" width="960px">
		<tbody >
			<tr>
				<td colspan="3" id="banner">考试</td>
			</tr>
		</tbody>
	</table>

	<form id="form1" name="fm" >
		<table width="960px" align="center" bgcolor="#FAFAFA">

			<tbody>
 
				<tr>
					<td id="info2" colspan="6">选择题</td>
				</tr>
				<%
				 
					for (Question problem : problemEntities) {
				%>
 
				<tr>
					<td colspan="4"><table width="98%" cellpadding="0"
							cellspacing="5" align="center">
							<tbody>
								<tr>
									<td><b><%=problem.getId()%>，<%=problem.getContent()%>(<%=problem.getScore() %>) </b></td>
									<input type="hidden" name="question<%=problem.getId()%>" value=<%=problem.getKey()%>>
								</tr>
								<tr><td>
									<%
										for (String problemKey : problem.getAnswers()) {
												AnswerEntity answer = answerService.getAnswerByKey(problemKey);
												{
													if(!answer.getContent().equals(""))
													{
												
									%>
									
										<input type="checkbox"   name="answer<%=problem.getId()%>" value="<%=answer.getKey()%>">  <%=answer.getContent().trim()%>    <br/>  <br/>
								    
								    
									<%
										            }
										        }
										}
									%>
								</td></tr>
							</tbody>
						</table></td>
				</tr>
					   
				<%
					}
				%>
				<input type="hidden" name="totalProblem" value=<%=problemEntities.size()%>>
				<tr>
					<td colspan="5" align="right"><input id="examination" type="button" name="Submit" value=" 提 交 "></td>
				</tr>

			</tbody>

		</table>
		
	</form>
<div class="tcdPageCode" > </div>
	<table cellspacing="0" cellpadding="0" align="center"
		background="<%=request.getContextPath()%>/front/files/botbj.jpg"
		width="960px">
		<tbody>
			<tr>
				<td height="60" align="center"><a href="http://www.xiao5u.com/"
					target="_blank"> agilet </a></td>
			</tr>
		</tbody>
	</table>

    
</body>
</html>