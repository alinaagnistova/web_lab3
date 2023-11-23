const MIN_X = -2;
const MAX_X = 2;
const MIN_Y = -3;
const MAX_Y = 5;
const MIN_COORD = 40;
const MAX_COORD = 280;
const TO_RECALC_COORD = 160;
const TO_RECALC_R = 80;


function checkArea(x, y, r) {
    let res = false;
    if (x <= 0 && x >= -r && y <= 0 && y >= -r) {
        res = true;
    }
    if (x >= 0 && x <= r / 2 && y >= 0 && y <= r) {
        res = true;
    }
    if (x <= 0 && y >= 0 && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2))) {
        res = true;
    }
    return res;
}
document.addEventListener('DOMContentLoaded', function () {
    const svg = document.querySelector('svg');
    svg.addEventListener('click', function (event) {
        let x = event.clientX;
        let y = event.clientY;
        let point = svg.createSVGPoint();
        point.x = x;
        point.y = y;
        let transformedPoint = point.matrixTransform(svg.getScreenCTM().inverse());
        let tx = transformedPoint.x;
        let ty = transformedPoint.y;
        //CheckClick
        if (!((tx >= MIN_COORD && tx <= MAX_COORD) && (ty >= MIN_COORD && ty <= MAX_COORD))) {
            showError(svg, 'Вы не попали в область');
            return;
        }
        const r = getRValue();
        if (r === -1) {
            showError(svg, 'R не выбран');
            return;
        }
        let toSendX = ((tx - TO_RECALC_COORD) / TO_RECALC_R * r).toFixed(5);
        let toSendY = ((TO_RECALC_COORD - ty) / TO_RECALC_R * r).toFixed(5);
        if (!(toSendX >= MIN_X && toSendX <= MAX_X && toSendY >= MIN_Y && toSendY <= MAX_Y)) {
            showError(svg, 'Выход значений за пределы');
            return;
        }
        try {
            clickSender([{name: 'x', value: toSendX}, {name: 'y', value: toSendY}, {name: 'r', value: r}]);
        } catch (e) {
            console.log("Error during sending values to Java");
        }
        let circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
        circle.setAttribute("cx", transformedPoint.x);
        circle.setAttribute("cy", transformedPoint.y);
        circle.setAttribute("r", "3");
        let isHit = checkArea(toSendX, toSendY, r);
        if (isHit) {
            circle.setAttribute("fill", "green");
        } else {
            circle.setAttribute("fill", "red");
        }
        svg.appendChild(circle);
    });
    // function checkClick(x, y) {
    //     if (!((x >= MIN_COORD && x <= MAX_COORD) && (y >= MIN_COORD && y <= MAX_COORD))) {
    //         showError(svg, 'Вы не попали в область');
    //         return;
    //     }
    //     const r = getRValue();
    //     if (r === -1) {
    //         showError(svg, 'R не выбран');
    //         return;
    //     }
    //     let toSendX = ((x - TO_RECALC_COORD) / TO_RECALC_R * r).toFixed(5);
    //     let toSendY = ((TO_RECALC_COORD - y) / TO_RECALC_R * r).toFixed(5);
    //     if (!(toSendX >= MIN_X && toSendX <= MAX_X && toSendY >= MIN_Y && toSendY <= MAX_Y)) {
    //         showError(svg, 'Выход значений за пределы допустимого');
    //         return;
    //     }
    //     console.log(`x: ${x}, normX: ${toSendX}`);
    //     console.log(`y: ${y}, normY: ${toSendY}`);
    //     try {
    //         clickSender([{name: 'x', value: toSendX}, {name: 'y', value: toSendY}, {name: 'r', value: r}]);
    //     } catch (e) {
    //         console.log("Error during sending values to Java");
    //     }
    //
    // }
});






