let serializedData = localStorage.getItem('research');
let researchObj = JSON.parse(serializedData);

let researchCard = document.createElement('div');
researchCard.classList.add('manager-card');

let researchContainer = document.getElementById('research-container');

// Create an array of contributor names
let contributorNames = [];

function fetchResearchData() {
    fetch(`http://localhost:8080/collaboration/getByResearchId?researchId=` + researchObj.researchId)
        .then((response) => {
            if (response.status === 302) {
                return response.json();
            } else if (response.status === 404) {
                // Handle the case where there are no contributors
                console.log('No contributors found for this manager');
                return null;
            } else {
                // Handle other error cases
                console.error('Error fetching contributor data:', response.status);
                return null;
            }
        })
        .then((contributors) => {
            if (contributors !== null) {
                // Process and display contributors
                console.log(contributors);
                contributors.forEach((contributor) => {
                    // Process contributors
                    console.log(contributor)
                    if (contributor.status === "ACCEPTED")
                        contributorNames.push(contributor.user.firstName + " " + contributor.user.lastName);
                });
            }
            // Update the researchCard with contributor names
            researchCard.innerHTML = `
                <div class="field-label">Research ID:</div>
                <div class="field-value">${researchObj.researchId}</div>
                <div class="field-label">Subject:</div>
                <div class="field-value">${researchObj.subject}</div>
                <div class="field-label">Description:</div>
                <div class="field-value">${researchObj.description}</div>
                <div class="field-label">Salary:</div>
                <div class="field-value">${researchObj.salary}</div>
                <div class="field-label">Start Date:</div>
                <div class="field-value">${researchObj.startDate}</div>
                <div class="field-label">Manager:</div>
                <div class="field-value">${researchObj.manager}</div>
                <div class="field-label">Contributors:</div>
                <div class="field-value">
                     ${contributorNames.map(name => `<li>${name}</li>`).join('')}
                </div>
            `;
        })
        .catch((error) => {
            console.error('Error fetching contributor data:', error);
        });
}

fetchResearchData();
researchContainer.appendChild(researchCard);

// const applyButton = document.getElementById("apply");
//
// // Add an event listener to the button
// applyButton.addEventListener("click", function() {
//     // Your code to handle the button click goes here
//     // For example, you can perform some action when the button is clicked.
//     console.log("Button clicked!");
// });


document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('apply-form');
    form.addEventListener("submit", function (e) {
        e.preventDefault();

        const formData = new FormData(form);

        const jsonData = {
            research: {
                researchId: researchObj.researchId,
            },
            user: {
                email: formData.get("email"),
            },
            coverLetter: formData.get("cover-letter"), // Use "coverLetter" to match your JSON object keys
        };

        fetch('http://localhost:8080/collaboration/create', {
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
                alert("Thank you for your application");
            })
            .catch(error => {
                alert("Invalid email");
                console.error('Error:', error);
            });
    });
});


