const loginForm = document.getElementById('login-form');
const searchForm = document.getElementById('search-form');
const errorWindow = document.getElementById('errorWindow');
const errorMessageSpan = document.getElementById('errorWindowMessage');
const searchResults = document.getElementById('search-results');

loginForm.addEventListener('submit', (formEvent) => {

    formEvent.preventDefault();

    const username = document.getElementById('login-username').value;
    const password = document.getElementById('login-password').value;

    let urlEncodedDataPairs = [];
    urlEncodedDataPairs.push('username='+encodeURIComponent(username));
    urlEncodedDataPairs.push('password='+encodeURIComponent(password));

    submitLoginForm(loginForm, urlEncodedDataPairs);

}, false);

searchForm.addEventListener('submit', (formEvent) => {
    
    formEvent.preventDefault();

    let urlEncodedDataPairs = [];
    
    submitSearchForm(searchForm, urlEncodedDataPairs);
})

function submitLoginForm(form, urlEncodedDataPairs) {

    const xhttp = new XMLHttpRequest();

    xhttp.addEventListener('load', () => {

        switch(xhttp.status) {
            case 200: // HTTP 200: OK
                // Members and Employees go to different pages
                break;
            case 400: // HTTP 400: Bad request
                errorMessageSpan.innerText = xhttp.responseText;
                break;
            case 401: // HTTP 401: Unauthorized -- invalid credentials
                errorMessageSpan.innerText = xhttp.responseText;
                break;
            default:
                errorMessageSpan.innerText = 'HTTP ' + xhttp.status + '+ ' + xhttp.statusText;
                break;
        }
    });

    // Define what happens in case of error
    xhttp.addEventListener('error', function () {
        setInvisible(errorWindow, false);

        errorMessageSpan.innerText = "Error: " + xhttp.statusText + " (" + xhttp.status + ")";
    });

    // Combine the pairs into a single string and replace all %-encoded spaces to
    // the '+' character; matches the behaviour of browser form submissions.
    let urlEncodedData = urlEncodedDataPairs.join('&').replace(/%20/g, '+');

    // Set up our request
    // We don't use a url parameter for some security. Don't really want to open up
    // a way to hijack the url an attacker can POST to (not that it's likely anyways)
    xhttp.open('POST', '' + (form.id === 'login-form' ? 'login' : 'register'));

    // Add the required HTTP header for form data POST requests
    xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    // Finally, send our data.
    xhttp.send(urlEncodedData);

}

function submitSearchForm(form, urlEncodedDataPairs) {
    
    const xhttp = new XMLHttpRequest();

    xhttp.addEventListener('load', () => {

        switch(xhttp.status) {
            case 200: // HTTP 200: OK
                printBooks(JSON.parse(xhttp.responseText));
                break;
            case 400: // HTTP 400: Bad request
                errorMessageSpan.innerText = xhttp.responseText;
                break;
            case 401: // HTTP 401: Unauthorized -- invalid credentials
                errorMessageSpan.innerText = xhttp.responseText;
                break;
            default:
                errorMessageSpan.innerText = 'HTTP ' + xhttp.status + '+ ' + xhttp.statusText;
                break;
        }
    });

    // Define what happens in case of error
    xhttp.addEventListener('error', function () {
        setInvisible(errorWindow, false);

        errorMessageSpan.innerText = "Error: " + xhttp.statusText + " (" + xhttp.status + ")";
    });

    // Combine the pairs into a single string and replace all %-encoded spaces to
    // the '+' character; matches the behaviour of browser form submissions.
    let urlEncodedData = urlEncodedDataPairs.join('&').replace(/%20/g, '+');

    // Set up our request
    // We don't use a url parameter for some security. Don't really want to open up
    // a way to hijack the url an attacker can POST to (not that it's likely anyways)
    xhttp.open('GET', 'http://localhost:8080/api/getBooks');

    // Add the required HTTP header for form data POST requests
    xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    // Finally, send our data.
    xhttp.send();
}

function setInvisible(el, invis) {
    if (invis) {
        el.style.display = "none";
    } else {
        el.style.display = ""
    }
}

function isInvisible(el) {
    return el.style.display === "none";
}

function printBooks(bookList) {
    while(searchResults.hasChildNodes()) {
        searchResults.removeChild(searchResults.firstChild);
    }

    const header = searchResults.insertRow();
    header.insertCell(0).innerHTML = "Book ID".bold();
    header.insertCell(1).innerHTML = "ISBN".bold();
    header.insertCell(2).innerHTML = "Title".bold();
    header.insertCell(3).innerHTML = "Author".bold();
    header.insertCell(4).innerHTML = "Genre".bold();
    header.insertCell(5).innerHTML = "Pages".bold();

    bookList.forEach((book) => {
        const row = searchResults.insertRow();
        row.insertCell(0).innerHTML = book.id;
        row.insertCell(1).innerHTML = book.isbn;
        row.insertCell(2).innerHTML = book.title;
        row.insertCell(3).innerHTML = book.author;
        row.insertCell(4).innerHTML = book.genre;
        row.insertCell(5).innerHTML = book.pages;
    })
}