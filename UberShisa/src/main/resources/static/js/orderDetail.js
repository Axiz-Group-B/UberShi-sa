/////////////////////////////////////////////////////////////////////////////////
//amountを変えるとsubtotal変わるメソッド
let amount = document.getElementById('amount');
const price = document.getElementById('price').value;//一度ページ表示されたら、このPriceは変わることがない

//変える度にじっこうする関数定義
const changeStotal =()=>{
	let subtotal = document.getElementById('subtotal');
	let amountValue = document.getElementById('amount').value;
	subtotal.value = price*amountValue;
}

//amountが変わった時のイベントを定義
	amount.addEventListener('input',changeStotal);
//////////////////////////////////////////////////////////////////////////////////
//削除ボタンポップアップ
function check(){

	if(window.confirm('操作を確定してよろしいですか？')){ // 確認ダイアログを表示

		// 「OK」時の処理
		return true;

	}
	else{ // 「キャンセル」時の処理

		window.alert('キャンセルされました'); // キャンセル押した後にポップアップで出るメッセージ
		return false; // 送信を中止
	}
}

//ここで、ボタン押したときの動きを定義
/*	var btns = document.getElementsByClassName('deleteItem');
	for(let i =0; i< btns.length ; i++){
		btns[i].addEventListener('click', check);
	}*/