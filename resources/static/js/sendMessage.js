function sendMessage(userIdFrom, userIdTo)
{
    var from = userIdFrom;
    var to = userIdTo;
    var text = document.getElementById('messageField'+userIdTo).value;

    stompClient.send("/app/chat", {}, JSON.stringify({'from':from, 'to':to, 'text':text}));
}
