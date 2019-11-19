let currentUser;
let reimbursementsArray;
let reimbursement;
let typeCheck;




function addReimbursementToTableSafe(reimbursement) {

    // create the row element
    const row = document.createElement('tr');

    // create all the td elements and append them to the row
    //might not need author
    const authorData = document.createElement('td');
    authorData.innerText = reimbursement.reimbAuthor;
    row.appendChild(authorData);

    const idData = document.createElement('td');
    idData.innerText = reimbursement.reimbId;
    row.appendChild(idData);

    const typeData = document.createElement('td');
    typeData.innerText = reimbursement.reimbTypeId;
    row.appendChild(typeData);

    const amountData = document.createElement('td');
    amountData.innerText = reimbursement.reimbAmount;
    row.appendChild(amountData);

    const whenSubmittedData = document.createElement('td');
    whenSubmittedData.innerText = reimbursement.reimbSubmitted;
    row.appendChild(whenSubmittedData);

    const whenResolvedData = document.createElement('td');
    whenResolvedData.innerText = reimbursement.reimbResolved;
    row.appendChild(whenResolvedData);

    const descriptionData = document.createElement('td');
    descriptionData.innerText = reimbursement.reimbDescription;
    row.appendChild(descriptionData);

    const statusData = document.createElement('td');
    statusData.innerText = reimbursement.reimbStatusId;
    row.appendChild(statusData);

    const resolverData = document.createElement('td');
    resolverData.innerText = reimbursement.reimbResolver;
    row.appendChild(resolverData);



    let manageButton = document.createElement('td');
    if (currentUser.userRoleId === 1 && reimbursement.reimbStatusId === 1) {
        manageButton.innerHTML = `<button onclick="update(${reimbursement.reimbId}, 2)">Approve</button> <button onclick="update(${reimbursement.reimbId}, 3)">Deny</button>`
    }
    row.appendChild(manageButton);


    // append the row into the table ; make to reimbursement-table-body if this doesnt work
    document.getElementById('past-reimbursement-body').appendChild(row);
}


function update(reimbId, statusInput) {


    let details = {
        reimbResolver: -1,
        reimbStatusId: -1,
        reimbId: -1
    };

    details.reimbResolver = currentUser.ersUserId;
    details.reimbStatusId = statusInput;
    details.reimbId = reimbId;
    fetch('http://localhost:8080/projectOne/reimbursement', {
        method: 'PUT',
        headers: {
            'content-type': 'application/json'
        },
        details: 'include',
        body: JSON.stringify(details)


    })
        .then(resp => {
            if (resp.status === 201) {
                document.getElementById('past-reimbursement-body').innerText = "";
                reimbursementsArray.forEach(reimbursement => {
                    if (reimbursement.reimbId === reimbId) {
                        reimbursement.reimbStatusId = statusInput;
                        reimbursement.reimbResolver = currentUser.ersUserId;
                        addReimbursementToTableSafe(reimbursement);
                        document.getElementById('past-reimbursement-body').innerText = "";
                        refreshTable();
                    } else {
                        addReimbursementToTableSafe(reimbursement);
                    }

                });


            } else {
                document.getElementById('error-message').innterText = "Failed to update";
            }
        });
}







function refreshTable() {
    let fetchUrl = 'http://localhost:8080/projectOne/reimbursement';
    if (currentUser.userRoleId !== 1) {
        fetchUrl += `?userId=${currentUser.ersUserId}`
    }
    fetch(fetchUrl)
        .then(res => res.json())
        .then(data => {
            reimbursementsArray = data;
            data.forEach(addReimbursementToTableSafe)
        })
        .catch(console.log);
}


function filterSelector() {

    if (typeCheck !== document.getElementById("statusFilter").value) {

        typeCheck = document.getElementById("statusFilter").value;

        if (typeCheck === 'All') {
            filterTable(0);

        } else if (typeCheck === 'Pending') {

            filterTable(1);

        } else if (typeCheck === 'Approved') {

            filterTable(2);

        } else if (typeCheck === 'Denied') {

            filterTable(3);

        }

    }

}
function filterTable(i) {


    let details = {

        reimbStatusId: -1,

    };


    details.reimbStatusId = i;


    fetch('http://localhost:8080/projectOne/reimbursement', {
        method: 'GET',

        details: 'include',



    })
        .then(resp => JSON.stringify(details))
        .then(data => {
            reimbursementsArray = data;
            console.log(reimbursementsArray);
            if (1 === 1) {

                document.getElementById('past-reimbursement-body').innerText = "";
                reimbursementsArray.forEach(reimbursement => {
                    if (i === 0) {

                        addReimbursementToTableSafe(reimbersement);
                    } else if (reimbursement.reimbStatusId === i) {
                        addReimbursementToTableSafe(reimbersement.reimbStatusId);
                    }
                });

            } else {
                document.getElementById('error-message').innterText = "Failed to update";
            }
        });
}







/*
function filterTable(i) {
 
    document.getElementById('past-reimbursement-body').innerText = "";
    reimbursementsArray.forEach(reimbersement =>{
    if (i === 0) {
         console.log('made it to 0');   
        addReimbursementToTableSafe(reimbersement);
        
    } else 
        if (reimbursement.reimbStatusId === i) {
                document.getElementById('past-reimbursement-body').innerText = "";
                addReimbursementToTableSafe(reimbersement.reimbStatusId);
           console.log('this is the problem right here');
            }
        });
    }
*/

function logout() {
    fetch('http://localhost:8080/projectOne/auth/logout', {
        method: 'DELETE',

        headers: {
            'content-type': 'application/json'
        },

        credentials: 'include',
    })
        .then(resp => {
            console.log('logged out properly');
        });
}






function getCurrentUser() {
    fetch('http://localhost:8080/projectOne/auth/session-user', {

        credentials: 'include'
    })

        .then(resp => resp.json())
        .then(data => {
            currentUser = data;
            console.log(currentUser);
            refreshTable();
        })

}
getCurrentUser();



