window.onload = connect();

function connect()
{
    var socket = new SockJS('http://localhost:8080/app-stomp-endpoint');
    var stompClient = Stomp.client(socket);
    stompClient.connect({}, function(frame)
    {
        console.log('Connected : ' + frame);
        stompClient.subscribe('/topic/messages', function(messageOutput)
        {
            showMessageOutput(JSON.parse(messageOutput.body));
        });
    });
}