//MAIN
(function () {
    chrome.runtime.onMessage.addListener((request, sender, sendResponse) => {
        sendRequest(request).then(sendResponse);
        return true;
    });
    async function sendRequest(request) {
        if (!request.message.command) return;
        return await fetch("http://localhost:" + request.accessPort + "/", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            accessKey: request.accessKey,
            body: JSON.stringify(request.message)
        })
            .then((response) => response.json())
            .then((data) => data.resp)
            .catch((error) => console.log(error));
    }
})();