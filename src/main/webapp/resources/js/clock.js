document.addEventListener("DOMContentLoaded", function () {
    setTime();
    setInterval(setTime, 8000)
});

function setTime(){
    let date = new Date();
    let time = document.getElementById('clock');
    time.innerHTML = `${date.toLocaleTimeString()}`;
}
