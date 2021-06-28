//配達員がお店で商品を受け取った事を知らせる（そろぼち来るか的な）
setInterval(function(){
	// ここに処理
		fetch('/room/orderNotifHotel', {
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
}, 30000)

//配達員が決まらなかった注文があることを知らせる
setInterval(function(){
	// ここに処理
		fetch('/room/orderNotifHotelThree', {
					method: 'get',
					})
	.then(response=>{
		if(response.ok){
			response.text().then(t => {
				console.log(t)
				if(t==='noDelivery'){
					Push.create('Uber-Shisaからのお知らせ', {
						body: '配達員が決まらなかった注文があります。',
						timeout:8000
					});
				}
			});
		}
	})
	.catch(reason=>{
		console.log('catch');
	})
}, 30000)