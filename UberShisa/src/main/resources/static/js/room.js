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