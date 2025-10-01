function calendarMonthRedirect() {
    const yearMonth = document.getElementById("yearMonthFilterInput").value;

    if(yearMonth != null && yearMonth !== "") {
        window.location.href = `/home/${yearMonth}`
    }
}