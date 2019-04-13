const checkedBooks = document.getElementById('checked-books-table');
const pastTransactions = document.getElementById('transaction-table');

const username = getUrlVars()["username"];

if (username !== "" && username !== undefined) {
    const xhttp = new XMLHttpRequest();

    xhttp.addEventListener('load', () => {

        switch(xhttp.status) {
            case 200: // HTTP 200: OK
                printTransactions(JSON.parse(xhttp.responseText));
                break;
            case 400: // HTTP 400: Bad request
                alert(xhttp.responseText);
                break;
            case 401: // HTTP 401: Unauthorized
                alert(xhttp.responseText);
                break;
            default:
                alert('HTTP ' + xhttp.status + '+ ' + xhttp.statusText);
                break;
        }
    });

    // Define what happens in case of error
    xhttp.addEventListener('error', function () {
        alert("Error: " + xhttp.statusText + " (" + xhttp.status + ")");
    });

    // Set up our request
    xhttp.open('GET', 'http://localhost:8080/api/getTransactions?memberID=' + username);

    // Finally, send our data.
    xhttp.send();
}

// https://html-online.com/articles/get-url-parameters-javascript/
function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function printTransactions(transactions) {
    // Add new row for each entry
    transactions.forEach((transaction) => {
        const checkedOut = (transaction.timeIn === null);

        const row = (checkedOut)
            ? checkedBooks.insertRow()
            : pastTransactions.insertRow();
        row.insertCell(0).innerHTML = transaction.bookTitle;
        row.insertCell(1).innerHTML = transaction.bookAuthor;
        row.insertCell(2).innerHTML = transaction.timeOut;
        row.insertCell(3).innerHTML = transaction.branchAddressOut;
        row.insertCell(4).innerHTML = (checkedOut) ? "-" : transaction.timeIn;
        row.insertCell(5).innerHTML = (checkedOut) ? "-" : transaction.branchAddressIn;
    })
}