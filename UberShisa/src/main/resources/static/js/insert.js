/**
 *
 */


function check(){
	if(window.confirm("ユーザー登録を完了させますか？")){
		return true;
	}else{
		window.alert('キャンセルされました');
		return false;
	}
}
