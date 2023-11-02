
// import axios from 'https://cdn.jsdelivr.net/npm/axios';

document.addEventListener('DOMContentLoaded', function () {
    //location.reload();
    const form = document.getElementById('collaboration-form');
    form.addEventListener('submit', function (e) {
        e.preventDefault();
        let pendingContainer = document.getElementById('pending-container');
        pendingContainer.innerHTML=``;
        let acceptContainer = document.getElementById('Accepted-container');
        acceptContainer.innerHTML=``;
        let cancelContainer = document.getElementById('Rejected-container');
        cancelContainer.innerHTML=``;
        const formData = new FormData(form);
        console.log(formData.get("userEmail"))
        fetch(`http://localhost:8080/collaboration/getByUserEmail?userEmail=${formData.get("userEmail")}`)
            .then((respond) => respond.json())
            .then((data) => {
                data.forEach((collItem) => {
                    createApplicationCard(collItem);
                })
            })
            .catch((error) => {
                console.error('Error fetching research data:', error);
            });
    });
});

function createApplicationCard(collItem) {

    let acceptCard = document.createElement('div');
    acceptCard.classList.add('accept-card');
    let rejectCard = document.createElement('div');
    rejectCard.classList.add('reject-card');
    let pendingCard = document.createElement('div');
    pendingCard.classList.add('pending-card');
    console.log("I am here");
    if (collItem.status === "PENDING") {
        pendingCard.innerHTML = `
            <div class="field-label">Collaboration ID:</div>
            <div class="field-value">${collItem.collaborationId}</div>
            <div class="field-label">Cover Letter:</div>
            <div class="field-value">${collItem.coverLetter}</div>
            <div class="field-label">Applicant Email:</div>
            <div class="field-value">${collItem.user.email}</div>
            <div class="field-label">Applicant Name:</div>
            <div class="field-value">${collItem.user.firstName} ${collItem.user.lastName}</div>
            <div class="field-label">Occupation:</div>
            <div class="field-value">${collItem.user.occupation}</div>
            <button class="delete-button">Delete</button>
        `;
        pendingCard.querySelector('.delete-button').addEventListener('click', () => {
            event.preventDefault();
            const confirmation = window.confirm("Do you want delete your application?");
            if (confirmation) {
                axios.delete(`http://localhost:8080/collaboration/delete?collId=${collItem.collaborationId}`).then((response) => {
                    // console.log(response.json())
                    if (response.status === 200) {
                        alert("Deleted Successfully")
                        // pendingCard.innerHTML = '';
                        // cancelCard.innerHTML = '';
                        let applicantContainer = document.getElementById('pending-container');
                        applicantContainer.innerHTML=``;
                        let acceptContainer = document.getElementById('Accepted-container');
                        acceptContainer.innerHTML=``;
                        let cancelContainer = document.getElementById('Rejected-container');
                        cancelContainer.innerHTML=``;
                        fetch(`http://localhost:8080/collaboration/getByUserEmail?userEmail=${collItem.user.email}`)
                            .then((respond) => respond.json())
                            .then((data) => {
                                data.forEach((collItem) => {
                                    createResearchCard(collItem);
                                })
                            })
                            .catch((error) => {
                                console.error('Error fetching research data:', error);
                            });
                        // location.href = `http://localhost:8080/research/respondApplication/respond.html`;
                    } else
                        alert("Something is wrong")
                })
            }
        });
    }
    else if (collItem.status === "ACCEPTED"){
        acceptCard.innerHTML = `
            <div class="field-label">Collaboration ID:</div>
            <div class="field-value">${collItem.collaborationId}</div>
            <div class="field-label">Cover Letter:</div>
            <div class="field-value">${collItem.coverLetter}</div>
            <div class="field-label">Applicant Email:</div>
            <div class="field-value">${collItem.user.email}</div>
            <div class="field-label">Applicant Name:</div>
            <div class="field-value">${collItem.user.firstName} ${collItem.user.lastName}</div>
            <div class="field-label">Occupation:</div>
            <div class="field-value">${collItem.user.occupation}</div>
            <button class="delete-button">Delete</button>
        `;
        acceptCard.querySelector('.delete-button').addEventListener('click', () => {
            event.preventDefault();
            const confirmation = window.confirm("Do you want delete your application?");
            if (confirmation) {
                axios.delete(`http://localhost:8080/collaboration/delete?collId=${collItem.collaborationId}`)
                    .then((response) => {
                        // console.log(response.json())
                        if (response.status === 200) {
                            alert("Deleted Successfully")
                            // pendingCard.innerHTML = '';
                            // cancelCard.innerHTML = '';
                            let applicantContainer = document.getElementById('pending-container');
                            applicantContainer.innerHTML=``;
                            let acceptContainer = document.getElementById('Accepted-container');
                            acceptContainer.innerHTML=``;
                            let cancelContainer = document.getElementById('Rejected-container');
                            cancelContainer.innerHTML=``;
                            fetch(`http://localhost:8080/collaboration/getByUserEmail?userEmail=${collItem.user.email}`)
                                .then((respond) => respond.json())
                                .then((data) => {
                                    data.forEach((collItem) => {
                                        createResearchCard(collItem);
                                    })
                                })
                                .catch((error) => {
                                    console.error('Error fetching research data:', error);
                                });
                            // location.href = `http://localhost:8080/research/respondApplication/respond.html`;
                        } else
                            alert("Something is wrong")
                    })
            }
        });
    }
    else {
        rejectCard.innerHTML = `
            <div class="field-label">Collaboration ID:</div>
            <div class="field-value">${collItem.collaborationId}</div>
            <div class="field-label">Cover Letter:</div>
            <div class="field-value">${collItem.coverLetter}</div>
            <div class="field-label">Applicant Email:</div>
            <div class="field-value">${collItem.user.email}</div>
            <div class="field-label">Applicant Name:</div>
            <div class="field-value">${collItem.user.firstName} ${collItem.user.lastName}</div>
            <div class="field-label">Occupation:</div>
            <div class="field-value">${collItem.user.occupation}</div>
        `;
    }

    if(collItem.status === "ACCEPTED"){
        let acceptContainer = document.getElementById('Accepted-container');
        acceptContainer.appendChild(acceptCard);
    }
    else if (collItem.status === "PENDING"){
        let pendingContainer = document.getElementById('pending-container');
        pendingContainer.appendChild(pendingCard);
    }
    else{
        let rejectContainer = document.getElementById('Rejected-container');
        rejectContainer.appendChild(rejectCard);
    }
}

