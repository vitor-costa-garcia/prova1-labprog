let newX = 0, newY = 0, startX = 0, startY = 0;

const postIt = document.getElementsByClassName("task-postit-1")
const postItDrag = document.getElementsByClassName("task-postit-top-1")


for (let i = 0; i < postItDrag.length; i++) {

    postItDrag[i].addEventListener("mousedown", mouseDown)

    function mouseDown(e) {
        startX = e.clientX
        startY = e.clientY

        document.addEventListener("mousemove", mouseMove)
        document.addEventListener("mouseup", mouseUp);
    }

    function mouseMove(e) {
        newX = startX - e.clientX;
        newY = startY - e.clientY;

        startX = e.clientX;
        startY = e.clientY;

        postIt[i].style.left = (postIt[i].offsetLeft - newX) + "px";
        postIt[i].style.top = (postIt[i].offsetTop - newY) + "px";
        postIt[i].style.boxShadow = "#c5c5c5 2px 2px 5px";

        console.log({newX, newY});
    }

    function mouseUp(e) {
        document.removeEventListener("mousemove", mouseMove)
        postIt[i].style.boxShadow = "none";
    }
}

