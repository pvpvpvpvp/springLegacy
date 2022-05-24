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
            <div class="panel-heading"><i class="fa fa-comments fa-fw"></i>
                Reply
                <button class="btn btn" id="addReplyBtn">댓글 작성하기</button>

            </div>
            <div class="panel-body">
                <ul class="chat">

                </ul>
            </div>
        </div> <!-- /.reply panel -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModallabel"
            aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Reply</label> <input class="form-control" name='reply' value='New Reply!!' />
                        </div>
                        <div class="form-group">
                            <label>Replyer</label> <input class="form-control" name='replyer' value='New Replyer!!' />
                        </div>
                        <div class="form-group">
                            <label>ReplyDate</label> <input class="form-control" name='replyDate'
                                value='New Reply Date!!' />
                        </div>
                        <!--             //replyer, replyDate 를위한 div 배치 -->
                    </div>
                    <div class="modal-footer">
                        <button id='modalModBtn' type="button" class="btn btn-info">Modify</button>
                        <button id='modalRegisterBtn' type="button" class="btn btn-info">Register</button>
                        <button id='modalCloseBtn' type="button" class="btn btn-info">Close</button>
                        <button id='modalRemoveBtn' type="button" class="btn btn-info">Remove</button>
                        <!--             id 가 modalRemoveBtn, modalRegisterBtn, modalCloseBtn 배치 -->
                    </div>
                </div>
            </div>
        </div>

        <script src="/resources/js/reply.js"></script>
        <script>
            $(document).ready(function () {
                var bnoValue = `${board.bno}`
                const replyUL = $('.chat');

                const showList = page => replyService.getList(
                    { bno: bnoValue, page: page },
                    list => {
                        let str = ""
                        list.forEach(element => {
                            let time = replyService.displyaTime(element.replyDate);
                            str += `
                           <li class="left clearfix" data-rno="${'${element.rno}'}">
                                <div>
                                    <div class="header">
                                        <strong class="primary-font">${'${element.replyer}'}</strong>
                                        <small class="pull-right text-muted">${'${time}'}</small>
                                    </div>
                                    <p>${'${element.reply}'}</p>
                                </div>
                            </li>`
                        });
                        replyUL.html(str);
                    }
                )
                showList(1);

           

                const modal = $(".modal");
                const modalInputReply = modal.find("input[name='reply']");
                const modalInputReplyer = modal.find("input[name='replyer']");
                const modalInputReplyDate = modal.find("input[name='replyDate']");

                const modalModBtn = $("#modalModBtn");
                const modalRemoveBtn = $("#modalRemoveBtn");
                const modalRegisterBtn = $("#modalRegisterBtn");
                const modalCloseBtn = $("#modalCloseBtn");

                $('#addReplyBtn').on("click", function (e) {
                    modal.find("input").val("");
                    modalInputReplyDate.closest("div").hide();
                    modal.find("button[id!='modalCloseBtn']").hide();
                    modalRegisterBtn.show();
                    $('#myModal').modal("show");
                });
                
                modalRegisterBtn.on("click", function (e) {
                    let reply = {
                        reply:modalInputReply.val(),
                        replyer:modalInputReplyer.val(),
                        bno:bnoValue
                    }
                    replyService.add(
                    reply,
                    result =>{
                        modal.find("input").val("");
                        modal.modal("hide");
                        showList(1);
                    }
                )
                });

                $('.chat').on("click",'li', function (e) {
                    let rno = $(this).data("rno");
                    replyService.get(rno, function(reply){
                    	modalInputReply.val(reply.reply);
                    	modalInputReplyer.val(reply.replyer);
                    	modalInputReplyDate.val(reply.replyDate).attr("readonly","readonly");
                    	modal.data("rno",reply.rno);
                    	
                    	modal.find("button[id!='modalCloseBtn']").hide();
                    	modalModBtn.show();
                    	modalRemoveBtn.show();
                    	modal.modal("show");
                    })
                });

              

               

            

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

                // replyService.update(
                //     {
                //         rno: 26,
                //         bno: bnoValue,
                //         reply: "modified reply...",
                //         replyer: "야너두?"
                //     },
                //     result => {
                //         alert("수정 완료");
                //     }
                // )
                // replyService.get(
                //     26,
                //     data => {
                //         console.log(data);
                //     }
                // )

            });
        </script>
        <%@include file="../includes/footer.jsp" %>