<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include  file="../shared/header.jsp"%>
<div id="addCommodityIndex">
             <!--input-group start-->
            <div class="input-group row">
                <div class="col-sm-12">
                    <label>用户头像</label>
                </div>
                <br/>
                <form action="<%=path%>/account/upload" method="post" enctype="multipart/form-data" id="uploadForm">
	                <input type="hidden" name="accountid" value="${sessionScope.ACCOUNT_SESSION.id}" id="accountid"/>
	                <div class="col-sm-9 big-photo">
	                	<div id="preview">
	                        <img id="imghead" border="0" src="<%=path%>${sessionScope.ACCOUNT_SESSION.headimage }" width="90" height="90" onclick="$('#previewImg').click();">
	                     </div>         
	                    <input type="file" onchange="previewImage(this)" style="display: none;" id="previewImg" name="file">
	                	<!--<input id="uploaderInput" class="uploader__input" style="display: none;" type="file" accept="" multiple="">-->
	                </div>
	                <div class="row">
	                	<div class="col-sm-12">
	                		<button type="button" class="btn btn-primary" id="btnSave">保存</button>
	                	</div>
	                </div>
                </form>
            </div>
            <!--input-group end-->
       
</div>
<%@ include  file="../shared/footer.jsp"%>
<script>

$(function(){
	$("#btnSave").click(function(){
         var id = $("#accountid").val().trim();
         var file = document.getElementById("previewImg").files[0];
         var formData = new FormData();
         formData.append('id', id);
         formData.append('file', file);

         $.ajax({
             url: "../account/upload",
             type: "post",
             data: formData,
             contentType: false,
             processData: false,
             mimeType: "multipart/form-data",
             dataType: "json",
             success: function (data) {
            	 //toastr.success(data.message, '提示');
                 window.location.reload();
             },
             error: function (data) {
            	 alert(data.message);
             }
         });
	});
});
      //图片上传预览    IE是用了滤镜。
        function previewImage(file)
        {
          var MAXWIDTH  = 90; 
          var MAXHEIGHT = 90;
          var div = document.getElementById('preview');
          if (file.files && file.files[0])
          {
              div.innerHTML ='<img id=imghead onclick=$("#previewImg").click()>';
              var img = document.getElementById('imghead');
              img.onload = function(){
                var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                img.width  =  rect.width;
                img.height =  rect.height;
				//img.style.marginLeft = rect.left+'px';
                img.style.marginTop = rect.top+'px';
              }
              var reader = new FileReader();
              reader.onload = function(evt){img.src = evt.target.result;}
              reader.readAsDataURL(file.files[0]);
          }
          else //兼容IE
          {
            var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
            file.select();
            var src = document.selection.createRange().text;
            div.innerHTML = '<img id=imghead>';
            var img = document.getElementById('imghead');
            img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
            div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
          }
        }
        function clacImgZoomParam( maxWidth, maxHeight, width, height ){
            var param = {top:0, left:0, width:width, height:height};
            if( width>maxWidth || height>maxHeight ){
                rateWidth = width / maxWidth;
                rateHeight = height / maxHeight;
                
                if( rateWidth > rateHeight ){
                    param.width =  maxWidth;
                    param.height = Math.round(height / rateWidth);
                }else{
                    param.width = Math.round(width / rateHeight);
                    param.height = maxHeight;
                }
            }
            param.left = Math.round((maxWidth - param.width) / 2);
            param.top = Math.round((maxHeight - param.height) / 2);
            return param;
        }
</script>
<%@ include  file="../shared/footer2.jsp"%>