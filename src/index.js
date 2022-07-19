

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
    
    fetch(`https://api.seatgeek.com/2/events?lat=${lat}&lon=${long}&client_id=${clientId}`)
    .then(response => response.json())
    .then(data => {
        console.log(data);
        var events ='';
        var eventsDate = '';
        var eventImage = '';
        for(var i = 0; i < data.events.length; i++) {
            events += `<li>${data.events[i].title}${data.events[i].datetime_local}${data.events[i].venue.name}</li>`
            eventImage = data.events[i].performers[0].images.huge;
        }
        document.getElementById("eventTitle", "eventDate").innerHTML = events + eventsDate;
        document.getElementById("eventImage").src = eventImage;
        
    }
    )
    .catch(error => console.log(error));
    
}
getLocation();


//${data.events[i].performers[0].images.huge}
