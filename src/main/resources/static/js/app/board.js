const board_main = {
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

        // 댓글 수정
        document.querySelectorAll('#btn-comment-update').forEach(function (item) {
            item.addEventListener('click', function () { // 버튼 클릭 이벤트 발생시
                const form = this.closest('form'); // btn의 가장 가까운 조상의 Element(form)를 반환 (closest)
                _this.commentUpdate(form); // 해당 폼으로 업데이트 수행
            });
        });

        // 회원 수정
        $('#btn-user-modify').on('click', function () {
            _this.userModify();
        });

        //회원 탈퇴
        $('#btn-cancel-user').on('click', function () {
            _this.cancel_user();
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
            dataType: 'JSON',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
         console.log(":::::::::::::::::::::::::ajax");
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
             }).always(function () {
                 alert('댓글이 등록되었습니다.');
                 window.location.reload();
             }).fail(function (error) {
                 alert(JSON.stringify(error));
             });
            }
        },

      // 댓글 수정
        commentUpdate : function (form) {
            const data = {
                id: form.querySelector('#id').value,
                postsId: form.querySelector('#postsId').value,
                comment: form.querySelector('#comment-content').value,
                writerUserId: form.querySelector('#writerUserId').value,
                sessionUserId: form.querySelector('#sessionUserId').value
            }
            console.log("commentWriterID : " + data.writerUserId);
            console.log("sessionUserID : " + data.sessionUserId);
            console.log("commentId : " + data.id);
            console.log("postId : " + data.postsId);

            if (data.writerUserId !== data.sessionUserId) {
                alert("본인이 작성한 댓글만 수정 가능합니다.");
                return false;
            }

            if (!data.comment || data.comment.trim() === "") {
                alert("공백 또는 입력하지 않은 부분이 있습니다.");
                return false;
            }
            const con_check = confirm("수정하시겠습니까?");
            if (con_check === true) {
                $.ajax({
                    type: 'PUT',
                    url: '/api/v1/posts/' + data.postsId + '/comments/' + data.id,
                    dataType: 'JSON',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(data)
                }).done(function () {
                    window.location.reload();
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                });
            }
        },
      //댓글 삭제
      commentDelete : function (postsId, commentId){
               const con_check = confirm("삭제하시겠습니까?");
               console.log("postId : " + postsId + "commentId : " + commentId);
               if (con_check === true) {
                   $.ajax({
                       type: 'DELETE',
                       url: '/api/v1/posts/' + postsId + '/comments/' + commentId,
                       dataType: 'JSON',
                   }).done(function () {
                       alert('댓글이 삭제되었습니다.');
                       window.location.reload();
                   }).fail(function (error) {
                       alert(JSON.stringify(error));
               });
           }
       },

    /** 회원 수정 */
    userModify : function () {
        const data = {
            id: $('#id').val(),
            modifiedDate: $('#modifiedDate').val(),
            name: $('#name').val(),
            nickName: $('#nickName').val(),
            password: $('#password').val()
        }
        //if(!data.nickName || data.nickName.trim() === "" || !data.password || data.password.trim() === "") {
        //    alert("공백 또는 입력하지 않은 부분이 있습니다.");
        //    return false;}
        if(!/^[ㄱ-ㅎ가-힣a-zA-Z0-9-_]{2,10}$/.test(data.nickName)) {
            alert("닉네임은 특수문자를 제외한 2~10자리여야 합니다.");
            $('#nickName').focus();
            return false;
        }
        const con_check = confirm("수정하시겠습니까?");
        if (con_check === true) {
            $.ajax({
                type: "PUT",
                url: "/api/v1/user",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)

            }).done(function () {
                alert("회원수정이 완료되었습니다.");
                window.location.href = "/";

            }).fail(function (error) {
                if (error.status === 500) {
                    alert("이미 사용중인 닉네임 입니다.");
                    $('#nickName').focus();
                } else {
                    alert(JSON.stringify(error));
                }
            });
        } else {
            return false;
        }
    },
    //회원탈퇴
     cancel_user : function () {
        const id = $('#del_id').val();

            $.ajax({
                type: 'POST',
                url: '/cansel_user/'+id,
                dataType: 'JSON',
                contentType: 'application/json; charset=utf-8'

            }).done(function () {
                alert("탈퇴 완료되었습니다.");
                window.location.href = '/logout';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });

    },

};
//console.log('asdasdasdad');
board_main.init();