<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                     	
                         <form action="/board/register" method="POST" role="form" accept-charset="euc-kr">
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
                </div>
                <!-- /.col-lg-6 -->
            </div>
            <!-- /.row -->
 <%@include file="../includes/footer.jsp" %>       