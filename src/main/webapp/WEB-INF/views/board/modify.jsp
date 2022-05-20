<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                         		<input class="form-control" name="title" value='<c:out value="${board.title }"></c:out>'>
                         	</div>
                       		<div class="form-group">
                         		<label>Content</label>
                         		<textarea rows="3" name="content" class="form-control">${board.content }</textarea>
                         	</div>
                         	<div class="form-group">
                         		<label>Writer</label>
                         		<input class="form-control" name="writer" value='<c:out value="${board.writer }"></c:out>'>
                         	</div>
                         	<div class="form-group" hidden="">
                         		<label>bno</label>
                         		<input class="form-control" name="bno" value='<c:out value="${board.bno }"></c:out>' hidden="">
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
                    let formObj = $("form");
                    $('button').on("click", function (e) {
                        e.preventDefault();
                        let operation = $(this).data("costom");
                        console.log(e);
                        console.log($(this));
                        console.log(this);
                        alert(operation);
                        console.log(operation);
                        if (operation === 'remove') {
                            formObj.attr("action","/board/remove");
                        }
                        if (operation === 'list') {
                            self.location = "/board/list"
                        }
                       formObj.submit();
                    });
                    document.getElementsByName("content")[0].focus();
                });
                
            </script>
           
 <%@include file="../includes/footer.jsp" %>       