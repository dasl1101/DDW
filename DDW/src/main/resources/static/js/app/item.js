const item_main = {
    init : function () {
        const _this = this;
        //저장
        $('#btn-saveShop').on('click', function () {
            _this.saveShop();

        });

       //썸네일업로드
        $('#btn-saveShop').on('click', function(){
             _this.uploadFile();

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


        uploadFile : function () {
            const imageInput = $("#thumbnail")[0];
            if(imageInput.files.length === 0){
                alert("썸네일을 선택해주세요");
                return;
              }
            const formData = new FormData();
            formData.append("file", imageInput.files[0]);
            console.log("::::::::::::::::ajax" + imageInput.files[0]);
            $.ajax({
                url : '/image',
                type : 'POST',
                data : formData,
                cache : false,
                contentType : false,
                enctype : 'multipart/form-data',
                processData : false
            }).done(function(data){
                callback(data);
            });
        }


};

item_main.init();