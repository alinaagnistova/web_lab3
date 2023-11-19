const MIN_X = -3;
const MAX_XY = 3;
const MIN_Y = -5;
const MIN_COORD = 40;
const MAX_COORD = 280;
const TO_RECALC_COORD = 160;
const TO_RECALC_R = 80;
// TODO переписать код
document.addEventListener('DOMContentLoaded', function () {
    const svg = document.querySelector('svg');
    svg.addEventListener('click', function (event) {
        let x = event.clientX;
        let y = event.clientY;
        let point = svg.createSVGPoint();
        point.x = x;
        point.y = y;
        let transformedPoint = point.matrixTransform(svg.getScreenCTM().inverse());
        checkClick(transformedPoint.x, transformedPoint.y);
        let circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
        circle.setAttribute("cx", transformedPoint.x);
        circle.setAttribute("cy", transformedPoint.y);
        circle.setAttribute("r", "3");
        circle.setAttribute("fill", "red");
        svg.appendChild(circle);
    });

    function checkClick(x, y) {
        if (!((x >= MIN_COORD && x <= MAX_COORD) && (y >= MIN_COORD && y <= MAX_COORD))) {
            showError(svg, 'Вы не попали в область');
            return;
        }
        const r = document.querySelector('input[name="r"]:checked');
        if (!r) {
            showError(svg, 'R не выбран');
            return;
        }
        let radius = r.value;
        let toSendX = ((x - TO_RECALC_COORD) / TO_RECALC_R * radius).toFixed(5);
        let toSendY = ((TO_RECALC_COORD - y) / TO_RECALC_R * radius).toFixed(5);
        if (!(toSendX >= MIN_X && toSendX <= MAX_XY && toSendY >= MIN_Y && toSendY <= MAX_XY)) {
            showError(svg, 'Выход значений за пределы допустимого');
            return;
        }
        $.ajax({
            type: "GET",
            url: "/ControllerServlet",
            async: false,
            data: {"x": toSendX, "y": toSendY, "r": radius},
            success: function (data) {
                window.location.replace('./result.jsp');
            },
            error: function (xhr, textStatus, err) {
                showError(document.getElementById('buttons-table'), "readyState: " + xhr.readyState + "\n" +
                    "responseText: " + xhr.responseText + "\n" +
                    "status: " + xhr.status + "\n" +
                    "text status: " + textStatus + "\n" +
                    "error: " + err);
            }
        });
    }
})

