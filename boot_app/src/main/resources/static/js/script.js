function deleteTask(cid){
  console.log(cid)
    swal({
        title: "Are you sure?",
        text: "Once deleted, you will not be able to recover your task!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
      .then((willDelete) => {
        if (willDelete) {
            window.location="delete/"+cid;
          }
        else {
          swal("Your Task is safe!");
        }
      });
}


window.onload = function(){

  var username = document.getElementById("Username");
  var email = document.getElementById("email");
  var pwd = document.getElementById("pwd");
  var cpwd = document.getElementById("cpwd");
  var check = document.getElementById("checkbox")
  var messageBox = document.getElementById("message");

  username.addEventListener("keyup",()=>{
      let usernameContent = username.value;
      messageBox.innerText=""
      if(usernameContent.length <4  || usernameContent.length >8){
          messageBox.innerText="Username should be 4 to 8 character long !!"
      }else{
          messageBox.innerText=""
      }
  });

  pwd.addEventListener("keyup",()=>{
      let password = pwd.value
      messageBox.innerText=""
      if(password.length <= 4){
          messageBox.innerText="password should be atleast 5 character long !!"
      }else{
          messageBox.innerText=""
      }
  });

  cpwd.addEventListener("keyup",()=>{
      let cpassword = cpwd.value
      messageBox.innerText=""
      if(cpassword!==pwd.value){
          messageBox.innerText="password doesn't matched !!"
      }else{
          messageBox.innerText=""
      }
  });

}

function formValidate(){
  console.log("inside function")
  let username = document.getElementById("Username");
  let email = document.getElementById("email");
  let check = document.getElementById("checkbox");
  let messageBox = document.getElementById("message");
  let flag = false;
  messageBox.innerText=""
  if(check.checked==false){
    messageBox.innerText="You need to check the box !!"
    return false;
  }else if(username.value==""){
    messageBox.innerText="username can not be blank !!"
    return false;
  }else if(email.value==""){
    messageBox.innerText="email can not be blank !!";
    return false;
  }

  var request= new XMLHttpRequest();
  request.open("POST","integrity?username="+username.value+"&email="+email.value,true);
  request.onreadystatechange=function(){
    if (this.readyState == 4 && this.status == 200) {
      if(this.responseText=="taken"){
        messageBox.innerText="username or email already exist !!"
        flag = false;
      }
    }
  }
  request.send();
}
