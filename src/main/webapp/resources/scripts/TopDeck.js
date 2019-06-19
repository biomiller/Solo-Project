
const HOSTURL = `http://localhost:8080/TopDeck/api`;

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

const clickGetUserDecks = () => {

    makeRequest("GET", HOSTURL + `/Users/getUser/${sessionStorage.getItem("userId")}`)
        .then((resolve) => { getUserDecks(resolve) })
        .catch(function (error) {
            console.log(error.message);

        })

    return false;
}

function getUserDecks(input) {

    let user = JSON.parse(input);

    for (let x = 0; x < user.decks.length; x++) {

        let newDeck = document.createElement('p');
        newDeck.innerText = user.decks[x].name;
        newDeck.style = "background-color: Black;color: white;padding: 18px;width: 100%;border: none;text-align: left;outline: none;font-size: 15px;active;collapsible:hover {background-color: #555;}"
        document.getElementById("decks").appendChild(newDeck);

        let newDeckDiv = document.createElement('div');
        newDeckDiv.class = "content";
        newDeckDiv.id = user.decks[x].deckId + "Div";
        document.getElementById("decks").appendChild(newDeckDiv);

        let newDeckDetails = document.createElement('div');
        
        let cards = user.decks[x].cards;
        let formatedCards = cards.split(",").join("\n");
        newDeckDetails.innerText = formatedCards;

        newDeckDetails.class = "columns";
        newDeckDetails.style = "column-count: 4;";
        newDeckDetails.id = user.decks[x].deckId + "Details";
        document.getElementById(user.decks[x].deckId + "Div").appendChild(newDeckDetails);

        document.getElementById(user.decks[x].deckId + "Div").appendChild(document.createElement('br'));
        document.getElementById(user.decks[x].deckId + "Div").appendChild(document.createElement('br'));


        let newDeckDelete = document.createElement('p');
        newDeckDelete.innerText = "Delete";
        newDeckDelete.id = user.decks[x].deckId + "delete";
        newDeckDelete.style = "cursor: pointer;background-color: #777;color: white;padding: 5px;width: 25%;border: none;text-align: left;outline: none;font-size: 15px;";
        newDeckDelete.onclick = clickDeleteDeck;
        document.getElementById(user.decks[x].deckId + "Details").appendChild(newDeckDelete);


        let newDeckUpdate = document.createElement('p');
        newDeckUpdate.innerText = "Update";
        newDeckUpdate.id = user.decks[x].deckId + "update";
        newDeckUpdate.style = "cursor: pointer; background-color: #777;color: white;padding: 5px;width: 25%;border: none;text-align: left;outline: none;font-size: 15px;";
        newDeckUpdate.onclick = updateDeck;
        document.getElementById(user.decks[x].deckId + "Details").appendChild(newDeckUpdate);


    }
}

const clickDeleteDeck = (e) => {

    let deckId = e.target.getAttribute('id').substring(0, 1);

    makeRequest("DELETE", HOSTURL + `/Decks/deleteDeck/${deckId}`)
        .then((resolve) => { location.href = 'user_decks.html' })
        .catch(function (error) { console.log(error.message) })

    return false;
}

const updateDeck = (e) => {
    let deckId = e.target.getAttribute('id').substring(0, 1);

    sessionStorage.setItem("deckId", deckId);

    makeRequest("GET", HOSTURL + `/Decks/getDeck/${sessionStorage.getItem("deckId")}`)
        .then((resolve) => { sessionStorage.setItem('deck', resolve) })
        .catch(function (error) { console.log(error.message) })

    location.href = 'update_deck.html';

}

const saveDeckChanges = () =>{

    let deck = sessionStorage.getItem("deck");

    let deckObj = JSON.parse(deck);

    let name = document.getElementById("deckName").value;

    deckObj.name = name;

    let format = document.getElementById("deckFormat").value;

    deckObj.format = format;

    let rawCards = document.getElementById("deckCards").value;

    let joinedCards = rawCards.split("\n").join(",");

    deckObj.cards = joinedCards;

    let updatedDeck = JSON.stringify(deckObj);

    makeRequest("PUT", HOSTURL + `/Decks/updateDeck/${sessionStorage.getItem("deckId")}`, updatedDeck)
        .then((resolve) => { location.href = 'user_decks.html' })
        .catch(function (error) { console.log(error.message) })

}

const createNewDeck = () =>{

    let newDeckObj = {};

    let name = document.getElementById("deckName").value;

    newDeckObj.name = name;

    let format = document.getElementById("deckFormat").value;

    newDeckObj.format = format;

    let rawCards = document.getElementById("deckCards").value;

    let joinedCards = rawCards.split("\n").join(",");

    newDeckObj.cards = joinedCards;

    let newDeckJSON = JSON.stringify(newDeckObj);

    makeRequest("POST", HOSTURL + `/Users/createDeck/${sessionStorage.getItem("userId")}`, newDeckJSON)
        .then((resolve) => { location.href = 'user_decks.html' })
        .catch(function (error) { console.log(error.message) })

}

function fillDeckFields() {
    let deck = sessionStorage.getItem("deck");

    let deckObj = JSON.parse(deck);

    document.getElementById("deckName").value = deckObj.name;
    document.getElementById("deckFormat").value = deckObj.format;

    let formattedCards = deckObj.cards.split(",").join("\n");

    document.getElementById("deckCards").value = formattedCards;

}


const clickGetallUsers = () => {
    makeRequest("GET", HOSTURL + `/Users/getAllUsers`)
        .then((resolve) => { getAllUsers(resolve) })
        .catch(function (error) { console.log(error.message) })

    return false;
}

function getAllUsers(input) {

    let allUsers = JSON.parse(input);

    while (document.getElementById("usersTable").rows.length > 1) {
        document.getElementById("usersTable").deleteRow(1);
    }

    for (let i = 0; i < allUsers.length; i++) {

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


function clickGetDeck() {
    makeRequest("GET", HOSTURL + `/Decks/getDeck/${sessionStorage.getItem('deckId')}`)
        .then((resolve) => { parseStoreDeck(resolve) })
        .catch(function (error) { console.log(error.message) })
    return false;
}


const clickSignIn = () => {
    makeRequest("GET", HOSTURL + `/Users/getUser/${document.getElementById("loginId").value}`)
        .then((resolve) => { signIn(resolve) })
        .catch(function (error) {
            console.log(error.message)
            document.getElementById("loginId").value = "ID not found!";
        })

    return false;
}

function signIn(input) {

    let user = JSON.parse(input);
    sessionStorage.setItem("userId", user.userId)
    location.href = 'home_signed_in.html';

}

function signOut() {
    sessionStorage.removeItem("userId")
    location.href = 'home.html';

}

