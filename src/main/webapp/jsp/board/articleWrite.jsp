<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HTML 편집기 예제</title>
<style type="text/css">

/* 넓이 높이 조절 */
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
		<label>제목</label> <input type="text" name="subject" id="title"><br />
		<label>내용</label>
		<textarea name="content" id="editor"></textarea>
		<input type="hidden" name="userid" id="userid" value="${sessionScope.id}">
		<label>태그</label>
		<select name="tag" id="tag">
			<option value="N">적용안함</option>
			<option value="Y">적용함</option>
		</select> 
		<input type="submit" value="전송">
		
	</form>
	<button type="button" class="btn btn-default"
		onclick="location.href ='articlelist.zan';">돌아가기</button>
	<script>
        ClassicEditor
            .create( document.querySelector( '#editor' ), {language : "ko"} )
            .catch( error => {
                console.error( error );
        } );
</script>
</body>
</html>