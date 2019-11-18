let currentUser;
getCurrentUser();



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