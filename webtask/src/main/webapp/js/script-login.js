document.getElementById("form-register").classList.add("hidden");
document.getElementById("reg-link").onclick = function () {
    document.getElementById("form-login").classList.add("hidden");
    document.getElementById("form-register").classList.remove("hidden");
};
document.getElementById("login-link").onclick = function () {
    document.getElementById("form-register").classList.add("hidden");
    document.getElementById("form-login").classList.remove("hidden");
}; 