<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <!DOCTYPE html>
  <html>

  <head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <meta charset="UTF-8" />
    <title>Insert title here</title>

    <script>
    const showImage = fileCallPath => {
        alert(fileCallPath);
        $('.bigPictureWrapper').css("display", "flex").show();
        $(".bigPicture").html("<img src='/display?fileName="+encodeURI(fileCallPath)+"'>").animate({width:'100%', height:'100%'});
      }
      $(document).ready(function () {

        $(".uploadResult").on("click", "span", function (e) {
          let targetFile = $(this).data("file");
          let type = $(this).data("type");
          console.log(type);
          $.ajax({
            type: "post",
            url: "/deleteFile",
            data: {
              fileName:targetFile,
              type:type
            },
            dataType: "text",
            success: function (result) {
              alert(result);
            }
          });
        });
        
        $('.bigPictureWrapper').on("click", function (e) {
          $(".bigPicture").animate({width:'0%', height:'0%'},1000);
          setTimeout(() => {
            $('.bigPictureWrapper').hide();
          }, 1000);
        });

        //let cloneObj = $('.uploadDiv').clone();
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

        let uploadResult = $(".uploadResult ul");
       

        const showUploadedFile = uploadResultArr => {
          let str = "";
          $(uploadResultArr).each(function (i, obj) {
            console.log(uploadResult);
            console.log(obj.fileType);
            if (!obj.fileType) {
              let fileCallPath =
                encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);
              str += "<li><div><a href='/download?fileName=" + fileCallPath + "'><img src='/resources/images/attacth.png'>" + obj.fileName + "</a>"
                +"<span data-file=\'"+fileCallPath+"\' data-type='file'>X</span></div>"
                +"</li> ";
            } else {
              //  str +="<li>"+obj.fileName+"<li>";
              let fileCallPath =
                encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
              let originPath = obj.uploadPath + "/" + obj.uuid + "_"+obj.fileName;
              originPath = originPath.replace(new RegExp(/\\/g), "/");// 구분자 통일하기

              str += "<li><a href=\"javascript:showImage(\'" + originPath + "\')\"> <img src='/display?fileName="+fileCallPath+"'></a>"
                +"<span data-file=\'"+fileCallPath+"\' data-type='image'>X</span>"
                +"</li> ";
            }
          });
          uploadResult.append(str);
        } //showUploadFile

        $("#uploadBtn").on("click", function (e) {
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
              //$('.uploadDiv').find("input[type='file']").val("");
              let cloneObj = $('.uploadDiv').clone();
              $(".uploadDiv").html(cloneObj.html());
              console.log(result);
              showUploadedFile(result);
            },//success
          }); //ajax
        }); //button event
      }); //document


    </script>
    <style>
      .uploadResult {
        width: 100%;
        background-color: #ddd;
      }

      .uploadResult ul {
        display: flex;
        flex-flow: row;
        justify-content: center;
        align-items: center;
      }

      .uploadResult ul li {
        list-style: none;
        padding: 10px;
      }

      .uploadResult ul li img {
        width: 20px;
      }

      .uploadResult ul li span {
        color: white;
      }

      .bigPictureWrapper {
        position: absolute;
        display: none;
        justify-content: center;
        align-items: center;
        top: 0%;
        width: 100%;
        height: 100%;
        background-color: gray;
        z-index: 100;
        background: rgba(255, 255, 255, 0.5);
      }

      .bigPicture {
        position: relative;
        display: flex;
        justify-content: center;
        align-items: center;
      }

      .bigPicture img {
        width: 400px;
      }
    </style>
  </head>

  <body>
    <div class="uploadDiv">
      <input type="file" name="uploadFile" multiple="multiple" />
    </div>
    <button id="uploadBtn">Upload</button>

    <div class="uploadResult">
      <ul></ul>
    </div>
    <div class="bigPictureWrapper">
    	<div class="bigPicture"></div>
    </div>
  </body>

  </html>