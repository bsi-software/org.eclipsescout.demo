//At the very first (before the html body gets loaded) redirect to the proper login page, if necessary
redirectIfNecessary();

function redirectIfNecessary(){
  var href = document.location.href;
  var loginUrl;
  try {
    loginUrl = sessionStorage.getItem("loginUrl");
  }
  catch(ex) {
    //May fail on old browser not supporting html 5 (IE 7)
  }

  //Redirect to original login url if url is j_security_check (after login failure), otherwise the check for mobile or tablet does not work anymore
  if(href.indexOf("/j_security_check") > 0) {
    if(loginUrl==null) {
      //Only used as fallback if login url is not stored properly
      loginUrl = href.substring(0, href.lastIndexOf('/'));
    }
    window.location.href=loginUrl;
  }
}

function setCookie(name,value, duration) {
  var cookie = name + "=" + value;
  if(duration) {
    var date = new Date();
    date = new Date(date.getTime() + duration);
    cookie +="; expires=" + date.toGMTString() + ";";
  }
  document.cookie = cookie;
}

function getCookie(name) {
  var cookies = document.cookie.split('; ');
  for(var i=0;i < cookies.length;i++) {
    var cookie = cookies[i];
    var cookieContent = cookie.split("=");
    if(cookieContent[0] === name) {
      return cookieContent[1];
    }
  }
}

function areCookiesDisabled() {
  setCookie("cookieCheck", "enabled", 15000);
  var cookieValue = getCookie("cookieCheck");
  if(cookieValue !== "enabled") {
    return true;
  }
}

$(document).ready(function(){
  // Cookies are only necessary for form authentication in tomcat, seems to be a bug in Tomcat 7. Rap does not require cookies.
  if(areCookiesDisabled()) {
    $('#nocookiebox').show();
    return;
  }

  var href = document.location.href;
  try {
    //Save login url for a proper redirect after a login failure
    sessionStorage.setItem("loginUrl", href);
  }
  catch(ex) {
    //May fail on old browser not supporting html 5 (IE 7)
  }

  if (href.indexOf("/mobile") > 0 || (href.indexOf("/tablet") > 0)) {
    $('#mainbox-mobile').show();
  }
  else {
    $('#mainbox').show();
  }

  $('#crmintern').change(function(){
    if(this.checked){
      $('#infotext').fadeIn(400);
    }else{
      $('#infotext').fadeOut(400);
    }
  });

  $('#name').focus(function(){
    if($('#name').val() == "Benutzername"){
      $('#name').val('').addClass('active');
    }
  })

  $('#fakepassword').focus(function(){
    $('#fakepassword').hide();
    $('#password').show();
    $('#password').focus();
  })

  $(window).resize(function(){
    if($(window).width() > 900){
      $("#bsilogo").css('marginLeft', $(window).width()-23-122+'px');
    }else{
      $("#bsilogo").css('marginLeft', 900-23-122+'px');
    }
  })

  $(window).resize();
});
