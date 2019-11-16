let currentUser;




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
    if (currentUser.userRoleId === 1) {
    manageButton.innerHTML = `<button onclick="update(${reimbursement.reimbId})">Approve</button> <button onclick="update(${reimbursement.reimbId})">Deny</button>`
    }
    row.appendChild(manageButton);
    
    // append the row into the table ; make to reimbursement-table-body if this doesnt work
    document.getElementById('past-reimbursement-body').appendChild(row);
}


function update(n){
    alert(n);
}







function refreshTable() {
    let fetchUrl = 'http://localhost:8080/projectOne/reimbursement';
    if (currentUser.userRoleId !== 1) { 
        fetchUrl += `?userId=${currentUser.ersUserId}`
    }
    fetch(fetchUrl)
        .then(res => res.json())
        .then(data => {
            data.forEach(addReimbursementToTableSafe)
        })
        .catch(console.log);
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
