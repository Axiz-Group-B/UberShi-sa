// select要素を取得
	let shopId = document.getElementById('shopId');
	//関数作る
	const select =()=>{

	let selectBox = document.getElementById('selectBox');

	//shopIdのvalueをクエリパラメーターにつける
	let selectedId = document.getElementById('shopId').value;
	//パラメーターをjson形式で書く
	let param = {
		a : selectedId
	}
	const query_param = new URLSearchParams(param);
	const url = 'http://localhost:8080/room/selectBox';
		fetch(`${url}?${query_param}`)
		.then(response=>{
			if(response.ok){
				response.text().then(r=>{
					selectBox.innerHTML= r;
				})
			}
		})
		.catch(reason=>{
		console.log('catch');
	})
	}
	//セレクトボックスが変更されたときのイベントを定義
	shopId.addEventListener('change', select);

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
/*	var btns = document.getElementsByClassName('deleteItem');//idじゃなくて配列でとってきて、あとでイベント情報からvalueとれる
	//引数にイベントeをとって、.toElement(イベント情報のプロパティを取れる感じ)してから、.valueするとか？どうにかしてやる
	for(let i =0; i< btns.length ; i++){
		btns[i].addEventListener('click', check);
	}
*/



