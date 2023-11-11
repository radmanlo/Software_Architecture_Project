jsonData = localStorage.getItem("research");
let research = JSON.parse(jsonData);
fetchCollaborationApi()

function fetchCollaborationApi(){
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
    `;
    let researchContainer = document.getElementById('research-container');
    researchContainer.appendChild(researchCard);
    fetch(`http://localhost:8080/collaboration/getByResearchId?researchId=${research.researchId}`)
        .then((respond) => {
            if (respond.status === 302){
                respond.json().then(collaborations =>{
                    collaborations.forEach(collaboration =>{
                        createResearchCard(collaboration)
                    })
                })
            }
            else{
                alert("Internal Server Error!")
            }
        })
        .catch((error) => {
            console.error('Error fetching manager data:', error);
        });
}

async function fetchOccupationApi(collItem){
    try {
        const response = await fetch(`http://localhost:8080/occupation/getByUserEmail?userEmail=${collItem.user.email}`);

        if (response.status === 302) {
            const occ = await response.json();
            return {
                employer: occ.employer,
                experience: occ.experience
            };
        } else if (response.status === 404) {
            return {
                employer: "Not Specified",
                experience: "Not Specified"
            };
        }
    } catch (error) {
        console.error('Error fetching occupation data:', error);
        return null;
    }
}

async function createResearchCard(collItem) {
    let occupation = await fetchOccupationApi(collItem)
    let pendingCard = document.createElement('div');
    pendingCard.classList.add('pending-card');
    let cancelCard = document.createElement('div');
    cancelCard.classList.add('pending-card');

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
            <div class="field-label">Employer:</div>
            <div class="field-value">${occupation.employer}</div>
            <div class="field-label">Experience:</div>
            <div class="field-value">${occupation.experience}</div>
            <button class="accept-button">Accept</button>
            <button class="reject-button">Reject</button>
        `;
        pendingCard.querySelector('.accept-button').addEventListener('click', () => {
            event.preventDefault();
            const confirmation = window.confirm("Are you sure you want to accept?");
            if (confirmation) {
                collItem.status = "ACCEPTED";
                fetch('http://localhost:8080/collaboration/update', {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(collItem),
                }).then((response) => {
                    console.log(response.json())
                    if (response.status === 202) {
                        alert("Application is Accepted")
                        let applicantContainer = document.getElementById('Applicant-container');
                        applicantContainer.innerHTML=``;
                        let acceptContainer = document.getElementById('Accepted-container');
                        acceptContainer.innerHTML=``;
                        let cancelContainer = document.getElementById('Rejected-container');
                        cancelContainer.innerHTML=``;
                        fetch(`http://localhost:8080/collaboration/getByResearchId?researchId=${collItem.research.researchId}`)
                            .then((respond) => respond.json())
                            .then((data) => {
                                data.forEach((collItem) => {
                                    createResearchCard(collItem);
                                })
                            })
                            .catch((error) => {
                                console.error('Error fetching manager data:', error);
                            });
                    } else
                        alert("Something is wrong")
                })
            }
        });
        pendingCard.querySelector('.reject-button').addEventListener('click', () => {
            event.preventDefault();
            const confirmation = window.confirm("Are you sure you want to accept?");
            if (confirmation) {
                collItem.status = "REJECTED";
                fetch('http://localhost:8080/collaboration/update', {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(collItem),
                }).then((response) => {
                    console.log(response.json())
                    if (response.status === 202) {
                        alert("Application is Rejected")
                        let applicantContainer = document.getElementById('Applicant-container');
                        applicantContainer.innerHTML=``;
                        let acceptContainer = document.getElementById('Accepted-container');
                        acceptContainer.innerHTML=``;
                        let cancelContainer = document.getElementById('Rejected-container');
                        cancelContainer.innerHTML=``;
                        fetch(`http://localhost:8080/collaboration/getByResearchId?researchId= + ${collItem.research.researchId}`)
                            .then((respond) => respond.json())
                            .then((data) => {
                                data.forEach((collItem) => {
                                    createResearchCard(collItem);
                                })
                            })
                            .catch((error) => {
                                console.error('Error fetching manager data:', error);
                            });
                    } else
                        alert("Something is wrong")
                })
            }
        });

    } else {
        cancelCard.innerHTML = `
            <div class="field-label">Collaboration ID:</div>
            <div class="field-value">${collItem.collaborationId}</div>
            <div class="field-label">Cover Letter:</div>
            <div class="field-value">${collItem.coverLetter}</div>
            <div class="field-label">Applicant Email:</div>
            <div class="field-value">${collItem.user.email}</div>
            <div class="field-label">Applicant Name:</div>
            <div class="field-value">${collItem.user.firstName} ${collItem.user.lastName}</div>
            <div class="field-label">Employer:</div>
            <div class="field-value">${occupation.employer}</div>
            <div class="field-label">Experience:</div>
            <div class="field-value">${occupation.experience}</div>
            <button class="cancel-button">Cancel</button>
        `;
        cancelCard.querySelector('.cancel-button').addEventListener('click', () => {
            event.preventDefault();
            const confirmation = window.confirm("Are you sure you want to cancel?");
            if (confirmation) {
                collItem.status = "PENDING";
                fetch('http://localhost:8080/collaboration/update', {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(collItem),
                }).then((response) => {
                    console.log(response.json())
                    if (response.status === 202) {
                        alert("Application is Accepted")
                        let applicantContainer = document.getElementById('Applicant-container');
                        applicantContainer.innerHTML=``;
                        let acceptContainer = document.getElementById('Accepted-container');
                        acceptContainer.innerHTML=``;
                        let cancelContainer = document.getElementById('Rejected-container');
                        cancelContainer.innerHTML=``;
                        fetch(`http://localhost:8080/collaboration/getByResearchId?researchId= + ${collItem.research.researchId}`)
                            .then((respond) => respond.json())
                            .then((data) => {
                                data.forEach((collItem) => {
                                    createResearchCard(collItem);
                                })
                            })
                            .catch((error) => {
                                console.error('Error fetching manager data:', error);
                            });
                    } else
                        alert("Something is wrong")
                })
            }
        });
    }

    if(collItem.status === "ACCEPTED"){
        let collaborationContainer = document.getElementById('Accepted-container');
        collaborationContainer.appendChild(cancelCard);
    }
    else if (collItem.status === "PENDING"){
        let collaborationContainer = document.getElementById('Applicant-container');
        collaborationContainer.appendChild(pendingCard);
    }
    else{
        let collaborationContainer = document.getElementById('Rejected-container');
        collaborationContainer.appendChild(cancelCard);
    }

}

