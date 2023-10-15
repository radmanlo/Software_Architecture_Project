document.addEventListener('DOMContentLoaded', function () {

    const form = document.getElementById('research-form');

    form.addEventListener('submit', function (e) {
        e.preventDefault();

        const formData = new FormData(form);

        const jsonData = {
            subject: formData.get('subject'),
            description: formData.get('description'),
            salary: parseFloat(formData.get('salary')),
            startDate: formData.get('startDate'),
            manager: {
                email: formData.get("email"),
            }
        };

        fetch('http://localhost:8080/research/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(jsonData),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                console.log('API Response:', data);
                alert("Research is created");
            })
            .catch(error => {
                alert("Invalid Manger ID");
                console.error('Error:', error);
            });
    });
});