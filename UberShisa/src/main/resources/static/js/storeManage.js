/**
 *
 */
$('#updateStoreBtn').click(() => {
	if (confirm('店舗情報を更新してよろしいでしょうか。')) {
		return true;
	}
	else {
		return false;
	}

});

$('select').change(function() {
	var optionSelected = $(this).find("option:selected");
	var valueSelected = optionSelected.val();
	if(valueSelected == 1){
		$('#orderListTable').show();
		$('#orderListTableFinish').hide();
	}
	else if(valueSelected == 2){
		$('#orderListTable').hide();
		$('#orderListTableFinish').show();

	}
});
$('#passed-btn').hide();