
let path = window.location.protocol + '//' + window.location.hostname + ':' + window.location.port;

//const HOSTURL = `http://localhost:8080/TopDeck/api`;
//const HOSTURL = `http://35.189.85.82:8888/TopDeck/api`;
const HOSTURL = path + "/TopDeck/api";

function makeRequest(method, url, body) {

    return new Promise(

        (resolve, reject) => {

            const req = new XMLHttpRequest();

            req.onload = () => {

                if (req.status >= 200 && req.status <= 299 && req.responseText != "{\"message\": \"Password Incorrect\"}") {
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

        let newDeckHead = document.createElement('div');
        newDeckHead.innerText = user.decks[x].name + " Format: " +  user.decks[x].format;
        newDeckHead.id = user.decks[x].deckId + "Head";
        newDeckHead.style = "display: inline-block; background-color: Black;color: white; padding: 5px;width: 100%;border: none;text-align: left;outline: none;font-size: 15px; height: 35px;"
        document.getElementById("decks").appendChild(newDeckHead);

        let newDeckDelete = document.createElement('p');
        newDeckDelete.innerText = "Delete";
        newDeckDelete.id = user.decks[x].deckId + "delete";
        newDeckDelete.style = "display: inline-block; cursor: pointer;background-color: saddlebrown;color: antiquewhite;margin-left: 50%;margin-right: 10px;padding: 2px;width: 10%;border: none;text-align: center;outline: none;font-size: 15px;border-radius: 5px;";
        newDeckDelete.onclick = clickDeleteDeck;
        document.getElementById(user.decks[x].deckId + "Head").appendChild(newDeckDelete);
        
        let newDeckUpdate = document.createElement('p');
        newDeckUpdate.innerText = "Update";
        newDeckUpdate.id = user.decks[x].deckId + "update";
        newDeckUpdate.style = "display: inline-block; cursor: pointer; background-color: saddlebrown;color: antiquewhite;margin-left: 10px;padding: 2px;width: 10%;border: none;text-align: center;outline: none;font-size: 15px;border-radius: 5px";
        newDeckUpdate.onclick = clickUpdateDeck;
        document.getElementById(user.decks[x].deckId + "Head").appendChild(newDeckUpdate);

        let newDeckDiv = document.createElement('div');
        newDeckDiv.style = "border-style: solid; border-color: black; padding: 20px;margin-bottom:10px;";
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

    }
}

const clickDeleteDeck = (e) => {

    let deckId = e.target.getAttribute('id').substring(0, 1);

    makeRequest("DELETE", HOSTURL + `/Decks/deleteDeck/${deckId}`)
        .then((resolve) => { location.href = 'user_decks.html' })
        .catch(function (error) { console.log(error.message) })

    return false;
}

const clickUpdateDeck = (e) => {
    let deckId = e.target.getAttribute('id').substring(0, 1);
    
    sessionStorage.setItem("deckId", deckId);

    makeRequest("GET", HOSTURL + `/Decks/getDeck/${deckId}`)
        .then((resolve) => { sessionStorage.setItem("deck", resolve)  })
        .catch(function (error) { console.log(error.message) })

    location.href = 'update_deck.html';

}

const updateDeck = () => {

    let deck = sessionStorage.getItem("deck");

    let deckObj = JSON.parse(deck);

    let name = document.getElementById("deckName").value;

    deckObj.name = name;

    let format = document.getElementById("deckFormat").value;

    deckObj.format = format;

    let rawCards = document.getElementById("deckCards").value;

    let joinedCards = rawCards.replace(/\s\s+/g, '\n').split("\n").join(",");

    deckObj.cards = joinedCards;

    let updatedDeck = JSON.stringify(deckObj);

    makeRequest("PUT", HOSTURL + `/Decks/updateDeck/${sessionStorage.getItem("deckId")}`, updatedDeck)
        .then((resolve) => { location.href = 'user_decks.html' })
        .catch(function (error) { console.log(error.message) })

    return false;
}

function createNewDeck() {

    let newDeckObj = {};

    let name = document.getElementById("deckName").value;

    newDeckObj.name = name;

    let format = document.getElementById("deckFormat").value;

    newDeckObj.format = format;

    let rawCards = document.getElementById("deckCards").value;

    let joinedCards = rawCards.replace(/\s\s+/g, '\n').split("\n").join(",");

    newDeckObj.cards = joinedCards;

    let newDeckJSON = JSON.stringify(newDeckObj);

    makeRequest("POST", HOSTURL + `/Users/createDeck/${sessionStorage.getItem("userId")}`, newDeckJSON)
        .then((resolve) => { location.href = 'user_decks.html' })
        .catch(function (error) { console.log(error.message) })

}

function createNewUser() {

    let newUserObj = {};

    let name = document.getElementById("userName").value;

    newUserObj.name = name;

    let email = document.getElementById("userEmail").value;

    sessionStorage.setItem("loginEmail", email);

    newUserObj.email = email;

    let password = document.getElementById("userPassword").value;


    newUserObj.password = password;

    let newUserJSON = JSON.stringify(newUserObj);

    makeRequest("POST", HOSTURL + `/Users/createUser`, newUserJSON)
        .then((resolve) => {
            makeRequest("GET", HOSTURL + `/Users/getUserByEmail/${sessionStorage.getItem("loginEmail")}/${document.getElementById("userPassword").value}`)
                .then((resolve) => { signIn(resolve) })
        })
        .catch(function (error) {
            console.log(error.message);
            document.getElementById("userEmail").placeholder = "Email taken";
            document.getElementById("userEmail").value = "";
        })

    return false;
}

function updateUser() {

    let newUserObj = {};

    let name = document.getElementById("userName").value;
    
    sessionStorage.setItem("userName", name);

    newUserObj.name = name;

    let email = document.getElementById("userEmail").value;

    sessionStorage.setItem("loginEmail", email);

    newUserObj.email = email;

    let password = document.getElementById("userPassword").value;

    newUserObj.password = password;

    let newUserJSON = JSON.stringify(newUserObj);

    makeRequest("PUT", HOSTURL + `/Users/updateUser/${sessionStorage.getItem("userId")}`, newUserJSON)
        .then((resolve) => {
            makeRequest("GET", HOSTURL + `/Users/getUser/${sessionStorage.getItem("userId")}`)
                .then((resolve) => {
                    sessionStorage.setItem("user", resolve);
                    location.href = "home_signed_in.html";
                })
        })
        .catch(function (error) {
            console.log(error);
            document.getElementById("userEmail").placeholder = "Email taken";
            document.getElementById("userEmail").value = "";
        })

    return false;
}

function deleteUser() {
    makeRequest("DELETE", HOSTURL + `/Users/deleteUser/${sessionStorage.getItem("userId")}`)
        .then((resolve) => { location.href = 'home.html' })
        .catch(function (error) { console.log(error.message) })
}



function fillDeckFields() {

    makeRequest("GET", HOSTURL + `/Decks/getDeck/${deckId}`)
        .then((resolve) => { sessionStorage.setItem("deck", resolve)  })
        .catch(function (error) { console.log(error.message) })
    
    let deck = sessionStorage.getItem("deck");

    let deckObj = JSON.parse(deck);

    document.getElementById("deckName").value = deckObj.name;
    document.getElementById("deckFormat").value = deckObj.format;

    let formattedCards = deckObj.cards.split(",").join("\n");

    document.getElementById("deckCards").value = formattedCards;

}

function fillUserFields() {
    let user = sessionStorage.getItem("user");
    let userObj = JSON.parse(user);

    document.getElementById("userName").value = userObj.name;
    document.getElementById("userEmail").value = userObj.email;
    document.getElementById("userPassword").value = userObj.password;
}



function clickGetDeck() {
    makeRequest("GET", HOSTURL + `/Decks/getDeck/${sessionStorage.getItem('deckId')}`)
        .then((resolve) => { parseStoreDeck(resolve) })
        .catch(function (error) { console.log(error.message) })
    return false;
}


const clickSignIn = () => {

    makeRequest("GET", HOSTURL + `/Users/getUserByEmail/${document.getElementById("loginEmail").value}/${document.getElementById("loginPassword").value}`)
        .then((resolve) => { signIn(resolve) })
        .catch(function (error) {
            document.getElementById("loginEmail").placeholder = "User not found!";
            document.getElementById("loginEmail").value = "";
            document.getElementById("loginPassword").placeholder = "Password Incorrect";
            document.getElementById("loginPassword").value = "";
        })

    return false;
}

function signIn(input) {
    sessionStorage.setItem("user", input);
    let user = JSON.parse(input);
    sessionStorage.setItem("userId", user.userId);
    sessionStorage.setItem("userName", user.name);
    location.href = 'home_signed_in.html';

}

function signOut() {
    sessionStorage.removeItem("userId")
    location.href = 'home.html';

}

function welcomeUser(){
    document.getElementById("userName").innerText = sessionStorage.getItem("userName");
}


const clickGetAllDecks = () => {

    makeRequest("GET", HOSTURL + `/Decks/getAllDecks`)
        .then((resolve) => { getAllDecks(resolve) })
        .catch(function (error) {
            console.log(error.message);

        })

    return false;
}

function getAllDecks(input) {

    let allDecks = JSON.parse(input);

    for (let x = 0; x < allDecks.length; x++) {

        let newDeck = document.createElement('div');
        newDeck.innerText = allDecks[x].name + " Format: " + allDecks[x].format;
        newDeck.style = "display: inline-block; background-color: Black;color: white; padding: 5px;width: 100%;border: none;text-align: left;outline: none;font-size: 15px; height: 35px;"
        document.getElementById("decks").appendChild(newDeck);

        let newDeckDiv = document.createElement('div');
        newDeckDiv.style = "border-style: solid; border-color: black; padding: 25px;margin-bottom:10px;";
        newDeckDiv.id = allDecks[x].deckId + "Div";
        document.getElementById("decks").appendChild(newDeckDiv);

        let newDeckDetails = document.createElement('div');

        let cards = allDecks[x].cards;
        let formatedCards = cards.split(",").join("\n");
        
        newDeckDetails.innerText = formatedCards;
        newDeckDetails.class = "columns";
        newDeckDetails.style = "column-count: 3;";
        newDeckDetails.id = allDecks[x].deckId + "Details";
        document.getElementById(allDecks[x].deckId + "Div").appendChild(newDeckDetails);


    }
}

const clickGetAllEvents = () => {

    makeRequest("GET", HOSTURL + `/Events/getAllEvents`)
        .then((resolve) => { getAllEvents(resolve) })
        .catch(function (error) {
            console.log(error.message);

        })

    return false;
}

function getAllEvents(input) {

    let AllEvents = JSON.parse(input);

    for (let x = 0; x < AllEvents.length; x++) {

        let newEventHead = document.createElement('div');
        newEventHead.innerText = AllEvents[x].name;
        newEventHead.id = AllEvents[x].eventId + "Head";
        newEventHead.style = "display: inline-block; background-color: Black;color: white; padding: 5px;width: 100%;border: none;text-align: left;outline: none;font-size: 15px; height: 35px;"
        document.getElementById("events").appendChild(newEventHead);

        let newEventStar = document.createElement('p');
        newEventStar.innerText = "Star";
        newEventStar.id = AllEvents[x].eventId + "star";
        newEventStar.style = "display: inline-block; cursor: pointer;background-color: saddlebrown;color: antiquewhite;margin-left: 55%;margin-right: 10px;padding: 2px;width: 10%;border: none;text-align: center;outline: none;font-size: 15px;border-radius: 5px;";
        newEventStar.onclick = clickStarEvent;
        document.getElementById(AllEvents[x].eventId + "Head").appendChild(newEventStar);

        let newEventDiv = document.createElement('div');
        newEventDiv.class = "content";
        newEventDiv.style = "border-style: solid; border-color: black; padding: 5px;margin-bottom:15px;";
        newEventDiv.id = AllEvents[x].eventId + "Div";
        document.getElementById("events").appendChild(newEventDiv);

        let newEventLocation = document.createElement('div');
        newEventLocation.innerText = "Location: " + AllEvents[x].location;
        document.getElementById(AllEvents[x].eventId + "Div").appendChild(newEventLocation);

        let newEventFormat = document.createElement('div');
        newEventFormat.innerText = "Format: " + AllEvents[x].format;
        document.getElementById(AllEvents[x].eventId + "Div").appendChild(newEventFormat);

        let newEventDate = document.createElement('div');
        newEventDate.innerText = "Date: " + AllEvents[x].eventDate;
        document.getElementById(AllEvents[x].eventId + "Div").appendChild(newEventDate);



    }
}

const clickStarEvent = (e) => {

    let eventId = e.target.getAttribute('id').substring(0, 1);

    makeRequest("POST", HOSTURL + `/Users/addEvent/${sessionStorage.getItem("userId")}/`+ eventId)
        .then((resolve) => { location.href = 'all_events.html' })
        .catch(function (error) { console.log(error.message) })

    return false;
}


const clickGetUserEvents = () => {

    makeRequest("GET", HOSTURL + `/Users/getUser/${sessionStorage.getItem("userId")}`)
        .then((resolve) => { getUserEvents(resolve) })
        .catch(function (error) {
            console.log(error.message);

        })

    return false;
}
function getUserEvents(input) {

    let user = JSON.parse(input);

    for (let x = 0; x < user.events.length; x++) {

        let newEventHead = document.createElement('div');
        newEventHead.innerText = user.events[x].name;
        newEventHead.id = user.events[x].eventId + "Head";
        newEventHead.style = "display: inline-block; background-color: Black;color: white; padding: 5px;width: 100%;border: none;text-align: left;outline: none;font-size: 15px; height: 35px;"
        document.getElementById("events").appendChild(newEventHead);

        let newEventRemove = document.createElement('p');
        newEventRemove.innerText = "Remove";
        newEventRemove.id = user.events[x].eventId + "remove";
        newEventRemove.style = "display: inline-block; cursor: pointer;background-color: saddlebrown;color: antiquewhite;margin-left: 55%;margin-right: 10px;padding: 2px;width: 10%;border: none;text-align: center;outline: none;font-size: 15px;border-radius: 5px;";
        newEventRemove.onclick = clickRemoveEvent;
        document.getElementById(user.events[x].eventId + "Head").appendChild(newEventRemove);

        let newEventDiv = document.createElement('div');
        newEventDiv.class = "content";
        newEventDiv.style = "border-style: solid; border-color: black; padding: 5px;margin-bottom:15px;";
        newEventDiv.id = user.events[x].eventId + "Div";
        document.getElementById("events").appendChild(newEventDiv);

        let newEventLocation = document.createElement('div');
        newEventLocation.innerText = "Location: " + user.events[x].location;
        document.getElementById(user.events[x].eventId + "Div").appendChild(newEventLocation);

        let newEventFormat = document.createElement('div');
        newEventFormat.innerText = "Format: " + user.events[x].format;
        document.getElementById(user.events[x].eventId + "Div").appendChild(newEventFormat);

        let newEventDate = document.createElement('div');
        newEventDate.innerText = "Date: " + user.events[x].eventDate;
        document.getElementById(user.events[x].eventId + "Div").appendChild(newEventDate);

        document.getElementById(user.events[x].eventId + "Div").appendChild(document.createElement('br'));

    }
}

const clickRemoveEvent = (e) => {

    let eventId = e.target.getAttribute('id').substring(0, 1);

    makeRequest("DELETE", HOSTURL + `/Users/removeEvent/${sessionStorage.getItem("userId")}`+`/`+ eventId)
        .then((resolve) => { location.href = 'user_events.html' })
        .catch(function (error) { console.log(error.message) })

    return false;
}


