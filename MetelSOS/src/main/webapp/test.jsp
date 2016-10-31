<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//500에러 임의로 발생, 500 error page test
	int i = 50 / 0;
%>

<script>
$(document).ready(function() {



	$(".file-dropzone").on('dragover', handleDragEnter);

	$(".file-dropzone").on('dragleave', handleDragLeave);

	$(".file-dropzone").on('drop', handleDragLeave);



	function handleDragEnter(e) {

		this.classList.add('drag-over');

	}



	function handleDragLeave(e) {

		this.classList.remove('drag-over');

	}



	Dropzone.autoDiscover = false;

	

	$('.dropzone').each(function() {

		var id = $(this).attr('id');

		var url = $(this).data('url');

		

		$('div#' + id).dropzone({

			url : url,

			autoProcessQueue : true,

			uploadMultiple : true,

			maxFilesize : 256,

			thumbnailWidth: 180,

		    thumbnailHeight: 120,

		    parallelUploads: 100,

			maxFiles : 3,

			addRemoveLinks : true,

			acceptedFiles: ".jpeg,.jpg,.png,.gif,.JPEG,.JPG,.PNG,.GIF",

			// The setting up of the dropzone

			accept: function(file, done) {

			    done();

			},

			init : function() {

				var myDropzone = this;

				var count = myDropzone.options.maxFiles;

				$.getJSON("./file/list.do", $("#"+id).find('input[type=hidden]')).done(function (data) {

					

					$('#'+id).find('input[type=hidden]').remove();

                    if (data.data != '' && data.data != undefined) {

                    	var existingFileCount = 0;

                        $.each(data.data, function (index, item) {

                            var mockFile = {

                                name: item.logicFileNm,

                                size: item.fileSize,

                                seq: item.fileId

                            };

                            myDropzone.emit("addedfile", mockFile);

                            myDropzone.emit("thumbnail", mockFile, item.filePath);

                            myDropzone.emit("success", mockFile);

                            myDropzone.files.push(mockFile);

                            

                            existingFileCount = existingFileCount + 1;

                        });

                        myDropzone.options.maxFiles = myDropzone.options.maxFiles - existingFileCount;

                    }

                });

				

				this.on("maxfilesexceeded", function (file) {

					alert('Max File Count : ' + count);

					myDropzone.removeFile(file);

				});

                

                this.on("success", function( file, resp, formData ){

                	var type = $("#"+id).data("type");

                	if(resp != null && resp != undefined) {

                		if(resp.data != null && resp.data != '' && resp.data != undefined) {

                			var existingFileCount = 0;

                			$.each(resp.data, function(){

                				file.previewElement.appendChild(Dropzone.createElement("<input type='hidden' name='fileId' value='" + this.fileId + "'/>"));

                				existingFileCount = existingFileCount + 1;

                			});

                		}

                	}

                	

            		if(file.seq != null && file.seq != '' && file.seq != undefined) {

        				file.previewElement.appendChild(Dropzone.createElement("<input type='hidden' name='fileId' value='" + file.seq + "'/>"));

            		}

            		

                });

                

                this.on("removedfile", function (data) {

                	var id = $(data.previewElement).find('input[name=fileId]').val();

            		$.ajax({

            			url: './file/delete.do',

            			type: 'POST',

            			data: { 'fileId' : id }

            		});

            		myDropzone.options.maxFiles = myDropzone.options.maxFiles + 1;

            		if(count < myDropzone.options.maxFiles) myDropzone.options.maxFiles = count;

                });

			}

		});

	});

	

	$('.push').click(function() {

		$('.dropzone').each(function() {

			

		});

	});

	

});
</script>
</body>
</html>