// // Create a div element for the manager card
// let collaborationCard = document.createElement('div');
// collaborationCard.classList.add('collaboration-card');
//
// // Populate the manager card with data
// collaborationCard.innerHTML = `
//         <div class="field-label">Collaboration ID:</div>
//         <div class="field-value">${collItem.collaborationId}</div>
//         <div class="field-label">Cover Letter:</div>
//         <div class="field-value">${collItem.coverLetter}</div>
//         <div class="field-label">Applicant Email:</div>
//         <div class="field-value">${collItem.user.email}</div>
//         <div class="field-label">Applicant Name:</div>
//         <div class="field-value">${collItem.user.firstName} ${collItem.user.lastName}</div>
//         <div class="field-label">Occupation:</div>
//         <div class="field-value">${collItem.user.occupation}</div>
//         ${collItem.status === 'PENDING' ? `
//             <button class="accept-button">Accept</button>
//             <button class="reject-button">Reject</button>
//         ` : `
//             <button class="cancel-button">Cancel</button>
//         `}
//     `;
//
// if (collItem.status === "PENDING") {
//     collaborationCard.querySelector('.accept-button').addEventListener('click', () => {
//         event.preventDefault();
//         const confirmation = window.confirm("Are you sure you want to accept?");
//         if (confirmation) {
//             collItem.status = "ACCEPTED";
//             fetch('http://localhost:8080/collaboration/update', {
//                 method: 'PUT',
//                 headers: {
//                     'Content-Type': 'application/json',
//                 },
//                 body: JSON.stringify(collItem),
//             }).then((response) => {
//                 console.log(response.json())
//                 if (response.status === 202) {
//                     alert("Application is Accepted")
//                     collaborationCard.innerHTML = '';
//                     fetch(`http://localhost:8080/collaboration/getByResearchId?researchId= + ${collItem.manager.researchId}`)
//                         .then((respond) => respond.json())
//                         .then((data) => {
//                             data.forEach((collItem) => {
//                                 createResearchCard(collItem);
//                             })
//                         })
//                         .catch((error) => {
//                             console.error('Error fetching manager data:', error);
//                         });
//                     // location.href = `http://localhost:8080/research/respondApplication/respond.html`;
//                 } else
//                     alert("Something is wrong")
//             })
//         }
//     });
//     collaborationCard.querySelector('.reject-button').addEventListener('click', () => {
//         event.preventDefault();
//         const confirmation = window.confirm("Are you sure you want to accept?");
//         if (confirmation) {
//             collItem.status = "REJECTED";
//             fetch('http://localhost:8080/collaboration/update', {
//                 method: 'PUT',
//                 headers: {
//                     'Content-Type': 'application/json',
//                 },
//                 body: JSON.stringify(collItem),
//             }).then((response) => {
//                 console.log(response.json())
//                 if (response.status === 202) {
//                     alert("Application is Rejected")
//                     collaborationCard.innerHTML = '';
//                     fetch(`http://localhost:8080/collaboration/getByResearchId?researchId= + ${collItem.manager.researchId}`)
//                         .then((respond) => respond.json())
//                         .then((data) => {
//                             data.forEach((collItem) => {
//                                 createResearchCard(collItem);
//                             })
//                         })
//                         .catch((error) => {
//                             console.error('Error fetching manager data:', error);
//                         });
//                 } else
//                     alert("Something is wrong")
//             })
//         }
//     });
// }
// else {
//     collaborationCard.querySelector('.cancel-button').addEventListener('click', () => {
//         event.preventDefault();
//         const confirmation = window.confirm("Are you sure you want to accept?");
//         if (confirmation) {
//             collItem.status = "PENDING";
//             fetch('http://localhost:8080/collaboration/update', {
//                 method: 'PUT',
//                 headers: {
//                     'Content-Type': 'application/json',
//                 },
//                 body: JSON.stringify(collItem),
//             }).then((response)=>{
//                 console.log(response.json())
//                 if (response.status === 202){
//                     alert("Application is Canceled")
//                     collaborationCard.innerHTML = '';
//                     fetch(`http://localhost:8080/collaboration/getByResearchId?researchId= + ${collItem.manager.researchId}`)
//                         .then((respond) => respond.json())
//                         .then((data) => {
//                             data.forEach((collItem) => {
//                                 createResearchCard(collItem);
//                             })
//                         })
//                         .catch((error) => {
//                             console.error('Error fetching manager data:', error);
//                         });
//                 }else
//                     alert("Something is wrong")
//             })
//         }
//     });
// }

