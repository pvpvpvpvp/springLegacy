<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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

						<form action="/board/modify" method="POST" role="form">
							<div class="form-group">
								<label>Title</label>
								<input class="form-control" name="title"
									value='<c:out value="${board.title }"></c:out>'>
							</div>
							<div class="form-group">
								<label>Content</label>
								<textarea rows="3" name="content" class="form-control">${board.content }</textarea>
							</div>
							<div class="form-group">
								<label>Writer</label>
								<input class="form-control" name="writer"
									value='<c:out value="${board.writer }"></c:out>'>
							</div>
							<div class="form-group" hidden="">
								<label>bno</label>
								<input class="form-control" name="bno" value='<c:out value="${board.bno }"></c:out>'
									hidden="">
							</div>
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
											<div class="" id="attachList">
												<ul>
													<div class="bigPictureWrapper">
														<div class="bigPicture"></div>
													</div>
												</ul>
											</div>
											<div class="form-group showResult">
												<ul></ul>
											</div>
											<div class="" id="attachList">
												<ul>
													<div class="bigPictureWrapper">
														<div class="bigPicture"></div>
													</div>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
							<button type="submit" data-costom="submit" class="btn btn-default">Submit</button>
							<button type="submit" data-costom="remove" class="btn btn-default">Remove</button>
							<button type="submit" data-costom="list" class="btn btn-default">List</button>
						</form>

					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-6 -->
		</div>
		<!-- /.row -->
		<script type="text/javascript">
			$(function () {
				var bnoValue = `${board.bno}`;

				let formObjF = $("form");
				$('button').on("click", function (e) {
					e.preventDefault();
					let operation = $(this).data("costom");
					console.log(e);
					console.log($(this));
					console.log(this);
					alert(operation);
					console.log(operation);
					if (operation === 'remove') {
						formObjF.attr("action", "/board/remove");
					}
					if (operation === 'list') {
						self.location = "/board/list"
					}
					let str = "";
					$(".uploadResult ul li").each(function (i, obj) {
						let jobj = $(obj);
						console.dir(jobj);
						str += "<input type='hidden' name='attachList[" + i + "].fileName' value='" + jobj.data("filename") + "'>";
						str += "<input type='hidden' name='attachList[" + i + "].uuid' value='" + jobj.data("uuid") + "'>";
						str += "<input type='hidden' name='attachList[" + i + "].uploadpath' value='" + jobj.data("path") + "'>";
						str += "<input type='hidden' name='attachList[" + i + "].fileType' value='" + jobj.data("type") + "'>";
					});
					formObjF.append(str); formObjF.submit();
				});
				document.getElementsByName("content")[0].focus();


				const showImage = fileCallPath => {
					alert(fileCallPath);
					$('.bigPictureWrapper').css("display", "flex").show();
					$(".bigPicture").html("<img src='/display?fileName=" + encodeURI(fileCallPath) + "'>").animate({ width: '100%', height: '100%' });
				}
				let uploadResult = $(".uploadResult ul");
				let showResult = $(".showResult ul");
				$(".showResult").on("click", "button", function (e) {
					let targetFile = $(this).data("file");
					let type = $(this).data("type");
					let targetLi = $(this).closest("li");
					let targetUuid = $(this).data("uuid");
					console.log(targetUuid);
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
					$.ajax({
						type: "delete",
						url: "/board/deleteFileTable/"+targetUuid,
						dataType: "text",
						success: function (result) {
							alert(result);
							targetLi.remove();
							$('.uploadDiv').find("input[type='file']").val("");
						}
					});
				});
				$(".uploadResult").on("click", "button", function (e) {
					let targetFile = $(this).data("file");
					let type = $(this).data("type");
					let targetLi = $(this).closest("li");
					let targetUuid = $(this).data("uuid");
					console.log(targetUuid);
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


				const showUploadedFile = (uploadResultArr,type) => {
					let str = "";
					$(uploadResultArr).each(function (i, obj) {
						if (!obj.fileType) {
							var fileCallPath = encodeURIComponent(obj.uploadpath + "/" + obj.uuid + "_" + obj.fileName);
							str += "<li data-path='" + obj.uploadpath + "' data-uuid='" + obj.uuid + "' data-filename='" + obj.fileName + "' data-type='" + obj.fileType + "'><div>";
							str += "<span>" + obj.fileName + "</span>";
							str += "<button type='button' data-file=\'" + fileCallPath + "\' data-type='file'  data-uuid='" + obj.uuid + "'  class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
							str += "<img src='/resources/images/attach.png'></a>"
							str += "</div></li>";
						} else {
							var fileCallPath = encodeURIComponent(obj.uploadpath + "/s_" + obj.uuid + "_" + obj.fileName);
							str += "<li data-path='" + obj.uploadpath + "' data-uuid='" + obj.uuid + "' data-filename='" + obj.fileName + "' data-type='" + obj.fileType + "'><div>";
							str += "<span>" + obj.fileName + "</span>";
							str += "<button type='button' data-file=\'" + fileCallPath + "\' data-type='image' data-uuid='" + obj.uuid + "'   class='btn btn-warning btn-circle'> <i class='fa fa-times'></i></button><br>";
							str += "<img src='/display?fileName=" + fileCallPath + "'>";
							str += "</div></li>";
						}

					});
					if (type==="showResult") {
						showResult.append(str);
					}
					if (type==="uploadResult") {
						uploadResult.append(str);
					}
					
				} //showUploadFile
				$.ajax({
					type: "get",
					url: `/board/getAttachList?bno=${'${bnoValue}'}`,
					dataType: "json",
					success: function (response) {
						console.log(`response`, response);
						showUploadedFile(response,"showResult");
					}
				});
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
				var formObj = $("form[role='form']");


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
							showUploadedFile(result,"uploadResult");
						},//success
					}); //ajax
				});
			});


		</script>

		<%@include file="../includes/footer.jsp" %>