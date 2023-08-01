const item_main = {
    init : function () {
        const _this = this;
        //저장
        $('#btn-saveShop').on('click', function () {
            _this.saveShop();

        });

        $('#btn-modifyShop').on('click', function () {
            _this.updateShop();
        });

        $('#btn-deleteShop').on('click', function () {
            _this.deleteShop();
            console.log("$('#item_id').val() : " + $('#item_id').val());
        });


    },

       //저장
    saveShop : function () {
        const fileValue = $("#thumbnail").val().split("\\");
        const fileName = fileValue[fileValue.length-1]; // 파일명
        console.log("::::::::::::::::ajax"+fileName);
            const data = {
                title: $('#title').val(),
                owner: $('#owner').val(),
                content: $('#content').val(),
                name: $('#name').val(),
                price: $('#price').val(),
                thumbnail: fileName
            };

        $.ajax({

            type: 'POST',
            url: '/api/v1/item',
            dataType: 'JSON',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {

            alert('글이 등록되었습니다.');
            window.location.href = '/shop/shop-list';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },


        //수정
        updateShop : function () {
            const fileValue = $("#itemThumbnail").val().split("\\");
            const fileName = fileValue[fileValue.length-1]; // 파일명
                const data = {
                    title: $('#itemTitle').val(),
                    owner: $('#itemOwner').val(),
                    content: $('#itemContent').val(),
                    name: $('#itemName').val(),
                    price: $('#itemPrice').val(),
                    thumbnail: fileName
                };
        const id = $('#itemId').val();
        const con_check = confirm("수정하시겠습니까?");
        if (con_check === true) {
            if (!data.title || data.title.trim() === "" || !data.content || data.content.trim() === ""
                || !data.name || data.name.trim() === "" || !data.price || data.price.trim() === "") {
                            alert("상품 정보를 모두 입력해 주세요.");
                            return false;
            } else {
                $.ajax({
                    type: 'PUT',
                    url: '/api/v1/item/'+id,
                    dataType: 'json',
                    contentType:'application/json; charset=utf-8',
                    data: JSON.stringify(data)
                 }).done(function() {
                    alert('글이 수정되었습니다.');
                    window.location.href = '/shop/shop-list';
                 }).fail(function (error) {
                     alert(JSON.stringify(error));
                  });
            }
        }
    },

    deleteShop : function () {
            const id = $('#item_id').val();
            const con_check = confirm("정말 삭제하시겠습니까?");

            if(con_check == true) {
                $.ajax({
                    type: 'DELETE',
                    url: '/api/v1/item/'+id,
                    dataType: 'JSON',
                    contentType: 'application/json; charset=utf-8'

                }).done(function () {
                    alert("삭제되었습니다.");
                    window.location.href = '/shop/shop-list';
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                });
            } else {
                return false;
            }
        }

};

item_main.init();