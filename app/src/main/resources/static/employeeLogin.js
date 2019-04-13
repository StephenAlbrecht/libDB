const loginForm = document.getElementById('emp-login-form');

loginForm.addEventListener('submit', (formEvent) => {

    formEvent.preventDefault();

    const username = document.getElementById('login-username').value;
    const password = document.getElementById('login-password').value;

    const credentials = {
        "username" : username,
        "password" : password
    }

    submitLoginForm(loginForm, credentials);

}, false);

function submitLoginForm(form, credentials) {

    const xhttp = new XMLHttpRequest();

    xhttp.addEventListener('load', () => {

        switch(xhttp.status) {
            case 200: // HTTP 200: OK
                window.location.href = '/employeeHome.html?username=' + credentials.username;
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
    urlEncodedDataPairs.push('username='+encodeURIComponent(credentials.username));
    urlEncodedDataPairs.push('password='+encodeURIComponent(credentials.password));
    let urlEncodedData = urlEncodedDataPairs.join('&').replace(/%20/g, '+');

    // Set up our request
    // We don't use a url parameter for some security. Don't really want to open up
    // a way to hijack the url an attacker can POST to (not that it's likely anyways)
    xhttp.open('POST', 'http://localhost:8080/api/employeeLogin?' + urlEncodedData);

    // Add the required HTTP header for form data POST requests
    xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    // Finally, send our data.
    xhttp.send();

}