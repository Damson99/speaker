$(document).ready(function()
{
    $('#scroll1').click(function()
    {
        $('#dropDownMenu1').stop().slideToggle(0);
   });

   $('#scroll2').click(function()
   {
        $('#dropDownMenu2').stop().slideToggle(0);
   });

   $('#scroll3').click(function()
   {
        $('#dropDownMenu3').stop().slideToggle(0);
   });

   $('#scroll4').click(function()
   {
        $('#dropDownMenu4').stop().slideToggle(0);
   });

   $('.scrollCom').click(function()
   {
        $('.com').stop().slideToggle(0);
   });

   $('#friends').click(function()
   {
        $('#scrollFriends').stop().slideToggle(0);
   });
});

function showLikes(likesPost)
{
    var div = document.getElementById('likes'+likesPost);
    if(div.style.display == "none")
    {
        div.style.display = ""
    }
    else
    {
        div.style.display = "none"
    }
}

function showFriends(friendsPost)
{
    var div = document.getElementById('friends');
    if(friendsPost==true)
    {
        div.style.display = ""
    }
    else
    {
        div.style.display = "none"
    }
}







