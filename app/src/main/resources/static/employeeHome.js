const branchAddr = document.getElementById('branch-address');
const checkInBtn = document.getElementById('check-in-btn');
const checkOutBtn = document.getElementById('check-out-btn');

const empID = getUrlVars()["employeeID"];
let branch;

if (empID !== "" && empID !== undefined) {
    const xhttp = new XMLHttpRequest();

    xhttp.addEventListener('load', () => {

        switch(xhttp.status) {
            case 200: // HTTP 200: OK
                printBranch(branch = JSON.parse(xhttp.responseText));
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
    xhttp.open('GET', 'http://localhost:8080/api/getBranchByID?employeeID=' + empID);

    // Finally, send our data.
    xhttp.send();
}

checkOutBtn.addEventListener('click', ()=> {

    const branchId = branch.id;
    const bookId = document.getElementById('bookID').value;
    const memberId = document.getElementById('memberId').value;

    submitCheckForm(branchId, bookId, memberId, "checkOutBook?");

}, false);

checkInBtn.addEventListener('click', ()=> {

    const branchId = branch.id;
    const bookId = document.getElementById('bookID').value;
    const memberId = document.getElementById('memberId').value;

    submitCheckForm(branchId, bookId, memberId, "checkInBook?");

}, false);

function submitCheckForm(branchId, bookId, memberId, apiCall) {

    const xhttp = new XMLHttpRequest();

    xhttp.addEventListener('load', () => {

        switch(xhttp.status) {
            case 200: // HTTP 200: OK
                alert(xhttp.responseText);
                break;
            case 400: // HTTP 400: Bad request
                alert(xhttp.responseText);
                break;
            case 401: // HTTP 401: Unauthorized -- invalid credentials
                alert(xhttp.responseText);
                break;
            default:
                alert('HTTP ' + xhttp.status + '+ ' + xhttp.statusText);
                break;
        }
    });

    // Define what happens in case of error
    xhttp.addEventListener('error', function () {
        errorMessageSpan.innerText = "Error: " + xhttp.statusText + " (" + xhttp.status + ")";
    });

    // Combine the pairs into a single string and replace all %-encoded spaces to
    // the '+' character; matches the behaviour of browser form submissions.

    let urlEncodedDataPairs = [];
    urlEncodedDataPairs.push('branchID='+encodeURIComponent(branchId));
    urlEncodedDataPairs.push('memberID='+encodeURIComponent(memberId));
    urlEncodedDataPairs.push('bookID='+encodeURIComponent(bookId));
    let urlEncodedData = urlEncodedDataPairs.join('&').replace(/%20/g, '+');

    // Set up our request
    // We don't use a url parameter for some security. Don't really want to open up
    // a way to hijack the url an attacker can POST to (not that it's likely anyways)
    xhttp.open('POST', 'http://localhost:8080/api/' + apiCall + urlEncodedData);

    // Add the required HTTP header for form data POST requests
    xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

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

function printBranch(branch) {
    console.log(branch.address);
    branchAddr.innerHTML = branch.address;
}