var status = document.getElementById("valid_status").value;
var locale = document.getElementById("locale").value;

if (status === "success") {
    swal("Congrats", "Account created", "success");
}
if (status === "emptyEmail") {
    if (locale == null || locale === "en") {
        swal("Incorrect data", "Empty email field!", "error");
    } else {
        swal("Некоректні дані", "Ви не ввели пошту", "error");
    }
}
if (status === "error.enter.invalid.email") {
    if (locale == null || locale === "en") {
        swal("Incorrect data", "Empty email field!", "error");
    } else {
        swal("Некоректні дані", "Ви не ввели пошту", "error");
    }
}
if (status === "error.wrong.int.number") {
    if (locale == null || locale === "en") {
        swal("Incorrect data", "You entered the wrong number. Try again!", "error");
    } else {
        swal("Некоректні дані", "Ви ввели не число. Спробуйте ще раз!", "error");
    }
}