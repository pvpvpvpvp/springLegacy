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
                        게시글 확인
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">


                        <div class="form-group">
                            <label>Title</label>
                            <input class="form-control" name="title" value='<c:out value="${board.title }"></c:out>'
                                readonly="readonly">
                        </div>
                        <div class="form-group">
                            <label>Content</label>
                            <textarea rows="3" name="content" class="form-control"
                                readonly="readonly"><c:out value="${board.content }"></c:out></textarea>
                        </div>
                        <div class="form-group">
                            <label>Writer</label>
                            <input class="form-control" name="writer" value='<c:out value="${board.writer }"></c:out>'
                                readonly="readonly">
                        </div>
                        <button data-oper='modify' onclick="location.href='/board/modify?bno=<c:out value=" ${board.bno
                            }" />'" class="btn btn-default">Modify</button>
                        <a href='/board/list' class="btn btn-default">List</a>


                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-6 -->
        </div>
        <!-- /.row -->
        <div class="panel panel-default">
            <div class="panel-heading"><i class="fa fa-comments fa-fw"></i> Reply</div>
            <div class="panel-body">
                <ul class="chat">
                    
                </ul>
            </div>
        </div> <!-- /.reply panel -->
        <script src="/resources/js/reply.js"></script>
        <script>
            $(document).ready(function () {
                var bnoValue = `${board.bno}`
                // replyService.add(
                //     {
                //         reply:"Js Test",
                //         replyer:"js tester",
                //         bno:bnoValue
                //     },
                //     result =>{
                //         alert(`RESULT: ${'${result}'}`);
                //     }
                // )
                const replyUL = $('.chat');
                replyService.getList(
                    { bno: bnoValue, page: 1 },
                    list => {
                        let str =""
                        list.forEach(element => {
                           str += `
                           <li class="left clearfix" data-rno="${'${element.rno}'}">
                                <div>
                                    <div class="header">
                                        <strong class="primary-font">${'${element.replyer}'}</strong>
                                        <small class="pull-right text-muted">${'${element.replyDate}'}</small>
                                    </div>
                                    <p>${'${element.reply}'}</p>
                                </div>
                            </li>`
                        });
                        replyUL.html(str);
                    } 
                )
              
                // replyService.remove(
                //     19,
                //     count => {
                //         console.log(count);
                //         if (count==="success") {
                //             alert("REMOVED");
                //         }
                //     }
                //     ,err => {
                //         alert("error occurred...");
                //     }
                // )

                replyService.update(
                    {
                        rno: 26,
                        bno: bnoValue,
                        reply: "modified reply...",
                        replyer: "야너두?"
                    },
                    result => {
                        alert("수정 완료");
                    }
                )
                replyService.get(
                    26,
                    data => {
                        console.log(data);
                    }
                )

            });
        </script>
        <%@include file="../includes/footer.jsp" %>