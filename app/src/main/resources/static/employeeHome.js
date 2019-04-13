const branchAddr = document.getElementById('branch-address');

const username = getUrlVars()["username"];
let branch;

if (username !== "" && username !== undefined) {
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
    xhttp.open('GET', 'http://localhost:8080/api/getBranchByID?employeeID=' + username);

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