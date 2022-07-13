
function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else {
        console.log("Geolocation is not supported by this browser.");
    }
}

function showPosition(position) {
    console.log(position);
    var lat = position.coords.latitude;
    var long = position.coords.longitude;
    fetch('https://api.seatgeek.com/2/events?client_id=Mjc4MTcxNDZ8MTY1NzU3OTE4MC4yMjE1NzI')
    .then(response => response.json())
    .then(data => {
        console.log(data);
    }
    )
    .catch(error => console.log(error));
    
}
getLocation();
//get users lat and long and save save as a variables








