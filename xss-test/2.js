let data = new FormData();
data.append("passwordnew", "2");
data.append("confirm_passwordnew", "2");
fetch("http://localhost:8080/DAT152WebSearch/updatepassword", {
  method: "post",
  body: data,
});
