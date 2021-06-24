//JSからコントローラーに飛ぶ.あとでsetIntervalに入れられるように…関数名必須
const delivery = ()=>{
	fetch('/room/orderNotif', {
					method: 'get',
					})
	.then(response=>{
		if(response.ok){
			response.text().then(t => {
				console.log(t)
				if(t==='delivery'){
					Push.create('Uber-Shisaからのお知らせ', {
						body: 'ご注文ありがとうございます。配達員が決まりました。',
						timeout:8000
					});
				}
			});
		}
	})
	.catch(reason=>{
		console.log('catch');
	})
}

const leaveShop = ()=>{
	fetch('/room/orderNotifFour', {
					method: 'get',
					})
	.then(response=>{
		if(response.ok){
			response.text().then(t => {
				console.log(t)
				if(t==='leaveShop'){
					Push.create('Uber-Shisaからのお知らせ', {
						body: '配達員がお店で注文商品を受け取りました。',
						timeout:8000
					});
				}
			});
		}
	})
	.catch(reason=>{
		console.log('catch');
	})
}

const hotelArrived = ()=>{
	fetch('/room/orderNotifFive', {
					method: 'get',
					})
	.then(response=>{//responseくるまで次の処理しないよ
		//jsで返す文字列を、レコードあるときとない時で分けてるから、こっちはresponseをtextにして、文字列比較する。
		//一致してたら処理する…みたいな
		if(response.ok){//responseOKなら処理はじめます
			response.text().then(t => {//responseをテキストに変えて、変え終わったらthenで、thenの中には直前の結果が入るから代入しなくてもいい
				console.log(t)
				if(t==='hotelArrived'){//プッシュ通知
					Push.create('Uber-Shisaからのお知らせ', {
						body: 'ホテルに注文商品が届きました。',
						timeout:8000//通知をクリックしたときのイベント
						//通知は、ステータス変わるまで送り続ける（flagつかわない
						//ここで通知送ったflagアップデートしてもいい。そうするとsqlもいじらんといけんけど…
					});
				}
			});
			//レコードがなかったら何も処理しないだけなので、空白でいい
		}
	})
	.catch(reason=>{
		console.log('catch');
	})
}
//一定間隔で実行。通知被らないようにずらしてる
setInterval(delivery, 500000);
setInterval(leaveShop, 600000);
setInterval(hotelArrived, 700000);