function check(){
	if(window.confirm('削除してよろしいですか？')){
		window.alert('削除しました');
		return true;
	}else{
		window.alert('キャンセルされました');
		return false;
	}
}