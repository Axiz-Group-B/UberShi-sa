const order = ()=>{
	fetch('/room/orderNotifShop', {
					method: 'get',
					})
	.then(response=>{
		if(response.ok){
			response.text().then(t => {
				console.log(t)
				if(t==='Yes'){
					Push.create('Uber-Shisaからのお知らせ', {
						body: '未完了の注文があります。',
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

setInterval(leaveShop, 1200000);