<%@ include  file="../shared/header.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
 <!-- Small boxes (Stat box) -->
 <center><h1>欢迎使用学院信息管理系统</h1></center>
 <!-- /.row (main row) -->
 <%@ include  file="../shared/footer.jsp"%>
 <script type="text/javascript">
 $(document).ready(function(){
	 $(".czxxmenu").removeClass("active");
	 $(".czxxmenu").removeClass("menu-open");
	 $("#welcome_menu").addClass("active");
	 $("#maintitle").text("欢迎使用");
 });
 </script>
<%@ include  file="../shared/footer2.jsp"%>