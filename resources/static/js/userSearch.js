function mainSearchUser()
{
  var input, filter, ul, li, a, i, txtValue;
  input = document.getElementById('searchInput');
  filter = input.value.toUpperCase();
  ul = document.getElementById("searchUl");
  li = ul.getElementsByClassName('searchResults');

  for (i = 0; i < li.length; i++)
   {
    a = li[i].getElementsByClassName("username")[0];
    txtValue = a.textContent || a.innerText;
    if (txtValue.toUpperCase().indexOf(filter) > -1)
    {
      li[i].style.display = "none";
    }
    else
    {
      li[i].style.display = "";
    }
  }
}

function searchUser()
{
  var input, filter, ul, li, a, i, txtValue;
  input = document.getElementById('searchInput');
  filter = input.value.toUpperCase();
  ul = document.getElementById("searchUl");
  li = ul.getElementsByClassName('active');

  for (i = 0; i < li.length; i++)
   {
    a = li[i].getElementsByClassName("username")[0];
    txtValue = a.textContent || a.innerText;
    if (txtValue.toUpperCase().indexOf(filter) > -1)
    {
      li[i].style.display = "";
    }
    else
    {
      li[i].style.display = "none";
    }
  }
}
