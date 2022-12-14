<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HTML ������ ����</title>
<style type="text/css">

/* ���� ���� ���� */
.ck.ck-editor {
	max-width: 1000px;
}

.ck-editor__editable {
	min-height: 300px;
}
</style>
<script
	src="https://cdn.ckeditor.com/ckeditor5/35.3.2/classic/ckeditor.js"></script>
<script
	src="https://cdn.ckeditor.com/ckeditor5/35.3.2/classic/translations/ko.js"></script>
</head>
<body>

	<form method="post" action="articlewrite.zan">
		<label>����</label> <input type="text" name="subject" id="title"><br />
		<label>����</label>
		<textarea name="content" id="editor"></textarea>
		<input type="hidden" name="userid" id="userid" value="${sessionScope.id}">
		<label>�±�</label>
		<select name="tag" id="tag">
			<option value="N">�������</option>
			<option value="Y">������</option>
		</select> 
		<input type="submit" value="����">
		
	</form>
	<button type="button" class="btn btn-default"
		onclick="location.href ='articlelist.zan';">���ư���</button>
	<script>
        ClassicEditor
            .create( document.querySelector( '#editor' ), {language : "ko"} )
            .catch( error => {
                console.error( error );
        } );
</script>
</body>
</html>