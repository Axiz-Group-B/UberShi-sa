function checkUpdateRoom(){
	if(window.confirm('更新してよろしいですか？')){
		location.href="/hotel/updateIdAndPass"
	}else{
		//window.alert('キャンセルされました');
		return false;
	}
}