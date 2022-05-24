<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">List Page</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            게시글 목록  ${total } 개 검색됨 <!-- if 처리 해야됨 -->
                        </div>
                        <div class="col-lg-12">
                        	<form id='searchForm' action="/board/list" method="get">
			                    <select class="form-group" name="type">
			                      	<option value=""<c:out value="${criteria.type==null?'selected':''}"/>>--</option>
			                       	<option value="T"<c:out value="${criteria.type eq 'T'?'selected':''}"/>>제목</option>
			                      	<option value="TC"<c:out value="${criteria.type eq 'TC'?'selected':''}"/>>제목 OR 내용</option>
			                      
			                    </select>
			                    <input type="text" class="form-control" name='keyword' value='<c:out value="${criteria.keyword}"/>'>
			                    <span class="input-group-btn">
			                      <button class="btn btn-info" type="button">검색하기</button>
			                    </span>
                  			</form>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>#번호</th>
                                        <th>제목</th>
                                        <th>작성자</th>
                                        <th>작성일</th>
                                        <th>수정일</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${list }" var="board">                   	
                                	<tr class="odd gradeX">
                                        <td>${board.bno }</td>
                                        <td><a href=/board/get?bno=${board.bno }>${board.title }</a></td>
                                        <td>${board.writer }</td>
                                        <td><fmt:formatDate value="${board.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
                                        <td><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    </tr>
                                	</c:forEach>
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
                          	
                            </div>
                            <!-- /.table-responsive -->
				                             <!-- /.row -->
				            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModallabel" aria-hidden="true">
				                <div class="modal-dialog">
				                    <div class="modal-content">
				                        <div class="modal-header">
				                             <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				                             <h4 class="modal-title" id="myModalLabel">Modal title</h4>
				                        </div>
				                        <div class="modal-body">처리가 완료되었습니다.</div>
				                        <div class="modal-footer">
				                             <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				                             <button type="button" class="btn btn-default" >Save Changes</button>
				                        </div>
				                   </div>
				                </div>
				             </div> <!-- /.modal fade -->
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
                let result ='<c:out value="${result}"/>';
                console.log("${list }");
                const checkModal = result => {
                    if (result == '') {
                        return;
                    }
                    if (parseInt(result) > 0) {
                        $(".modal-body").html(
                            `게시글 ${"${parseInt(result)}"}번이 등록되었습니다`
                        );
                    }
                    $("#myModal").modal("show");
                }
                checkModal(result);
                
                let searchForm = $("#searchForm");
                $("#searchForm button").on("click", function(e){
                	if (!searchForm.find("option:selected").val()) {
						alert("검색 종류를 선택하세요");
						return false;
					}
                	if (!searchForm.find("input[name='keyword']").val()) {
						alert("키워드를 입력하세요");
						return false;
					}
                	e.preventDefault();
                	searchForm.submit();
                })
                
            });
            </script>
     
 <%@include file="../includes/footer.jsp" %>       