const item_main = {
    init : function () {
        const _this = this;
        //저장
        $('#btn-saveShop').on('click', function () {
            _this.saveShop();

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




};

item_main.init();