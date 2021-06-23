
$("#menu-icon").click(function(){
    $(".sidebar-menu").toggleClass("open-menu");
    $(".fas").toggleClass("fa-chevron-right");
});

// $(window).resize(function() {
//     var width = $(window).width();
//     if (width <= 768){
//     //   alert('Your screen is too small');
//     $("input[type='text'").css("placeholder")
//     }
//   });

function check(){
	if(window.confirm('送信してよろしいですか？')){
		return true;
	}else{
		window.alert('キャンセルされました');
		return false;
	}
}
