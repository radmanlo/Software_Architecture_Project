let managerEmail = ""
document.addEventListener('DOMContentLoaded', function () {
    let form = document.getElementById('manager-form');
    form.addEventListener('submit', function (e) {
        e.preventDefault();
        let formData = new FormData(form);
        console.log("Email =>> " + formData.get("email"))
        fetch(`http://localhost:8080/research/researchesByManager?managerEmail=${formData.get("email")}`)
            .then((respond) => {
                if (respond.status === 302){
                    respond.json().then(researches =>{
                        if (researches.length === 0){
                            alert("You are not manager of any research!")
                        }
                        else{
                            managerEmail = formData.get("email")
                            let updateContainer = document.getElementById('update-container');
                            let researchContainer = document.getElementById('research-container');
                            updateContainer.innerHTML = '';
                            researchContainer.innerHTML ='';
                            researches.forEach((research) => {
                                console.log("I am in for each!")
                                createResearchCard(research);
                            })
                        }
                    })
                }
                else if (respond.status === 404){
                    alert("Manager is not found")
                }
                else{
                    alert("Internal Server Error")
                }
            }).catch((error) => {
                console.error('Error fetching manager data:', error);
            });
    });
});

function fetchResearchApi(){
    fetch(`http://localhost:8080/research/researchesByManager?managerEmail=${managerEmail}`)
        .then((respond) => {
            if (respond.status === 302){
                respond.json().then(researches =>{
                    if (researches.length === 0){
                        alert("You are not manager of any research!")
                    }
                    else{
                        researches.forEach((research) => {
                            createResearchCard(research);
                        })
                    }
                })
            }
            else if (respond.status === 404){
                alert("Manager is not found")
            }
            else{
                alert("Internal Server Error")
            }
        }).catch((error) => {
            console.error('Error fetching manager data:', error);
        }
    );
}

function createResearchCard(research) {
    let researchCard = document.createElement('div');
    researchCard.classList.add('research-card');
    researchCard.innerHTML = `
        <div class="field-label">Research ID:</div>
        <div class="field-value">${research.researchId}</div>
        <div class="field-label">Subject:</div>
        <div class="field-value">${research.subject}</div>
        <div class="field-label">Description:</div>
        <div class="field-value">${research.description}</div>
        <div class="field-label">Salary:</div>
        <div class="field-value">${research.salary}</div>
        <div class="field-label">Start Date:</div>
        <div class="field-value">${research.startDate}</div>
        <button class="update-button">Update</button>
        <button class="delete-button">delete</button>
        <button class="applicants-button">Applicants</button>
    `;
    researchCard.querySelector('.update-button').addEventListener('click', () =>{
        event.preventDefault();
        let updateCard = document.createElement('div')
        updateCard.classList.add('update-occupation-card')
        let updateContainer = document.getElementById('update-container');
        updateContainer.innerHTML = '';
        updateCard.innerHTML = `
            <label for="subject">Subject:</label>
            <input type="text" id="subject" name="subject">
            <label for="description">Description:</label>
            <textarea id="description" name="description" rows="5"></textarea>
            <label for="salary">Salary:</label>
            <input type="number" id="salary" name="salary" step="0.01">
            <label for="startDate">Start Date:</label>
            <input type="date" id="startDate" name="startDate">
            <button class="update-update-Button">Update</button>
            <button class="cancelBtn">Cancel</button>
        `;
        updateCard.querySelector('.update-update-Button').addEventListener('click', ()=>{
            event.preventDefault();
            let newSubject = document.getElementById('subject').value
            let newDescription = document.getElementById('description').value
            let newSalary = document.getElementById('salary').value
            let newStartDate = document.getElementById('startDate').value
            let updatedResearch = {
                researchId: research.researchId,
                subject: newSubject,
                description: newDescription,
                salary: newSalary,
                startDate: newStartDate,
            }
            let confirmation = window.confirm("Are you sure you want to update your research?");
            if (confirmation) {
                fetch("http://localhost:8080/research/update", {
                    method:"PUT",
                    headers:{
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(updatedResearch)
                }).then(response =>{
                    if (response.status === 200){
                        alert("Research is Updated!")
                        fetchResearchApi()
                    }
                    else if (response.status === 404){
                        alert("research is not found!")
                    }
                    else{
                        alert("Internal Server Error!")
                    }
                }).catch(error => {
                    console.log(error)
                })
            }
        })
        updateContainer = document.getElementById('update-container');
        updateContainer.appendChild(updateCard);
    })
    researchCard.querySelector(".delete-button").addEventListener('click', ()=>{
        event.preventDefault();
        let confirmation = window.confirm("Are you sure you want to delete this research?");
        if (confirmation) {
            fetch(`http://localhost:8080/research/delete?researchId=${research.researchId}`, {
                method:"DELETE",
                headers:{
                    'Content-Type': 'application/json',
                },
            }).then(response =>{
                if (response.status === 200){
                    alert("Research is Deleted!")
                    let updateContainer = document.getElementById('update-container');
                    let researchContainer = document.getElementById('research-container');
                    updateContainer.innerHTML = '';
                    researchContainer.innerHTML ='';
                    fetchResearchApi()
                }
                else if (response.status === 404){
                    alert("research is not found!")
                }
                else{
                    alert("Internal Server Error!")
                }
            }).catch(error => {
                console.log(error)
            })
        }
    })
    researchCard.querySelector(".applicants-button").addEventListener('click', () => {
        event.preventDefault();
        localStorage.setItem("research", JSON.stringify(research))
        window.location.href = "applications/respond.html";
    })
    researchContainer = document.getElementById('research-container');
    researchContainer.appendChild(researchCard);
}
