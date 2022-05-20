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
                     	
                         
                       	<div class="form-group">
                       		<label>Title</label>
                       		<input class="form-control" name="title" value='<c:out value="${board.title }"></c:out>' readonly="readonly">
                       	</div>
                     		<div class="form-group">
                       		<label>Content</label>
                       		<textarea rows="3" name="content" class="form-control" readonly="readonly"><c:out value="${board.content }"></c:out></textarea>
                       	</div>
                       	<div class="form-group">
                       		<label>Writer</label>
                       		<input class="form-control" name="writer" value='<c:out value="${board.writer }"></c:out>' readonly="readonly">
                       	</div>
                       	<button data-oper='modify' onclick="location.href='/board/modify?bno=<c:out value="${board.bno }"/>'" class="btn btn-default">Modify</button>
                       	<a href='/board/list' class="btn btn-default">List</a>
                         
                         
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
            </div>
            <!-- /.row -->
 <%@include file="../includes/footer.jsp" %>       