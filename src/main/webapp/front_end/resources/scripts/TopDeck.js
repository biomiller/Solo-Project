function makeRequest(method, url, body) {

    return new Promise(

        (resolve, reject) => {

            const req = new XMLHttpRequest();

            req.onload = () => {

                if (req.status >= 200 && req.status <= 299) {
                    resolve(req.responseText);
                }
                else {
                    const reason = new Error("Rejected");
                    reject(reason);
                }
            }
            req.open(method, url);
            req.send(body);
        });

}


const clickGetUser = () => {
    makeRequest("GET", `http://localhost:8080/TopDeck/api/Users/getUser/${document.getElementById("getUserId").value}`)
        .then((resolve) => { getUser(resolve) })
        .catch(function (error) { 
            console.log(error.message) 
            document.getElementById("getUserId").value = "ID not found!";
        })

    return false;
}

function getUser(input) {

    user = JSON.parse(input);

    while (document.getElementById("usersTable").rows.length > 1) {
        document.getElementById("usersTable").deleteRow(1);
    }

    let newRow = document.createElement('TR');

    newRow.id = "row" + user.userId;

    document.getElementById("usersTable").appendChild(newRow);

    let td1 = document.createElement('TD');
    document.getElementById("row" + user.userId).appendChild(td1);
    td1.innerText = user.name;

    let td2 = document.createElement('TD');
    document.getElementById("row" + user.userId).appendChild(td2);
    td2.innerText = user.email;

    let td3 = document.createElement('TD');
    document.getElementById("row" + user.userId).appendChild(td3);
    deckNames = "";

    for (let x = 0; x < user.decks.length; x++) {
        let newLink = document.createElement('a');
        newLink.style = "color:Red;text-decoration:underline;cursor:pointer";
        newLink.id = user.decks[x].deckId;
        newLink.innerText = user.decks[x].name + " ";
        newLink.onclick = deckDetails;
        td3.appendChild(newLink);
    }

}


const clickGetallUsers = () => {
    makeRequest("GET", `http://localhost:8080/TopDeck/api/Users/getAllUsers`)
        .then((resolve) => { getAllUsers(resolve) })
        .catch(function (error) { console.log(error.message) })

    return false;
}

function getAllUsers(input) {

    allUsers = JSON.parse(input);

    while (document.getElementById("usersTable").rows.length > 1) {
        document.getElementById("usersTable").deleteRow(1);
    }

    for (let i = 0; i < allUsers.length; i++) {

        // if (document.contains(document.getElementById("row" + allUsers[i].userId))) {
        //     document.getElementById("usersTable").removeChild(document.getElementById("row" + allUsers[i].userId));
        //     }

        let newRow = document.createElement('TR');

        newRow.id = "row" + allUsers[i].userId;

        document.getElementById("usersTable").appendChild(newRow);

        let td1 = document.createElement('TD');
        document.getElementById("row" + allUsers[i].userId).appendChild(td1);
        td1.innerText = allUsers[i].name;

        let td2 = document.createElement('TD');
        document.getElementById("row" + allUsers[i].userId).appendChild(td2);
        td2.innerText = allUsers[i].email;

        let td3 = document.createElement('TD');
        document.getElementById("row" + allUsers[i].userId).appendChild(td3);

        for (let x = 0; x < allUsers[i].decks.length; x++) {
            let newLink = document.createElement('a');
            newLink.style = "color:Red;text-decoration:underline;cursor:pointer";
            newLink.id = allUsers[i].decks[x].deckId;
            newLink.innerText = allUsers[i].decks[x].name + " ";
            newLink.onclick = deckDetails;
            td3.appendChild(newLink);
        }

    }

}

const deckDetails = (e) => {
    sessionStorage.setItem("deckId", e.target.getAttribute('id'));
    location.href = 'deck_details.html';
};

function clickGetDeck(){
    makeRequest("GET", `http://localhost:8080/TopDeck/api/Decks/getDeck/${sessionStorage.getItem('deckId')}`)
    .then((resolve) => {getDeck(resolve)})
    .catch(function (error) { console.log(error.message) })
return false;
}

function getDeck(input){

    deck = JSON.parse(input);

    document.getElementById("deckList").innerText = deck.cards;

}

