function myFunction() {

    //Data atual
    const dateNow = new Date();

    //Pego a data vinda do caminho ano e mês
    const yearMonth = document.getElementsByClassName("container-home")[0].id

    //Separo o ano do mês
    const yearSelect = yearMonth.split('-')[0]
    const monthSelect = yearMonth.split('-')[1]

    //Crio o objeto Date do ano e mês
    var today = new Date(Number(yearSelect), Number(monthSelect)-1, 1);
    console.log(today);

    //Mês
    var month = today.getMonth();
    console.log(month);

    //Dias no mês
    var daysLeft = daysInMonth(month+1, today.getFullYear())
    console.log(daysLeft);

    //Primeiro dia da semana do mês do mês 0Domingo 6Sábado
    var firstDay = monthStartDay(month+1, today.getFullYear())
    console.log(firstDay);

    //Marcador do dia atual sendo criado
    var currentDay = 0

    //Flag colocar dias inválidos
    var placingBlanks = true

    //Marcador semana atual
    var week = 1

    //Dia válido atual
    var validCurrentDay = 1

    //Objeto html semana atual
    var currentWeek = document.getElementById('calendar-w' + week);

    //Enquanto houverem dias restantes a serem colocados
    while (validCurrentDay < daysInMonth(month + 1, today.getFullYear())+1) {
        //Crio uma nova janela de dia para o calendário
        const newDay = document.createElement("div")

        //Se o dia atual for o primeiro da do mês, ativo os dias válidos
        if (currentDay === firstDay){
            placingBlanks = false
        }

        //Se os dias válidos estiverem inativos, coloco uma janela de dia vazia
        if(placingBlanks === true){
            newDay.classList.add("calendar-day-blank")
        } else { //Se não, coloco uma janela de dia válido com o id representando o dia alvo
            var monthFix = today.getMonth()+1 < 10 ? `0${today.getMonth()+1}` : `${today.getMonth()+1}`;
            var dayFix = validCurrentDay < 10 ? `0${validCurrentDay}` : `${validCurrentDay}`;
            var currentDateString = `${today.getFullYear()}-${today.getMonth()+1}-${validCurrentDay}`
            newDay.id = `${today.getFullYear()}-${monthFix}-${dayFix}`;
            const dayNumber = document.createElement("p")
            newDay.classList.add("calendar-day")
            dayNumber.innerHTML = String(validCurrentDay)

            if(currentDateString === `${dateNow.getFullYear()}-${dateNow.getMonth()+1}-${dateNow.getDate()}`){
                dayNumber.classList.add("day-number-today")
                newDay.classList.add("calendar-day-today")
            }

            newDay.appendChild(dayNumber)
            validCurrentDay++
        }

        currentWeek.appendChild(newDay)
        if((currentDay+1)%7 === 0){
            week++;
            currentWeek = document.getElementById('calendar-w' + week);
        }
        currentDay++
        daysLeft--
    }

    const lastDay = currentDay;

    while (currentDay !== 7*6){
        const newDay = document.createElement("div")
        newDay.classList.add("calendar-day-blank")
        currentWeek.appendChild(newDay)
        currentDay++

        if((currentDay)%7===0){
            week++
            currentWeek = document.getElementById('calendar-w' + week);
        }
    }

    currentWeek = document.getElementById("calendar-w6");
    if (lastDay >= 36) {
        currentWeek.style.display = "flex";
    } else {
        currentWeek.style.display = "none";
    }
}

function daysInMonth(month,year) {
    var targetDate = new Date(year, month, 0);
    return targetDate.getDate()
}

function monthStartDay(month,year) {
    var targetDate = new Date(year, month-1, 1); //0-sunday 1-monday 2-tuesday 3-wednesday 4-thursday 5- friday 6-saturday
    return targetDate.getDay();
}

myFunction();