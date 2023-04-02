//MAIN
(function () {
	const innerDoor = {
		readFile: (key, port, path, listener) => {
			chrome.runtime.sendMessage(
				{
					message: { command: "READ", content: { filePath: path } },
					accessKey: key,
					accessPort: port
				}, listener);
		}
	}
	innerDoor.readFile("lskgMIdmFOwarteSdsFamshc", "1127", "C:/Teste",
		(response) => {
			console.log("RESP: " + response);
		}
	);
})();