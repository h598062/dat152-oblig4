fetch("http://localhost:8080/DAT152WebSearch/updatepassword", {
  method: "post",
  body: "passwordnew=test&confirm_passwordnew=test",
  headers: {
    "Content-Type": "application/x-www-form-urlencoded",
  },
});
