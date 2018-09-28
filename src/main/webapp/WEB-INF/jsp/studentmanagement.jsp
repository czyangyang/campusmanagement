<%@ include  file="../shared/header.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 屏蔽tomcat 自带的 EL表达式 -->
<%@ page isELIgnored="false" %>

<div class="row">
 <div class="col-xs-12">
   <div class="box">
     <div class="box-header">
     <div>
     <table>
       <tr>
       <td><button type="button" class="btn btn-block btn-primary btnWidthNormal" data-toggle="modal" id="newBtn" >新增</button></td>
       <td><button type="button" class="btn btn-block btn-primary btnWidthNormal" id="importBtn">导入</button></td>
       <td><button type="button" class="btn btn-block btn-primary btnWidthNormal" id="exportBtn">导出</button></td>
       </tr>
     </table>
     </div>
     </div>
     <!-- /.box-header -->
     <div class="box-body">
       <table id="example2" class="table table-bordered table-hover">
         <thead>
         <tr>
           <th style="width:9%">序号</th>
           <th style="width:11%">头像</th>
           <th style="width:20%">学生姓名</th>
           <th style="width:9%">性别</th>
           <th style="width:9%">年龄</th>
           <th style="width:21%">班级</th>
           <th style="width:21%">年级</th>
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
           <label for="studentname">学生名</label>
           <input type="text" class="form-control textAlignLeft" id="studentname" name="studentname" placeholder="请输入学生名">
         </div>
         <div class="form-group">
           <label for="sex">性别</label>
           <select class="form-control" name="sex" id="sex">
             <option value="0" selected>请选择</option>
             <option value="1">男</option>
             <option value="2">女</option>
 			</select>
           <!-- <input type="text" class="form-control" id="sex" name="sex" placeholder="请输入性别"> -->
         </div>
         <div class="form-group">
           <label for="age">年龄</label>
           <input type="text" class="form-control textAlignLeft" id="age" name="age" placeholder="请输入年龄">
         </div>
         <div class="form-group">
           <label for="classid">班级</label>
           <select class="form-control" name="classid" id="classid">
             <option value="0" selected>请选择</option>
             <c:forEach items="${schoolclassList.list}" var="schoolclass">
   				<option value="<c:out value="${schoolclass.id}"/>"><c:out value="${schoolclass.classname}"/></option>
 			</c:forEach>
           </select>
           <!-- <input type="text" class="form-control" id="classid" name="classid" placeholder="请输入班级"> -->
         </div>
         <div class="form-group">
           <label for="classid">班级</label>
           <select class="form-control" name="gradeid" id="gradeid">
           	 <option value="0" selected>请选择</option>
             <c:forEach items="${gradeList.list}" var="grade">
   				<option value="<c:out value="${grade.id}"/>"><c:out value="${grade.gradename}"/></option>
 			</c:forEach>
           </select>
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

<div class="modal fade" id="modal_import">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modal_title_import"></h4>
      </div>
      <div class="modal-body">
      <form action="../studentmanagement/readexcel" enctype="multipart/form-data" method="post">
      	
         <div class="form-group">
         	 <label id="realBtn" class="btn btn-info">
    	  	 <input type="file" onchange="importf(this)" id="fileInput1" name="excelFile" class="mFileInput" style="left:-9999px;position:absolute;" >
     	  	 <span>导入EXCEL数据</span>
		  </label>
         </div>
        
      	<!-- <input type="file" onchange="importf(this)" /> -->
        <div id="demo"></div>
	  
      <div class="modal-footer">
        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">关闭</button>
        <button type="submit" class="btn btn-primary">导入</button>
      </div>
      </form>
    </div>
    <!-- /.modal-content -->
  </div>
  <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
        
