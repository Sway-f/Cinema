
if(document.getElementById('fid')){
    var cfid = document.getElementById('fid').value;
    console.log(cfid);
    if(document.querySelector('.chat[data-chat=person'+cfid+']')!=null){
        document.querySelector('.chat[data-chat=person'+cfid+']').classList.add('active-chat');
        document.querySelector('.person[data-chat=person'+cfid+']').classList.add('active');
    }
    else {
        document.querySelector('.person[data-chat=person'+cfid+']').classList.add('active');
    }

}


var friends = {
  list: document.querySelector('ul.people'),
  all: document.querySelectorAll('.left .people .person'),
  name: ''},

chat = {
  container: document.querySelector('.container .right'),
  current: null,
  person: null,
  // name: document.querySelector('.container .right .top .name')
    };

console.log(friends.list)
console.log(friends.all)



friends.all.forEach(function (f) {
  f.addEventListener('mousedown', function () {
    document.getElementById('change'+f.querySelector('.form').getAttribute("value")).submit();
    f.classList.contains('active') || setAciveChat(f);
  });
});

function setAciveChat(f) {
  friends.list.querySelector('.active').classList.remove('active');
  f.classList.add('active');
  chat.current = chat.container.querySelector('.active-chat');
  chat.person = f.getAttribute('data-chat');
  chat.current.classList.remove('active-chat');
  chat.container.querySelector('[data-chat="' + chat.person + '"]').classList.add('active-chat');
  // friends.name = f.querySelector('.name').innerText;
  // chat.name.innerHTML = friends.name;
}