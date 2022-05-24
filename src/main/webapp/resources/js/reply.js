console.log("Reply Module............");

var replyService = ( ()=>{
	const add = (reply,callback,error) =>{

        $.ajax({
            type: "post",
            url: "/rsample/new",
            data: JSON.stringify(reply),
            contentType: "application/json;charset=utf-8",
            success:(result, status, xhr) => {
                if (callback) {
                    callback(result);
                }
            },
            error:(xhr,status,er) => {
                if (error) {
                    error(er);
                }
            }
        });
	}

    const getList = (param, callback, error) => {
        let bno = param.bno;
        let page = param.page||1;
        $.getJSON(`/rsample/pages/${bno}/${page}.json`,
            function (data, textStatus, jqXHR)  {
                if (callback) {
                    callback(data);
                }
            }
        );
    }
    const remove = (rno, callback, error) => {
        $.ajax({
            type: "delete",
            url: `/rsample/${rno }`,
            success: function (deleteResult, status, xhr) {
                if (callback) {
                    callback(deleteResult);
                }
               
            },
            error:(xhr,status,er) => {
                if (error) {
                    error(er);
                }
            }
        });
    }
    const update = (reply, callback, error) => {
        $.ajax({
            type: "put",
            url: `/rsapmle/${reply.rno}`,
            data: JSON.stringify(reply),
            contentType: "application/json;charset=utf-8",
            success:(result, status, xhr) => {
                if (callback) {
                    callback(result);
                }
            },
            error:(xhr,status,er) => {
                if (error) {
                    error(er);
                }
            }
        });
    }
    return {
        add:add,
        getList:getList,
        remove:remove,
        update:update
    }
})();
