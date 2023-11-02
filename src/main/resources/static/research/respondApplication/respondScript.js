
document.addEventListener('DOMContentLoaded', function () {
    //location.reload();
    const form = document.getElementById('collaboration-form');

    form.addEventListener('submit', function (e) {
        e.preventDefault();
        let applicantContainer = document.getElementById('Applicant-container');
        applicantContainer.innerHTML=``;
        let acceptContainer = document.getElementById('Accepted-container');
        acceptContainer.innerHTML=``;
        let cancelContainer = document.getElementById('Rejected-container');
        cancelContainer.innerHTML=``;
        const formData = new FormData(form);;

        fetch(`http://localhost:8080/collaboration/getByResearchId?researchId= + ${formData.get("researchId")}`)
            .then((respond) => respond.json())
            .then((data) => {
                data.forEach((collItem) => {
                    createResearchCard(collItem);
                })
            })
            .catch((error) => {
                console.error('Error fetching research data:', error);
            });
    });
});

function createResearchCard(collItem) {

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
            <div class="field-label">Occupation:</div>
            <div class="field-value">${collItem.user.occupation}</div>
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
                        // pendingCard.innerHTML = '';
                        // cancelCard.innerHTML = '';
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
                                console.error('Error fetching research data:', error);
                            });
                        // location.href = `http://localhost:8080/research/respondApplication/respond.html`;
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
                        // pendingCard.innerHTML = '';
                        // cancelCard.innerHTML = '';
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
                                console.error('Error fetching research data:', error);
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
            <div class="field-label">Occupation:</div>
            <div class="field-value">${collItem.user.occupation}</div>
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
                        // pendingCard.innerHTML = '';
                        // cancelCard.innerHTML = '';
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
                                console.error('Error fetching research data:', error);
                            });
                        // location.href = `http://localhost:8080/research/respondApplication/respond.html`;
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

// // Create a div element for the research card
// let collaborationCard = document.createElement('div');
// collaborationCard.classList.add('collaboration-card');
//
// // Populate the research card with data
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
//                     fetch(`http://localhost:8080/collaboration/getByResearchId?researchId= + ${collItem.research.researchId}`)
//                         .then((respond) => respond.json())
//                         .then((data) => {
//                             data.forEach((collItem) => {
//                                 createResearchCard(collItem);
//                             })
//                         })
//                         .catch((error) => {
//                             console.error('Error fetching research data:', error);
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
//                     fetch(`http://localhost:8080/collaboration/getByResearchId?researchId= + ${collItem.research.researchId}`)
//                         .then((respond) => respond.json())
//                         .then((data) => {
//                             data.forEach((collItem) => {
//                                 createResearchCard(collItem);
//                             })
//                         })
//                         .catch((error) => {
//                             console.error('Error fetching research data:', error);
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
//                     fetch(`http://localhost:8080/collaboration/getByResearchId?researchId= + ${collItem.research.researchId}`)
//                         .then((respond) => respond.json())
//                         .then((data) => {
//                             data.forEach((collItem) => {
//                                 createResearchCard(collItem);
//                             })
//                         })
//                         .catch((error) => {
//                             console.error('Error fetching research data:', error);
//                         });
//                 }else
//                     alert("Something is wrong")
//             })
//         }
//     });
// }

