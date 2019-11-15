function saveReimbursement() {

    const reimbInfo = {
        reimbTypeId,
        reimbAmount,
        reimbDescription,
        reimbStatusId: -1,
        reimbAuthor: 1

    };
    reimbInfo.reimbTypeId = document.getElementById('reimbTypeId').value;
    reimbInfo.reimbAmount = document.getElementById('reimbAmount').value;
    reimbInfo.reimbDescription = document.getElementById('reimbDescription').value;

    reimbInfo.reimbStatusId = 1;
    reimbInfo.reimbAuthor = currentUser.reimbAuthor;
    console.log(reimbInfo.reimbAuthor);




    fetch('http://localhost:8080/projectOne/reimbursement', {

        credentials: 'include',
        body: JSON.stringify(reimbInfo),


        method: 'POST',
        headers: {
            'content-type': 'application/json'

        },

    })
        .then(resp => {
            if (resp.status === 201) {

                console.log('successfully inserted pending reimbursement')
                window.location = './home.html';
            } else {
                document.getElementById('error-message').innerText = 'Incorrect input values';


            }


        })


}
function getCurrentUser() {
    fetch('http://localhost:8080/projectOne/auth/session-user', {

        credentials: 'include'
    })

        .then(resp => resp.json())
        .then(data => {
            currentUser = data;
            console.log(currentUser);
        })

}
getCurrentUser();