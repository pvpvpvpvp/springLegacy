console.log("Reply Module............");



var replyService = (() => {
    const add = (reply, callback, error) => {

        $.ajax({
            type: "post",
            url: "/rsample/new",
            data: JSON.stringify(reply),
            contentType: "application/json;charset=utf-8",
            success: (result, status, xhr) => {
                if (callback) {
                    callback(result);
                }
            },
            error: (xhr, status, er) => {
                if (error) {
                    error(er);
                }
            }
        });
    }

    const getList = (param, callback, error) => {
        let bno = param.bno;
        let page = param.page || 1;
        $.getJSON(`/rsample/pages/${bno}/${page}.json`,
            function (data, textStatus, jqXHR) {
                if (callback) {
                    callback(data);
                }
            }
        );
    }
    const remove = (rno, callback, error) => {
        $.ajax({
            type: "delete",
            url: `/rsample/${rno}`,
            success: function (deleteResult, status, xhr) {
                if (callback) {
                    callback(deleteResult);
                }

            },
            error: (xhr, status, er) => {
                if (error) {
                    error(er);
                }
            }
        });
    }
    const update = (reply, callback, error) => {
        $.ajax({
            type: "put",
            url: `/rsample/${reply.rno}`,
            data: JSON.stringify(reply),
            contentType: "application/json;charset=utf-8",
            success: (result, status, xhr) => {
                if (callback) {
                    callback(result);
                }
            },
            error: (xhr, status, er) => {
                if (error) {
                    error(er);
                }
            }
        });
    }
    const get = (rno, callback, error) => {
        $.getJSON(`/rsample/${rno}.json`,
            (data, textStatus, jqXHR) => {
                if (callback) {
                    callback(data);
                }
            }
        )
        /*
        .fail(
            (xhr, status, err) =>{
                if (error) {
                    error();
                }
            }
        );*/
    }
    const displyaTime = timeValue => {
        var today = new Date();
        var gap = today.getTime().timeValue;
        var dateObj = new Date(timeValue);
        var str = "";
        if (gap < 1000 * 60 * 60 * 24) {
          var hh = dateObj.getHours();
          var mi = dateObj.getMinutes();
          var ss = dateObj.getSeconds();
          return [
            (hh > 9 ? "" : "0") + hh,
            ":",
            (mi > 9 ? "" : "0") + mi,
            ":",
            (ss > 9 ? "" : "0") + ss,
          ].join("");
        } else {
          var yy = dateObj.getFullYear();
          var mm = dateObj.getMonth() + 1;
          var dd = dateObj.getDate();
          return [
            yy,
            "/",
            (mm > 9 ? "" : "0") + mm,
            "/",
            (dd > 9 ? "" : "0") + dd,
          ].join("");
        }
    }
    return {
        add: add,
        getList: getList,
        remove: remove,
        update: update,
        get: get,
        displyaTime:displyaTime
    }
})();


