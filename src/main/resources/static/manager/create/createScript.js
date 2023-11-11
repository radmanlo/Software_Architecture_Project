document.addEventListener('DOMContentLoaded', function () {

    const form = document.getElementById('research-form');

    form.addEventListener('submit', function (e) {
        e.preventDefault();

        const formData = new FormData(form);

        fetch(`http://localhost:8080/manager/getByEmail?email=${formData.get("email")}`)
            .then(response =>{
                if (response.status === 302){
                    const jsonData = {
                        subject: formData.get('subject'),
                        description: formData.get('description'),
                        salary: parseFloat(formData.get('salary')),
                        startDate: formData.get('startDate'),
                        manager: {
                            email: formData.get("email"),
                        }
                    };
                    let confirmation = window.confirm("Are you sure you want to create this research?");
                    if (confirmation) {
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
                    }
                }
                else if (response.status === 404){
                    alert("Manager with this email address does not exists")
                }
                else {
                    alert("Internal Server Error!")
                }
            }).catch(error =>{
                console.log(error)
        })
    });
});