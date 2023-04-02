//MAIN
(function () {
    chrome.runtime.onMessage.addListener(function (request, sender, sendResponse) {
        if (request.send) {
            fetch('http://localhost:1127/', {
                method: "POST",
                headers: {
                    "Content-Type": "text/plain"
                },
                body: request.send
            })
            .then(response => response.json())
            .then(data => console.log(data.resp))
            .catch((error) => console.log("FAIL: " + error));
        }
    });
})();