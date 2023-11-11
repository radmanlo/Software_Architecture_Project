const apiUrl = 'http://localhost:8080/research/getAll';

function fetchResearchData() {
    fetch(apiUrl)
        .then((response) => response.json())
        .then((data) => {
            data.forEach((researchItem) => {
                createResearchCard(researchItem);
            });
        })
        .catch((error) => {
            console.error('Error fetching manager data:', error);
        });
}
function createResearchCard(researchItem) {
    let researchCard = document.createElement('div');
    researchCard.classList.add('research-card');
    researchCard.innerHTML = `
        <div class="field-label">Research ID:</div>
        <div class="field-value">${researchItem.researchId}</div>
        <div class="field-label">Subject:</div>
        <div class="field-value">${researchItem.subject}</div>
        <div class="field-label">Description:</div>
        <div class="field-value">${researchItem.description}</div>
        <div class="field-label">Salary:</div>
        <div class="field-value">${researchItem.salary}</div>
        <div class="field-label">Start Date:</div>
        <div class="field-value">${researchItem.startDate}</div>
        <div class="field-label">Manager:</div>
        <div class="field-value">${researchItem.manager.firstName} ${researchItem.manager.lastName}</div>
        <button class="apply-button">Apply</button>
    `;
    let applyButton = researchCard.querySelector('.apply-button');
    applyButton.addEventListener('click', () => {
        event.preventDefault();
        let researchObj = {
            researchId: researchItem.researchId,
            subject: researchItem.subject,
            description: researchItem.description,
            salary: researchItem.salary,
            startDate: researchItem.startDate,
            manager: researchItem.manager.firstName + " "  + researchItem.manager.lastName
        };
        localStorage.setItem('research', JSON.stringify(researchObj));
        location.href = `../../apply/apply.html`; //?id=${researchItem.researchId}&subject=${researchItem.subject}`;
    });

    let researchContainer = document.getElementById('research-container');
    researchContainer.appendChild(researchCard);
}

// Function to redirect to the desired URL
// function Redirect() {
//     event.preventDefault();
//     console.log("I clicked");
//     window.location.href = "http://localhost:8080/research/apply/apply.html";
//     // window.location.replace("http://localhost:8080/research/apply/apply.html");
//     //return false;
// }

fetchResearchData();