// fetch('http://localhost:8080/research/showAll')
//     .then(response => response.json())
//     .then(data => {
//         const tbody = document.querySelector('tbody');
//         data.forEach(research => {
//             const row = document.createElement('tr');
//             row.innerHTML = `
//                         <td>${research.researchId}</td>
//                         <td>${research.subject}</td>
//                         <td>${research.description}</td>
//                         <td>${research.salary}</td>
//                         <td>${research.startDate}</td>
//                         <td>${research.manager.firstName} ${research.manager.lastName}</td>
//                     `;
//             tbody.appendChild(row);
//         });
//     })
//     .catch(error => {
//         console.error('Error:', error);
//     });
// Assuming you have an API endpoint that returns a list of research data
const apiUrl = 'http://localhost:8080/research/showAll';

// Function to fetch research data from the API
function fetchResearchData() {
    fetch(apiUrl)
        .then((response) => response.json())
        .then((data) => {
            // Call a function to create and append card-like structures for each research item
            data.forEach((researchItem) => {
                createResearchCard(researchItem);
            });
        })
        .catch((error) => {
            console.error('Error fetching research data:', error);
        });
}

// Function to create and append a card-like structure for a research item
function createResearchCard(researchItem) {
    // Create a div element for the research card
    const researchCard = document.createElement('div');
    researchCard.classList.add('research-card');

    // Populate the research card with data
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

    // Append the research card to the research container
    const researchContainer = document.getElementById('research-container');
    researchContainer.appendChild(researchCard);
}

// Call the fetchResearchData function to load and display research data
fetchResearchData();