/////////////////////////////////////////////////////////////////////////////////
//amountを変えるとsubtotal変わるメソッド
let amount = document.getElementById('amount');
const price = document.getElementById('price').value;//一度ページ表示されたら、このPriceは変わることがない
//変える度にじっこうする関数定義
const changeStotal =()=>{
	let subtotal = document.getElementById('subtotal');
	let amountValue = document.getElementById('amount').value;
	let sValue = price*amountValue;

	subtotal.innerHTML='<input type=\"text\" value=\"'+sValue+'\" readonly name=\"subtotal\" id=\"subtotal\">';
}

//amountが変わった時のイベントを定義
	amount.addEventListener('input',changeStotal);

