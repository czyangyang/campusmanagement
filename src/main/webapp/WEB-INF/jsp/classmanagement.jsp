<%@ include  file="../shared/header.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="row">
 <div class="col-xs-12">
   <div class="box">
     <div class="box-header">
       <button type="button" class="btn btn-block btn-primary btnWidthNormal" data-toggle="modal" id="newBtn" >新增</button>
     </div>
     <!-- /.box-header -->
     <div class="box-body">
       <table id="example2" class="table table-bordered table-hover">
         <thead>
         <tr>
           <th style="width:9%">序号</th>
           <th style="width:31%">班级名称</th>
           <th style="width:60%">描述</th>
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
           <label for="classname">班级名</label>
           <input type="text" class="form-control textAlignLeft" id="classname" name="classname" placeholder="请输入班级名">
         </div>
         <div class="form-group">
           <label for="description">班级描述</label>
           <input type="text" class="form-control textAlignLeft" id="description" name="description" placeholder="请输入班级描述">
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
	 
	 function getData(page)
	 {
		 queryObj.page = page;

		 $.ajax({
	         type: "post",
	         url: '../schoolclass/getallschoolclass',
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
	            		 html+=val.classname;
	            		 html+="</td>";
	            		 html+="<td>";
	            		 html+=val.description;
	            		 html+="</td>";
	            		 html+="<td>";
	            		 html+="<div class='floatLeft'><button type='button' class='btn btn-block btn-success btnWidthSmall btn-sm modifyBtn' classname='"+val.classname+"' description='"+val.description+"' dataid='"+val.id+"'>修改</button></div>";
	            		 html+="<div class='floatLeft actionBtnMarginLeft'><button type='button' class='btn btn-block btn-warning btnWidthSmall btn-sm deleteBtn' classname='"+val.classname+"' dataid='"+val.id+"'>删除</button></td></div>";
	            		 html+="</td>";
	            		 html+="</tr>";
	            	 });
	            	 $("#list_content").html(html);
	            	 
	            	 $(".modifyBtn").on("click", function(){
	            		 $("#modal_title").text("编辑班级");
	            		 var dataid = $(this).attr("dataid");
	            		 var classname = $(this).attr("classname");
	            		 
	            		 var description = $(this).attr("description");
	            		 $("#dataid").val(dataid);
	            		 $("#classname").val(classname);
	            		 $("#description").val(description);
	            		 $("#modal-default").modal();
	            	 });
	            	 
	            	 $(".deleteBtn").on("click", function(){
	            		 var dataid = $(this).attr("dataid");
	            		 var classname = $(this).attr("classname");
	       
	            		 confirm("确定删除吗?", "即将删除一条数据["+classname+"]", function (isConfirm) {
	                         if (isConfirm) {
	                        	 var deleteObj = new Object();
	                        	 deleteObj.id = dataid;
	                        	 $.ajax({
	     	                        type: "post",
	     	                        url: '../schoolclass/deleteschoolclassbyid',
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
	 $("#basedatamanagement_menu").addClass("active");
	 $("#classmanagement_menu").addClass("active");
	 $("#maintitle").text("班级管理");
	 
	 $("#newBtn").click(function(){
		 $("#modal_title").text("新增班级");
		 $("#dataid").val("0");
		 $("#classname").val("");
		 $("#description").val("");
		 $("#modal-default").modal();
	 });
	

	 $("#btnSave").click(function(){
		 var dataid = $("#dataid").val();
		 
		 $.ajax({
            type: "post",
            url: '../schoolclass/createoreditschoolclass',
            async: false, // 使用同步方式
            data: JSON.stringify($('#dataForm').serializeJSON()),
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
