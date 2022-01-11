$(function() {
	//#region Add images item
	var list_img = $(".display_img-list");
	var add_btn = $(".add-img-button");

	$(add_btn).click(function(e) { //on add input button click
		var last_item = $(".form-list-wrapper .display_img-item:last-child").find(".change-img").attr("id")
		var last_index = 1
		if (last_item) {
			last_index = last_item.match(/\d+/gi);
			if (last_index) {
				last_index = parseInt(last_index) + 1;
			} else {
				last_index = 1;
			}
		}
		var html_content = '<div class="display_img-item"> <img src="/areas/admin/dist/img/upload_img.png" class="upload-img"> <div class="display_img-remove">x</div> <label for="add-img-' + last_index + '" class="display_img-change">Chọn ảnh</label> <input type="file" id="add-img-' + last_index + '" class="change-img" name="add_img-pictures" multiple="multiple" hidden> </div>';

		e.preventDefault();
		$(list_img).append(html_content); //add input box
	});

	$(list_img).on("click", ".display_img-remove", function(e) {
		e.preventDefault();
		var sub = $(this);
		if ($(this).hasClass("picture-path")) {
			var file = $(this).parent(".display_img-item").find("img.upload-img").attr('src');
			file = file.substring(17);
			let data = { id: $("#id").val(), path: file, };
			console.log(data);

			$.ajax({
				url: "/admin/products/ajax/delete_image",
				type: "post",
				contentType: "application/json",
				data: JSON.stringify(data),
				dataType: "json",
				success: function(jsonResult) {
					sub.parent('div').remove();
				},
				error: function(jqXhr, textStatus, errorMessage) {
				}
			});
		} else {
			$(this).parent('div').remove();
		}

	})

	$(list_img).on('change', ".change-img", function(event) {
		event.preventDefault();
		var file = event.target.files[0];
		if (file) {
			var src = URL.createObjectURL(file);
			$(this).parent(".display_img-item").find("img.upload-img").attr("src", src);
		}
	});

	$(".display_img-avatar").on('change', ".change-img", function(event) {
		event.preventDefault();
		var file = event.target.files[0];
		console.log(file);
		if (file) {
			var src = URL.createObjectURL(file);
			$(this).parent(".display_img-avatar").find("img.upload-img").attr("src", src);
		}
	});

	//#endregion Add images item

	//#region  Summernote
	$('.summernote').summernote({
		minHeight: 185,
		maxHeight: 300
	});

	//#endregion  Summernote
});