<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
			<%@include file="../includes/header.jsp" %>
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Page</h1>
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								게시글 작성
							</div>
							<!-- /.panel-heading -->
							<div class="panel-body">

								<form action="/board/register" method="POST" role="form">
									<div class="form-group">
										<label>Title</label>
										<input class="form-control" name="title">
									</div>
									<div class="form-group">
										<label>Content</label>
										<textarea rows="3" name="content" class="form-control"></textarea>
									</div>
									<div class="form-group">
										<label>Writer</label>
										<input class="form-control" name="writer">
									</div>

									<button type="submit" class="btn btn-default">Submit</button>
									<button type="reset" class="btn btn-default">Reset</button>
								</form>

							</div>
							<!-- /.panel-body -->
						</div>
						<!-- /.panel -->
						<div class="row">
							<div class="col-lg-12">
								<div class="panel panel-info">
									<div class="panel-heading">File Attach</div>
									<div class="panel-body">
										<div class="form-group uploadDiv">
											<input type="file" name='uploadFile' multiple>
										</div>
										<div class="form-group uploadResult">
											<ul></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
						
					</div>
					<!-- /.col-lg-6 -->
				</div>
				<!-- /.row -->
				<script type="text/javascript">
					const showImage = fileCallPath => {
						alert(fileCallPath);
						$('.bigPictureWrapper').css("display", "flex").show();
						$(".bigPicture").html("<img src='/display?fileName=" + encodeURI(fileCallPath) + "'>").animate({ width: '100%', height: '100%' });
					}
					$(function () {
						let uploadResult = $(".uploadResult ul");
						$(".uploadResult").on("click", "button", function (e) {
							let targetFile = $(this).data("file");
							let type = $(this).data("type");
							let targetLi = $(this).closest("li");
							console.log(type);
							$.ajax({
								type: "post",
								url: "/deleteFile",
								data: {
									fileName: targetFile,
									type: type
								},
								dataType: "text",
								success: function (result) {
									alert(result);
									targetLi.remove();
									$('.uploadDiv').find("input[type='file']").val("");
								}
							});
						});

						

						const showUploadedFile = uploadResultArr => {
							let str = "";
							  $(uploadResultArr).each(function(i, obj){
		            			   if(!obj.fileType){
		            				   var fileCallPath  = encodeURIComponent(obj.uploadpath+"/"+obj.uuid+"_"+obj.fileName);
		            				   str +="<li data-path='"+obj.uploadpath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'><div>";
		            				   str +="<span>"+obj.fileName+"</span>";
		            				   str +="<button type='button' data-file=\'"+fileCallPath+"\' data-type='file'  class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
		            				   str +="<img src='/resources/images/attach.png'></a>"
		            				   str +="</div></li>";
		            				}else{
		            				   var fileCallPath = encodeURIComponent(obj.uploadpath+"/s_"+obj.uuid+"_"+obj.fileName);
		            				   str +="<li data-path='"+obj.uploadpath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'><div>";
		            				   str +="<span>"+obj.fileName+"</span>";
		            				   str +="<button type='button' data-file=\'"+fileCallPath+"\' data-type='image'  class='btn btn-warning btn-circle'> <i class='fa fa-times'></i></button><br>";
		            				   str +="<img src='/display?fileName="+fileCallPath+"'>"; 
		            				   str +="</div></li>";
		            				}

		            		   });
							uploadResult.append(str);
						} //showUploadFile
						var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
						var maxSize = 5242880;
						function checkExtension(fileName, fileSize) {
							if (fileSize >= maxSize) {
								alert("파일 크기 초과");
								return false;
							}
							if (regex.test(fileName)) {
								alert("해당 종류의 파일은 업로드 할 수 없음");
								return false;
							}
							return true;
						}
						var formObj = $("form[role='form']")
						

						$("input[type='file']").change(function (e) {
							alert("update");
							var formData = new FormData();
							var inputFile = $("input[name='uploadFile']");
							var files = inputFile[0].files;
							console.log(files);

							for (var i = 0; i < files.length; i++) {
								if (!checkExtension(files[i].name, files[i].size)) {
									return false;
								}
								formData.append("uploadFile", files[i]);
							}
							console.log("files.lengh : " + files.length);
							$.ajax({
								url: "/uploadAjaxAction",
								processData: false,
								contentType: false,
								data: formData,
								type: "POST",
								dataType: 'json',
								success: function (result) {
									//  alert("Uploaded");

									//let cloneObj = $('.uploadDiv').clone();
									//$(".uploadDiv").html(cloneObj.html());
									console.log(result);
									showUploadedFile(result);
								},//success
							}); //ajax
						});
						$("button[type='submit']").on("click", function (e) {
							e.preventDefault();
							console.log("submit clicked");

							let str = "";
							$(".uploadResult ul li").each(function (i, obj) {
								let jobj = $(obj);
								console.dir(jobj);
								str += "<input type='hidden' name='attachList[" + i + "].fileName' value='" + jobj.data("filename") + "'>";
								str += "<input type='hidden' name='attachList[" + i + "].uuid' value='" + jobj.data("uuid") + "'>";
								str += "<input type='hidden' name='attachList[" + i + "].uploadpath' value='" + jobj.data("path") + "'>";
								str += "<input type='hidden' name='attachList[" + i + "].fileType' value='" + jobj.data("type") + "'>";
							});
							formObj.append(str);  formObj.submit();
						

						}); // submit button event
						
					});

				</script>
				<%@include file="../includes/footer.jsp" %>