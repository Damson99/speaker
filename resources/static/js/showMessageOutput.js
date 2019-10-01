function showMessageOutput(messageOutput)
{
    var response = document.getElementById('response');
    var div = document.createElement('div');
    div.appendChild(document.createTextNode(messageOutput.from + " : "
        + messageOutput.text + " (" + messageOutput.time + ")"));
        response.appendChild(div);
    response.appendChild(div);////////////
}