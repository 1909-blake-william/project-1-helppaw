function saveReimbursement(event) {
    event.preventDefault();
    let currentUser;
    fetch('localhost:8080/projectOne/auth/session-user', {
      
        
        credentials: 'include'
    })

    .then(resp => resp.json())
        .then(data => {
            currentUser = data;
        });


    const reimbInfo = {
        reimbTypeId,
        reimbAmount,
        reimbDescription,
        reimbStatusId,
        reimbAuthor

    };
    reimbInfo.reimbTypeId = document.getElementById('reimbTypeId').value;
    reimbInfo.reimbAmount = document.getElementById('reimbAmount').value;
    reimbInfo.reimbDescription = document.getElementById('reimbDescription').value;

    reimbInfo.reimbStatusId = 1;
    reimbInfo.reimbAuthor = currentUser.reimbAuthor;
    fetch('localhost:8080/projectOne/reimbursement', {
        method: 'POST',
        headers: {
            'content-type': 'application/json'

        },
        credentials: 'include',
        body: JSON.stringify(reimbInfo)

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