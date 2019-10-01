function disconnect()
{
    if(stompClient != null)
    {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}