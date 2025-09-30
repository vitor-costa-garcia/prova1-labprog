function startResize() {
    var offsetX = 1;
    var offsetY = 0;
    const postIts = document.getElementsByClassName("task-postit-1");

    for (let i = 0; i < postIts.length; i++) {
        const postIt = postIts[i];
        postIt.style.width = "200px";
        postIt.style.height = "200px";
        postIt.style.left = offsetX * 200 + "px";
        postIt.style.top = offsetY * 200 + "px";
        if (offsetX === 6) {
            offsetY++;
            offsetX = 0;
        } else {
            offsetX++;
            /*
            const newPosition = {
                idTarefa: postIt.id, // make sure each div has a unique id in a data attribute
                x: postIt.offsetLeft,
                y: postIt.offsetTop
            };

            // Send to backend
            fetch('/tarefas/atualizar_pos', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newPosition)
            })
                .then(response => response.json())
                .then(data => console.log('Position updated:', data))
                .catch(err => console.error('Error updating position:', err));

            // Send the new size to the backend
            const newSize = {
                idTarefa: postIt.id,
                comprimento: postIt.offsetWidth,
                altura: postIt.offsetHeight
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
        }
        */
        }
    }
}