// function createResearchCard(researchItem) {
//     let researchCard = document.createElement('div');
//     researchCard.classList.add('research-card');
//     researchCard.innerHTML = `
//         <div class="field-label">Research ID:</div>
//         <div class="field-value">${researchItem.researchId}</div>
//         <div class="field-label">Subject:</div>
//         <div class="field-value">${researchItem.subject}</div>
//         <div class="field-label">Description:</div>
//         <div class="field-value">${researchItem.description}</div>
//         <div class="field-label">Salary:</div>
//         <div class="field-value">${researchItem.salary}</div>
//         <div class="field-label">Start Date:</div>
//         <div class="field-value">${researchItem.startDate}</div>
//         <div class="field-label">Manager:</div>
//         <div class="field-value">${researchItem.manager.firstName} ${researchItem.manager.lastName}</div>
//
//         <button class="apply-button" onclick=Redirect()>Apply</button>
//     `;
//
//
//     let applyButton = researchCard.querySelector('.apply-button');
//     applyButton.addEventListener('click', () => {
//         event.preventDefault();
//         console.log('Button clicked');
//         let researchObj = {
//             researchId: researchItem.researchId,
//             subject: researchItem.subject,
//             description: researchItem.description,
//             salary: researchItem.salary,
//             startDate: researchItem.startDate,
//             manager: researchItem.manager.firstName + " "  + researchItem.manager.lastName
//         };
//         localStorage.setItem('research', JSON.stringify(researchObj));
//         location.href = `../../apply/apply.html`; //?id=${researchItem.researchId}&subject=${researchItem.subject}`;
//     });
//
//     let researchContainer = document.getElementById('research-container');
//     researchContainer.appendChild(researchCard);
// }

// Function to redirect to the desired URL
// function Redirect() {
//     event.preventDefault();
//     console.log("I clicked");
//     window.location.href = "http://localhost:8080/research/apply/apply.html";
//     // window.location.replace("http://localhost:8080/research/apply/apply.html");
//     //return false;
// }

// fetchResearchData();