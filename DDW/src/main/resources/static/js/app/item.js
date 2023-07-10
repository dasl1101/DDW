const main = {
    init : function () {
        const _this = this;
        $('#btn-saveShop').on('click', function () {
            _this.save();
        });

       },
    save : function () {
            const data = {
                title: $('#title').val(),
                owner: $('#owner').val(),
                content: $('#summernote').val()
            };

        $.ajax({
            type: 'POST',
            url: '/api/v1/item',
            dataType: 'JSON',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/item/list';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

};

main.init();