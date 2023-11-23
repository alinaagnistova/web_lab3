// let xInput = document.getElementById('valform:X');
// console.log(xInput.value)
// document.getElementById('valForm').addEventListener('submit', function (e) {
//     e.preventDefault();
//     let x = document.getElementById('x');
//     let y = document.getElementById('y');
//     let r = document.querySelectorAll('input[type="radio"]:checked')[0];
//
//     if (validate(x, y, r)) {
//         send(x.value, y.value, r.value);
//     }
//
// });

// function send(x, y, r) {
//     $.ajax({
//             type: "GET",
//             url: "/ControllerServlet",
//             async: false,
//             data: {"x": x, "y": y, "r": r},
//             success: function (data) {
//                 window.location.replace("./result.jsp")
//             }, error: function (xhr, textStatus, err) {
//                 showError(document.getElementById('buttons-table'), "readyState: " + xhr.readyState + "\n" +
//                     "responseText: " + xhr.responseText + "\n" +
//                     "status: " + xhr.status + "\n" +
//                     "text status: " + textStatus + "\n" +
//                     "error: " + err);
//             }
//         }
//     )
// }
function getRValue(){
    let value;
    if(document.forms['valForm']['valForm:r'][0].checked === true ){
        value = 1;
    }else if(document.forms['valForm']['valForm:r'][1].checked === true ){
        value = 2;
    }else if(document.forms['valForm']['valForm:r'][2].checked === true ){
        value = 3;
    }else if(document.forms['valForm']['valForm:r'][3].checked === true ){
        value = 4;
    }else if(document.forms['valForm']['valForm:r'][4].checked === true ){
        value = 5;
    }else{
        value = -1;
    }
    return value;
}
function showError(element, message) {
    const errorElement = document.createElement('div');
    errorElement.classList.add('error-message');
    errorElement.textContent = message;
    errorElement.style.color = 'red';
    errorElement.style.fontSize = '20px';
    errorElement.style.textAlign = 'left';
    element.parentNode.insertBefore(errorElement, element.nextSibling);
    setTimeout(function () {
        errorElement.remove();
    }, 2000);
}
//
// function validate(x, y, r) {
//     let replaceDot = val => val.replace(',', '.');
//     let x1 = replaceDot(x.value);
//     if (r == null) {
//         showError(r, "Необходимо выбрать значение R:(");
//         return false;
//     }
//     if (y == null) {
//         showError(y, "Необходимо выбрать значение Y:(");
//         return false;
//     }
//
//     if (x1 === "") {
//         showError(x, "Необходимо указать значение Y:(");
//         return false;
//     }
//
//     if (isNaN(x1)) {
//         showError(x, "Нет, так не надо. Надо вот так: X - число");
//         return false;
//     }
//
//     if (x1 < MIN_X || x1 > MAX_XY) {
//         showError(x, "X может быть любым числом из {-3;3}");
//         return false;
//     }
//     return true;
// }

function resetForm() {
    document.getElementById("valForm").reset();
}

