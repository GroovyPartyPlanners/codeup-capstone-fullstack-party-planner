

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
        for(var i = 0; i < data.events.length; i++) {
            events += `<li>${data.events[i].title}${data.events[i].datetime_local}${data.events[i].venue.name}${data.events[i].performers[0].images.huge}</li>`
        }
        document.getElementById("eventTitle", "eventDate").innerHTML = events + eventsDate;
        document.getElementById("eventImg").src = events;
        
    }
    )
    .catch(error => console.log(error));
    
}
getLocation();

// function getEvents() {
//     fetch(`https://api.seatgeek.com/2/events?lat=${lat}&lon=${long}&client_id=${clientId}`)
//     .then(response => response.json())
//     .then(data => {
//         console.log(data);
//         var events ='';
//         for(var i = 0; i < data.events.length; i++) {
//             events += `<li>${data.events[i].title}</li>`;
            
//         }
//         document.getElementById("eventTitle").innerHTML = events
//     }
//     )
//     .catch(error => console.log(error));
// }
// getEvents();


