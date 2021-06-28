function updateCheck() {

	if (window.confirm('更新してよろしいですか？')) { // 確認ダイアログを表示

		// 「OK」時の処理
		return true;

	}
	else { // 「キャンセル」時の処理

		window.alert('キャンセルされました'); // キャンセル押した後にポップアップで出るメッセージ
		return false; // 送信を中止
	}
}

//画像を読み込む
function updateImageView() {

	var updateImage = document.getElementById("updateProductImage");
	var nowImage = document.getElementById("imageView");
	nowImage.setAttribute('src',updateImage.value);
}

function deleteProductCheck() {

	if (window.confirm('チェックした項目を削除してよろしいですか？')) { // 確認ダイアログを表示

		// 「OK」時の処理
		return true;

	}
	else { // 「キャンセル」時の処理

		window.alert('削除を取り消しました'); // キャンセル押した後にポップアップで出るメッセージ
		return false; // 送信を中止
	}
}

document.getElementById('updateProductImage').addEventListener('change', function(e) {
	var file = e.target.files[0];
	var reader = new FileReader();
	reader.onload = function() {
		var uri = this.result;
		document.getElementById('preview').src = uri;
	}
	reader.readAsDataURL(file);
});



