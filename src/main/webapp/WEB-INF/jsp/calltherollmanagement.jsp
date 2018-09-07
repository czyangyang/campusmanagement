<%@ include  file="../shared/header.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 屏蔽tomcat 自带的 EL表达式 -->
<%@ page isELIgnored="false" %>
<div class="row">
 <div class="col-xs-12">
   <div class="box">
     <div class="box-header">
       <button type="button" class="btn btn-block btn-primary btnWidthNormal" data-toggle="modal" id="newBtn" >开始点名</button>
     </div>
     <!-- /.box-header -->
     <div class="box-body">
       <table id="example2" class="table table-bordered table-hover">
         <thead>
         <tr>
           <th style="width:9%">序号</th>
           <th style="width:31%">班级</th>
           <th style="width:60%">标题</th>
           <th style="min-width:200px;">操作</th>
         </tr>
         </thead>
         <tbody id="list_content">
         
         </tbody>
         
       </table>
       
     </div>
     <!-- /.box-body -->
   </div>
   <!-- /.box -->
 </div>
 <!-- /.col -->
</div>
<div class="m-style M-box"></div>
<!-- /.row -->

<div class="modal fade" id="modal-default">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modal_title"></h4>
      </div>
      <div class="modal-body">
      <form id="dataForm">
      <input type="hidden" id="dataid" name="id" value="0"/>
        <div class="form-group">
           <label for="classname">标题</label>
           <input type="text" class="form-control textAlignLeft" id="title" name="title" placeholder="请输入标题">
         </div>
         <div class="form-group" id="schoolclassdiv" style="display:none;">
           <label for="schoolclass">班级</label>
           <input type="text" class="form-control textAlignLeft" id="schoolclass" placeholder="请输入标题">
         </div>
         <div class="form-group" id="classiddiv">
           <label for="classid">班级</label>
           <select class="form-control" name="classid" id="classid">
             <option value="0" selected>请选择</option>
             <c:forEach items="${schoolclassList.list}" var="schoolclass">
   				<option value="<c:out value="${schoolclass.id}"/>"><c:out value="${schoolclass.classname}"/></option>
 			</c:forEach>
           </select>
           <!-- <input type="text" class="form-control" id="classid" name="classid" placeholder="请输入班级"> -->
         </div>
   
         <div class="form-group" id="studentsList">
           
         </div>
      </div>
      </form>
      <div class="modal-footer">
        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="btnSave">保存</button>
      </div>
    </div>
    <!-- /.modal-content -->
  </div>
  <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
        
