/**
 *
 */
// 店舗情報ボタンをクリックすると確認ポップアップが表示される
$("#updateStoreBtn").click(() => {
  if (confirm("店舗情報を更新してよろしいでしょうか。")) {
    return true;
  } else {
    return false;
  }
});
// 渡しましたボタンをクリックすると確認ポップアップが表示される
$("#passed-btn").click(() => {
  if (confirm("商品を受け渡し済みでよろしいでしょうか。")) {
    return true;
  } else {
    return false;
  }
});

$("select")
  .change(function () {
    var optionSelected = $(this).find("option:selected");
    var valueSelected = optionSelected.val();
    if (valueSelected == 1) {
      $("#orderListTable").show();
      $("#orderListTableFinish").hide();
      localStorage.setItem("passedBtnStatus", "false");
    } else if (valueSelected == 2) {
      $("#orderListTable").hide();
      $("#orderListTableFinish").show();
      localStorage.setItem("passedBtnStatus", "true");
    }
    var passedBtnStatus = localStorage.getItem("passedBtnStatus");
  })
  .change();
var passedBtnStatus = localStorage.getItem("passedBtnStatus");
if (passedBtnStatus == "true") {
  $("#passed-btn").hide();
} else if (passedBtnStatus == "false") {
  $("#passed-btn").show();
}
