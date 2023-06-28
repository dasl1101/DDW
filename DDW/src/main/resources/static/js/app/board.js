var main = {
    init : function () {
        const _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

        // 댓글 저장
        $('#btn-comment-save').on('click', function () {
            _this.commentSave();
        });

    },
    save : function () {
        const data = {
            title: $('#title').val(),
            owner: $('#owner').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/board/list';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        const data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        const id = $('#id').val();
        const con_check = confirm("수정하시겠습니까?");
         if (con_check === true) {
            if (!data.title || data.title.trim() === "" || !data.content || data.content.trim() === "") {
                alert("제목과 내용을 모두 입력해 주세요.");
                return false;
            } else {
                    $.ajax({
                        type: 'PUT',
                        url: '/api/v1/posts/'+id,
                        dataType: 'json',
                        contentType:'application/json; charset=utf-8',
                        data: JSON.stringify(data)
                    }).done(function() {
                        alert('글이 수정되었습니다.');
                        window.location.href = '/board/list';
                    }).fail(function (error) {
                        alert(JSON.stringify(error));
                 });
             }
         }
     },
     delete : function () {
        const id = $('#id').val();
        const con_check = confirm("정말 삭제하시겠습니까?");

        if(con_check == true) {
            $.ajax({
                type: 'DELETE',
                url: '/api/v1/posts/'+id,
                dataType: 'JSON',
                contentType: 'application/json; charset=utf-8'

            }).done(function () {
                alert("삭제되었습니다.");
                window.location.href = '/board/list';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        } else {
            return false;
        }
    },

      // 댓글 저장
        commentSave : function () {
            const data = {
                postsId: $('#postsId').val(),
                comment: $('#comment').val()
            }

            // 공백 및 빈 문자열 체크
            if (!data.comment || data.comment.trim() === "") {
                alert("공백 또는 입력하지 않은 부분이 있습니다.");

                return false;
            } else {
               $.ajax({
                 type: 'POST',
                 url: '/api/v1/posts/' + data.postsId + '/comments',
                 contentType: 'application/json; charset=utf-8',
                 data: JSON.stringify(data)
             }).done(function () {
                 alert('댓글이 등록되었습니다.');
                 window.location.reload();
             }).fail(function (error) {
                 alert(JSON.stringify(error));
             });
            }
        }

};

main.init();