var status = document.getElementById("valid_status").value;
var locale = document.getElementById("locale").value;

if (status === "success") {
    swal("Congrats", "Account created", "success");
}
if (status === "user.exist") {
    if (locale == null || locale === "en") {
        swal("Error", "Email already exist!", "error");
    } else {
        swal("Помилка", "Ця електрона пошта уже зареєстрована!", "error");
    }
}
if (status === "user.not.exist") {
    if (locale == null || locale === "en") {
        swal("Error", "There is no client with such an email!", "error");
    } else {
        swal("Помилка", "Немає клієнта з такою електронною поштою!", "error");
    }
}
if (status === "wrong.password") {
    if (locale == null || locale === "en") {
        swal("Incorrect password", "Enter your password again!", "error");
    } else {
        swal("Хибний пароль", "Введіть пароль ще раз!", "error");
    }
}
if (status === "error.wrong.int.number") {
    if (locale == null || locale === "en") {
        swal("Incorrect data", "You entered the wrong number. Try again!", "error");
    } else {
        swal("Некоректні дані", "Ви ввели не число. Спробуйте ще раз!", "error");
    }
}



const myModal = document.getElementById('myModal');
const myInput = document.getElementById('myInput');

myModal.addEventListener('shown.bs.modal', function () {
    myInput.focus()
});

function checkMatching() {
    const pass1 = document.getElementById('inputPassword').value;
    const pass2 = document.getElementById('inputPasswordAgain').value;
    if (pass1 !== pass2) {
        return swal("Error", "Password doesn't matched!", "error")
    }
}