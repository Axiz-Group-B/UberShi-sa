/**
 *
 */
$('#updateDeliveryBtn').click(() => {
    if(confirm('配達員情報を更新してよろしいでしょうか。')){
        return true;
    }
    else{
        return false;
    }

});