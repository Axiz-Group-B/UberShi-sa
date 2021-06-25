//配達員に渡していない注文があることを知らせる
setInterval(function(){
	// ここに処理
	fetch('/room/orderNotifShop', {
					method: 'get',
					})
	.then(response=>{
		if(response.ok){
			response.text().then(t => {
				console.log(t)
				if(t==='Yes'){
					Push.create('Uber-Shisaからのお知らせ', {
						body: '進行中の注文があります。',
						timeout:8000
					});
				}
			});
		}
	})
	.catch(reason=>{
		console.log('catch');
	})
}, 600000)