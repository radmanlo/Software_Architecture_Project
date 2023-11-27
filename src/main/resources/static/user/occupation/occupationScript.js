let localUser = ""
document.addEventListener('DOMContentLoaded', function () {
    //location.reload();
    const form = document.getElementById('occupation-form');
    form.addEventListener('submit', function (e) {
        e.preventDefault();
        const formData = new FormData(form);
        console.log(formData.get("userEmail"))
        fetch(`http://localhost:8080/researcher/getByEmail?email=${formData.get("userEmail")}`)
            .then((respond) => {
                if (respond.status === 302){
                    respond.json().then(data =>{
                        localUser = data
                        let occupationContainer = document.getElementById('update-create-container');
                        occupationContainer.innerHTML ='';
                        let occContainer = document.getElementById('occupation-container');
                        occContainer.innerHTML='';
                        occupationCard(data)
                    })
                }
                else if (respond.status === 404){
                    fetch(`http://localhost:8080/manager/getByEmail?email=${formData.get("userEmail")}`)
                        .then((respond) =>{
                            if (respond.status === 302){
                                respond.json().then(data =>{
                                    localUser = data
                                    let occupationContainer = document.getElementById('occupation-container');
                                    occupationContainer.innerHTML='';
                                    occupationCard(data)
                                })
                            }
                            else if (respond.status === 404){
                                alert("Bad Request Status!")
                            }
                            else{
                                alert("Internal Server Error!")
                            }
                        })
                }
                else{
                    alert("Internal Server Error!")
                }
            }).catch((error) => {
                console.error('Error fetching manager data:', error);
            });
    });
});


function occupationCard(data) {
    fetch(`http://localhost:8080/occupation/getByUserEmail?userEmail=${data.email}`)
        .then(response =>{
            if (response.status === 302){
                response.json().then(occupation => {
                    let occupationCard = document.createElement('div');
                    occupationCard.classList.add('occupation-card');
                    occupationCard.innerHTML = `
                        <div class="field-label">Employer:</div>
                        <div class="field-value">${occupation.employer}</div>
                        <div class="field-label">Year of experience:</div>
                        <div class="field-value">${occupation.experience}</div>
                        <button class="update-button">Update</button>
                        <button class="delete-button">Delete</button>
                    `;
                    occupationCard.querySelector('.update-button').addEventListener('click', () => {
                        event.preventDefault();
                        let occupationContainer = document.getElementById('update-create-container');
                        occupationContainer.innerHTML ='';
                        createUpdateCard(occupation)
                    })
                    occupationCard.querySelector('.delete-button').addEventListener('click', () =>{
                        event.preventDefault();
                        let confirmation = window.confirm("Are you sure you want to update your occupation?");
                        if (confirmation) {
                            fetch(`http://localhost:8080/occupation/delete?occupationId=${occupation.occupationId}`, {
                                method: "DELETE",
                                headers: {
                                    'Content-Type': 'application/json'
                                }
                            }).then(response =>{
                                if (response.status === 200){
                                    alert("Occupation is deleted!")
                                    let occContainer = document.getElementById('occupation-container');
                                    occContainer.innerHTML='';
                                    createOccupationCard()
                                }
                                else if (response.status === 404){
                                    alert("Not Found Status Code!")
                                }
                                else{
                                    alert("Internal Server Error!")
                                }
                            }).catch(err =>
                                console.log(err)
                            )
                        }
                    })
                    let occupationContainer = document.getElementById('occupation-container');
                    occupationContainer.appendChild(occupationCard);
                })
            }
            else if (response.status === 404){
                createOccupationCard()
            }
            else{
                alert("Internal Server Error!")
            }
        })
}

function createUpdateCard(occupation){
    let updateCard = document.createElement('div')
    updateCard.classList.add('update-occupation-card')
    updateCard.innerHTML = `
        <div class="field-label">Employer: <input type="text" name="employer" id="employer"></div>
        <div class="field-label">Experience: <input type="text" name="experience" id="experience"></div> 
        <button class="update-button">Update</button>
        <button class="cancelBtn">Cancel</button>
    `;
    updateCard.querySelector('.update-button').addEventListener('click', () => {
        event.preventDefault();
        const newEmployer = document.getElementById('employer').value
        const newExperience = document.getElementById('experience').value
        let confirmation = window.confirm("Are you sure you want to update your occupation?");
        if (confirmation) {
            let updateOcc ={
                occupationId: occupation.occupationId,
                employer: newEmployer,
                experience: newExperience
            }
            fetch(`http://localhost:8080/occupation/update`, {
                method: "PUT",
                headers:{
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(updateOcc)
            }).then(response => {
                if (response.status === 202){
                    alert("Your occupation is updated!")
                    let occupationContainer = document.getElementById('update-create-container');
                    occupationContainer.innerHTML ='';
                    let occContainer = document.getElementById('occupation-container');
                    occContainer.innerHTML='';
                    occupationCard(localUser)
                }
                else if (response.status === 404){
                    alert("Occupation is not found!")
                }
                else{
                    alert("Internal Server Error!")
                }
            })
        }
    })
    let occupationContainer = document.getElementById('update-create-container');
    occupationContainer.appendChild(updateCard);
}

function createOccupationCard(){
    let createCard = document.createElement('div')
    createCard.classList.add('update-occupation-card')
    createCard.innerHTML = `
        <p>PLease Specify Your Occupation</p>
        <div class="field-label">Employer: <input type="text" name="employer" id="employer"></div>
        <div class="field-label">Experience: <input type="text" name="experience" id="experience"></div>
        <button class="createBtn">Create</button>
        <button class="cancelBtn">Cancel</button>
    `;
    createCard.querySelector('.createBtn').addEventListener('click', () => {
        event.preventDefault();
        const newEmployer = document.getElementById('employer').value
        const newExperience = document.getElementById('experience').value
        let confirmation = window.confirm("Are you sure you want to create?");
        if (confirmation) {
            let occupation ={
                employer: newEmployer,
                experience: newExperience,
                user: localUser
            }
            fetch(`http://localhost:8080/occupation/create`, {
                method: "POST",
                headers:{
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(occupation)
            }).then(response => {
                if (response.status === 201){
                    alert("Occupation is created")
                    let occupationContainer = document.getElementById('update-create-container');
                    occupationContainer.innerHTML = '';
                    occupationCard(localUser)
                }
                else if (response.status === 400){
                    alert("Bad Request Status!")
                }
                else{
                    alert("Internal Server Error!")
                }

            }).catch(error =>
                console.log(error)
            )
        }
    })
    let occupationContainer = document.getElementById('update-create-container');
    occupationContainer.appendChild(createCard);
}