<%@ include  file="../shared/footer.jsp"%>
<script src="https://cdn.bootcss.com/xlsx/0.12.12/xlsx.core.min.js"></script>
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
	         url: '../student/getallstudent',
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
	            		 html+="<img src='<%=path%>"+val.headimage+"' width='25' height='25'>";
	            		 html+="</td>";
	            		 html+="<td>";
	            		 html+=val.studentname;
	            		 html+="</td>";
	            		 html+="<td>";
	            		 var sex="";
	            		 if(val.sex==1)
            			 {
            			 	sex="男";
            			 }
	            		 if(val.sex==2)
            			 {
            			 	sex="女";
            			 }
	            		 html+=sex;
	            		 html+="</td>";
	            		 html+="<td>";
	            		 html+=val.age;
	            		 html+="</td>";
	            		 html+="<td>";
	            		 html+=val.schoolClass==null?"":val.schoolClass.classname;
	            		 html+="</td>";
	            		 html+="<td>";
	            		 html+=val.grade==null?"":val.grade.gradename;
	            		 html+="</td>";
	            		 html+="<td>";
	            		 html+="<div class='floatLeft'><button type='button' class='btn btn-block btn-success btnWidthSmall btn-sm modifyBtn' studentname='"+val.studentname+"' sex='"+val.sex+"' age='"+val.age+"' classid='"+val.classid+"' gradeid='"+val.gradeid+"' dataid='"+val.id+"'>修改</button></div>";
	            		 html+="<div class='floatLeft actionBtnMarginLeft'><button type='button' class='btn btn-block btn-warning btnWidthSmall btn-sm deleteBtn' classname='"+val.classname+"' dataid='"+val.id+"'>删除</button></td></div>";
	            		 html+="</td>";
	            		 html+="</tr>";
	            	 });
	            	 $("#list_content").html(html);
	            	 
	            	 $(".modifyBtn").on("click", function(){
	            		 $("#modal_title").text("编辑学生");
	            		 var dataid = $(this).attr("dataid");
	            		 var studentname = $(this).attr("studentname");
	            		 var sex = $(this).attr("sex");
	            		 var age = $(this).attr("age");
	            		 var classid = $(this).attr("classid");
	            		 var gradeid = $(this).attr("gradeid");
	            		 $("#dataid").val(dataid);
	            		 $("#studentname").val(studentname);
	            		 /* $("#sex").val(sex); */
	            		 $("#sex").find("option[value="+sex+"]").prop("selected",true);
	            		 $("#age").val(age);
	            		 /* $("#classid").val(classid);
	            		 $("#gradeid").val(gradeid); */
	            		 $("#classid").find("option[value="+classid+"]").prop("selected",true);
	            		 $("#gradeid").find("option[value="+gradeid+"]").prop("selected",true);
	            		 $("#modal-default").modal();
	            	 });
	            	 
	            	 $(".deleteBtn").on("click", function(){
	            		 var dataid = $(this).attr("dataid");
	            		 var studentname = $(this).attr("studentname");
	       
	            		 confirm("确定删除吗?", "即将删除一条数据["+classname+"]", function (isConfirm) {
	                         if (isConfirm) {
	                        	 var deleteObj = new Object();
	                        	 deleteObj.id = dataid;
	                        	 $.ajax({
	     	                        type: "post",
	     	                        url: '../student/deletestudentbyid',
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
	 $("#studentmanagement_menu").addClass("active");
	 $("#maintitle").text("学生管理");
	 
	 $("#newBtn").click(function(){
		 $("#modal_title").text("新增学生");
		 $("#dataid").val("0");
		 $("#studentname").val("");
		 //$("#sex").val("");
		 $("#sex").find("option[value=0]").prop("selected",true);
		 $("#age").val("");
		 //$("#classid")
		 $("#classid").find("option[value=0]").prop("selected",true);
		 //$("#gradeid").val("");
		 $("#gradeid").find("option[value=0]").prop("selected",true);
		 $("#modal-default").modal();
	 });
	

	 $("#btnSave").click(function(){
		 /* alert(JSON.stringify($('#dataForm').serializeJSON()));
		 return; */
		 //alert($("#dataForm").serialize());
		 
		 var dataid = $("#dataid").val();
		 
		 $.ajax({
            type: "post",
            url: '../student/createoreditstudent',
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
	 
	 $("#importBtn").click(function(){
		 $("#demo").html("");
		 $("#modal_title_import").text("新增学生");
		 $("#modal_import").modal();
	 });
	 
	 $("#exportBtn").click(function(){
		 window.location.href="../studentmanagement/exportexcel";
	 });
 });
 </script>
 <script>
            /*
            FileReader共有4种读取方法：
            1.readAsArrayBuffer(file)：将文件读取为ArrayBuffer。
            2.readAsBinaryString(file)：将文件读取为二进制字符串
            3.readAsDataURL(file)：将文件读取为Data URL
            4.readAsText(file, [encoding])：将文件读取为文本，encoding缺省值为'UTF-8'
                         */
            var wb;//读取完成的数据
            var rABS = false; //是否将文件读取为二进制字符串
            function importf(obj) {//导入
                if(!obj.files) {
                    return;
                }
                var f = obj.files[0];
                var reader = new FileReader();
                reader.onload = function(e) {
                    var data = e.target.result;
                    if(rABS) {
                        wb = XLSX.read(btoa(fixdata(data)), {//手动转化
                            type: 'base64'
                        });
                    } else {
                        wb = XLSX.read(data, {
                            type: 'binary'
                        });
                    }
                    //wb.SheetNames[0]是获取Sheets中第一个Sheet的名字
                    //wb.Sheets[Sheet名]获取第一个Sheet的数据
                    document.getElementById("demo").innerHTML= JSON.stringify( XLSX.utils.sheet_to_html(wb.Sheets[wb.SheetNames[0]]) );
                };
                if(rABS) {
                    reader.readAsArrayBuffer(f);
                } else {
                    reader.readAsBinaryString(f);
                }
            }
            function fixdata(data) { //文件流转BinaryString
                var o = "",
                    l = 0,
                    w = 10240;
                for(; l < data.byteLength / w; ++l) o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w, l * w + w)));
                o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w)));
                return o;
            }
        </script>
<%@ include  file="../shared/footer2.jsp"%>
