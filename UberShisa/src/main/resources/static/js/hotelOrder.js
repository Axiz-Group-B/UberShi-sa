function orderCheck() {

	if (window.confirm('配達が完了しましたか？')) { // 確認ダイアログを表示
		// 「OK」時の処理
		return true;

	}
	else { // 「キャンセル」時の処理

		//window.alert('キャンセルされました'); // キャンセル押した後にポップアップで出るメッセージ
		return false; // 送信を中止
	}
}