const postIts = document.getElementsByClassName("task-postit-1");

for (let i = 0; i < postIts.length; i++) {
    const handle = postIts[i].querySelector(".resize-handle");

    handle.addEventListener("mousedown", startResize);

    function startResize(e) {
        e.preventDefault();
        document.addEventListener("mousemove", resizing);
        document.addEventListener("mouseup", stopResize);
    }

    function resizing(e) {
        const rect = postIts[i].getBoundingClientRect();
        const newWidth = e.clientX - rect.left;
        const newHeight = e.clientY - rect.top;

        postIts[i].style.width = newWidth + "px";
        postIts[i].style.height = newHeight + "px";
    }

    function stopResize(e) {
        document.removeEventListener("mousemove", resizing);

        // Send the new size to the backend
        const newSize = {
            idTarefa: postIts[i].id,
            comprimento: postIts[i].offsetWidth,
            altura: postIts[i].offsetHeight
        };

        fetch('/tarefas/atualizar_tamanho', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newSize)
        })
            .then(response => response.json())
            .then(data => console.log('Size updated:', data))
            .catch(err => console.error('Error updating size:', err));

        document.removeEventListener("mouseup", stopResize);
    }
}