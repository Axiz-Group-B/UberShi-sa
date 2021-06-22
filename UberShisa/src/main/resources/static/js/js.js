
$("#menu-icon").click(function(){
    $(".sidebar-menu").toggleClass("open-menu");
    $(".fas").toggleClass("fa-chevron-right");
});

function check(){
	if(window.confirm('送信してよろしいですか？')){
		return true;
	}else{
		window.alert('キャンセルされました');
		return false;
	}
}