<%@ include  file="../shared/footer.jsp"%>
 <script type="text/javascript">
 $(function(){
	 
	 var isFirstLoad=0;
	 var queryObj = new Object();
	 queryObj.page=0;
	 getData(0);
	 
	 function pageCallBack(api,jq)
	 {
		 var current = parseInt(api.getCurrent())-1;
		 getData(current);
	 }
	 
	 $("#classid").change(function(){
		 var classid = $(this).val();
		 
		 var obj = new Object();
		 obj.classid = classid;
		 
		 $.ajax({
            type: "post",
            url: '../student/getstudentbyclassid',
            async: false, // 使用同步方式
            data: JSON.stringify(obj),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data) {
            	if(data.code==1)
	           	{
	            	 $("#studentsList").html("");
	            	 var html="";
	            	 jQuery.each(data.result.list,function(i,val){
	            		 html+="<div class='checkbox'>";
	            		 html+="<label style='height:20px;'>";
            			 html+="<input type='checkbox' style='height:20px;width:20px;' studentid='"+val.id+"'>";
            			 html+=val.studentname;
           				 html+="</label>";
       					 html+="</div>";

	            	 });
	            	 $("#studentsList").html(html);
	           	}
            	else{
	            	 toastr.error(data.message, '提示');
	            }
            }
         });
	 });
	 
	 function getData(page)
	 {
		 queryObj.page = page;

		 $.ajax({
	         type: "post",
	         url: '../calltheroll/getallcalltheroll',
	         async: false, // 使用同步方式
	         data: JSON.stringify(queryObj),
	         contentType: "application/json; charset=utf-8",
	         dataType: "json",
	         success: function(data) {
	             if(data.code==1)
	           	 {
	            	 $("#list_content").html("");
	            	 var html="";
	            	 jQuery.each(data.result.list,function(i,val){
	            		 html+="<tr>";
	            		 html+="<td>";
	            		 html+=val.id;
	            		 html+="</td>";
	            		 html+="<td>";
	            		 html+=val.schoolClass==null?"":val.schoolClass.classname;
	            		 html+="</td>";
	            		 html+="<td>";
	            		 html+=val.title;
	            		 html+="</td>";
	            		 html+="<td>";
	            		 html+="<div class='floatLeft'><button type='button' class='btn btn-block btn-success btnWidthSmall btn-sm modifyBtn' classname='"+val.classname+"' description='"+val.description+"' dataid='"+val.id+"'>查看</button></div>";
	            		 html+="<div class='floatLeft actionBtnMarginLeft'><button type='button' class='btn btn-block btn-warning btnWidthSmall btn-sm deleteBtn' title='"+val.title+"' dataid='"+val.id+"'>删除</button></td></div>";
	            		 html+="</td>";
	            		 html+="</tr>";
	            	 });
	            	 $("#list_content").html(html);
	            	 
	            	 $(".modifyBtn").on("click", function(){
	            		 $("#modal_title").text("点名查看");
	            		 
	            		 var dataid = $(this).attr("dataid");
	            		 $("#classiddiv").css("display","none");
     	       		 	 $("#schoolclassdiv").css("display","");
     	       		 	 
     	       		 	 $("#btnSave").css("display","none");
     	       		 	 
	            		 var obj = new Object();
	            		 obj.id=dataid;
	            		 $.ajax({
	            	         type: "post",
	            	         url: '../calltheroll/getcalltherollbyid',
	            	         async: false, // 使用同步方式
	            	         data: JSON.stringify(obj),
	            	         contentType: "application/json; charset=utf-8",
	            	         dataType: "json",
	            	         success: function(data) {
	            	        	 var html="";
	            	        	 $("#studentsList").html("");
	            	        	 jQuery.each(data.result,function(i,val){
	            	        	 	var title = val.callTheRoll.title;
	            	        	 	$("#title").val(title);
	            	        	 	$("#schoolclass").val(val.callTheRoll.schoolClass.classname);
	            	        	 	
	           	            		html+="<div class='checkbox'>";
	           	            		html+="<label style='height:20px;'>";
	                       			html+="<input type='checkbox' ";
	                       			html+=(val.confirm==1?"checked":"");
	                       			html+=" style='height:20px;width:20px;' studentid='"+val.id+"' disabled>";
	                       			html+=val.student.studentname;
                      				html+="</label>";
                  					html+="</div>";
		           	            	 
	            	        	 });

	            	        	 $("#studentsList").html(html);
	            	         }
	            		 });
	            		 $("#modal-default").modal();
	            	 });
	            	 
	            	 $(".deleteBtn").on("click", function(){
	            		 var dataid = $(this).attr("dataid");
	            		 var title = $(this).attr("title");
	            		 confirm("确定删除吗?", "即将删除一条数据["+title+"]", function (isConfirm) {
	                         if (isConfirm) {
	                        	 var deleteObj = new Object();
	                        	 deleteObj.id = dataid;
	                        	 $.ajax({
	     	                        type: "post",
	     	                        url: '../calltheroll/deletecalltherollbyid',
	     	                        async: false, // 使用同步方式
	     	                        data: JSON.stringify(deleteObj),
	     	                        contentType: "application/json; charset=utf-8",
	     	                        dataType: "json",
	     	                        success: function(data) {
	     	                            if(data.code==1)
	     	                           	{
	     	                            	getData(queryObj.page);
	     	                            	toastr.success(data.message, '提示');
	     	                           	}
	     	                            else{
	     	                            	toastr.error(data.message, '提示');
	     	                            }
	     	                        }
	     	                     }); 
	                         } else {
	                           
	                         }
	                     }, {confirmButtonText: '对，没错!', cancelButtonText: '不，我再想想!', width: 400});
	     
	            	 });
	            	 
	            	 isFirstLoad++;
	            	 var currentPage = queryObj.page + 1;
            		 $('.M-box').pagination({
            			 	current: currentPage,
            			    totalData: data.result.count,
            			    showData: data.result.pagesize,
            			    coping: true,
            			    count:4,
            			    mode:"fixed",
            			    homePage: '首',
            			    endPage: '末',
            			    prevContent: '<',
            			    nextContent: '>',
            			    callback:pageCallBack
           			 });
	            
	           	 }
	             else{
	            	 toastr.error(data.message, '提示');
	             }
	         }
	      });
	 }
	 $(".czxxmenu").removeClass("active");
	 $(".czxxmenu").removeClass("menu-open");
	 $("#calltherollmanagement_menu").addClass("active");
	 $("#callmanagement_menu").addClass("active");
	 $("#maintitle").text("点名管理");
	 
	 $("#newBtn").click(function(){
		 $("#modal_title").text("开始点名");
		 $("#dataid").val("0");
		 $("#title").val("");
		 $("#classid").find("option[value=0]").prop("selected",true);
		 $("#classiddiv").css("display","");
		 $("#schoolclassdiv").css("display","none");
		 $("#studentsList").html("");
		 $("#btnSave").css("display","");
		 $("#modal-default").modal();
	 });
	

	 $("#btnSave").click(function(){
		 var dataid = $("#dataid").val();
		 var title = $("#title").val();
		 var classid = $("#classid").val();
		 
		 var studentids = new Array();
		 var confirms = new Array();
		 $("#studentsList :checkbox").each(function(){
			 studentids.push($(this).attr("studentid"));
			 if($(this).prop("checked"))
			 {
				 confirms.push(1);
			 }else{
				 confirms.push(0);
			 }
		 });
		 
		 var obj = new Object();
		 obj.id = 0;
		 obj.title = title;
		 obj.classid = classid;
		 obj.studentids = studentids;
		 obj.confirms = confirms;
		 
		 $.ajax({
            type: "post",
            url: '../calltheroll/createoreditcalltheroll',
            async: false, // 使用同步方式
            data: JSON.stringify(obj),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data) {
                if(data.code==1)
               	{
                	getData(queryObj.page);
                	toastr.success(data.message, '提示');
               		$("#modal-default").modal("hide");
               	}
                else{
                	toastr.error(data.message, '提示');
                }
            }
         });
	 });
	 
	 
 });
 
 
 
 </script>
<%@ include  file="../shared/footer2.jsp"%>
