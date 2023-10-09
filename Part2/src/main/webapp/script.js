// script.js
document.addEventListener("DOMContentLoaded", function () {
  const form = document.querySelector(".login-form");

  form.addEventListener("submit", function (event) {
    const idField = form.querySelector(".id");
    const passwordField = form.querySelector(".password");

    if (!idField.value || !passwordField.value) {
      alert("Please fill in all fields.");
      event.preventDefault();
    }
  });
});
