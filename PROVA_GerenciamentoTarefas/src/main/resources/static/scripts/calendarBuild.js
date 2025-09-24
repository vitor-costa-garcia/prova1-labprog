function myFunction() {
    var today = new Date();
    var month = today.getMonth();
    var daysLeft = daysInMonth(month + 1, today.getFullYear())
    var firstDay = monthStartDay(month, today.getFullYear())
    var currentDay = 0
    var placingBlanks = true
    var week = 1
    var validCurrentDay = 1
    var currentWeek = document.getElementById('calendar-w' + week);

    while (daysLeft+1) {
        const newDay = document.createElement("div")

        if (currentDay === firstDay){
            placingBlanks = false

        }
        if(placingBlanks === true){
            newDay.classList.add("calendar-day-blank")
        } else {
            newDay.classList.add("calendar-day")
            newDay.innerHTML = String(validCurrentDay)
            validCurrentDay++
        }

        currentWeek.appendChild(newDay)
        if((currentDay+1)%7===0){
            week++
            currentWeek = document.getElementById('calendar-w' + week);
        }
        currentDay++
        daysLeft--
    }

    while (currentDay !== 7*5){
        const newDay = document.createElement("div")
        newDay.classList.add("calendar-day-blank")
        currentWeek.appendChild(newDay)
        currentDay++
    }
}

function daysInMonth(month,year) {
    return new Date(year, month, 0).getDate(); //0-sunday 1-monday 2-tuesday 3-wednesday 4-thursday 5- friday 6-saturday
}

function monthStartDay(month,year) {
    return new Date(year, month, 1).getDay(); //0-sunday 1-monday 2-tuesday 3-wednesday 4-thursday 5- friday 6-saturday
}

myFunction();