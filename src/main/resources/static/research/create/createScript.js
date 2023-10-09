document.addEventListener('DOMContentLoaded', function () {

    const form = document.getElementById('research-form');
    // const successMessage = document.getElementById('success-message');
    // const failMessage = document.getElementById('fail-message');

    form.addEventListener('submit', function (e) {
        e.preventDefault();

        const formData = new FormData(form);

        // Create the JSON object with the desired format
        const jsonData = {
            subject: formData.get('subject'),
            description: formData.get('description'),
            salary: parseFloat(formData.get('salary')),
            startDate: formData.get('startDate'),
            manager: {
                userId: parseInt(formData.get('userId')),
                // firstName: formData.get('firstName'),
                // lastName: formData.get('lastName'),
                // email: formData.get('email'),
                // password: formData.get('password'),
                // birthdate: formData.get('birthdate')
            }
        };

        // Send JSON data to your API
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
                // Handle the API response (if needed)
                console.log('API Response:', data);
                // failMessage.remove();
                alert("Research is created");
                // successMessage.style.display = 'block';
            })
            .catch(error => {
                // successMessage.remove();
                // failMessage.style.display = 'block';
                alert("Invalid Manger ID");
                console.error('Error:', error);
            });
    });
});