//MAIN
(function () {
	chrome.runtime.sendMessage({send:"AAAA!"},null);
	// fetch('http://localhost:1127/', {
	// 	message: "AAAA!"
	// }, {
	// 	mode: 'no-cors',
	// 	method: 'post',
	// 	url: `http://localhost:1127`
	// })
	// .then(data=>data.json())
	// .then(res=>console.log(res));
})();