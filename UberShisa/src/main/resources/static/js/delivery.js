/**
 *
 */
$('#updateDeliveryBtn').click(() => {
	if (confirm('配達員情報を更新してよろしいでしょうか。')) {
		return true;
	}
	else {
		return false;
	}

});

//配達員が注文を選択
function orderCheck() {

	if (window.confirm('この注文を配達しますか？')) { // 確認ダイアログを表示

		// 「OK」時の処理
		location.href = "/deliveryMan/deliveryOrderSelected";

	}
	else { // 「キャンセル」時の処理

		//window.alert('キャンセルされました'); // キャンセル押した後にポップアップで出るメッセージ
		return false; // 送信を中止
	}

}
//配達員がホテルまで配達を完了した時
function checkDeliveryCompleted() {

	if (window.confirm('配達が完了しましたか？')) { // 確認ダイアログを表示

		// 「OK」時の処理
		location.href = "/deliveryMan/deliveryCompleted";

	}
	else { // 「キャンセル」時の処理

		//window.alert('キャンセルされました'); // キャンセル押した後にポップアップで出るメッセージ
		return false; // 送信を中止
	}

}
