const leaveShop = ()=>{
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
}

setInterval(leaveShop, 1200000);