document.querySelectorAll('.calendar-day').forEach(function(dayBlock) {
    dayBlock.addEventListener('click', function() {
        // Get the specific day from the data-day attribute
        const date = this.id; // e.g., "2023-10-26"

        // Construct the URL dynamically
        // Example: Redirect to a page that shows details for that specific day
        window.location.href = `/tarefas/1/${date}`;

        // Or for a simpler, static redirect:
        // window.location.href = 'some-other-page.html';
    });
});