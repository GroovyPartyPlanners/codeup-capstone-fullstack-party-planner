function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else {
        console.log("Geolocation is not supported by this browser.");
    }
}

function mainEvent() {
    this.id;
    this.name;
    this.location;
}



function showPosition(position) {
    console.log(position);
    var lat = position.coords.latitude;
    var long = position.coords.longitude;

    fetch(`https://api.seatgeek.com/2/events?lat=${lat}&lon=${long}&client_id=${clientId}`)
        .then(response => response.json())
        .then(data => {
                console.log(data);
                // var events ='';
                var eventsDate = '';
                var eventsLocation = '';
                var eventsCity = '';
                var eventImage = '';
                let thisEvent = new mainEvent();
                let events = [];

            for(var i = 0; i < data.events.length; i++) {
                    mainEvent.id = data.events[i].id;
                    mainEvent.name = data.events[i].venue.name;
                    mainEvent.location = data.events[i].venue.display_location;

                }
                    events += `<h1>${data.events[i].title}`+" "+`${data.events[i].venue.name}`+" "+`${data.events[i].venue.display_location}</h1>`
                // }
                // document.getElementById("eventTitle").innerHTML = events
            }
        )
        .catch(error => console.log(error));
    return events;
}
getLocation();
//
// document.getElementById('search-btn').addEventListener('click', function (e){
//     e.preventDefault();
//
//     var type = document.getElementById("type").value
//     var search = document.getElementById("search").value
//     console.log(search)
//     fetch(`https://api.seatgeek.com/2/events?q=${search}&type=${type}&client_id=${clientId}`)
//         .then(response => response.json())
//         .then(data => {
//             console.log(data);
//             var events ='';
//             var eventsDate = '';
//             var eventsCity = '';
//             var eventImage = '';
//             for(var i = 0; i < data.events.length; i++) {
//                 events += `<h1>${data.events[i].title}`+" "+`${data.events[i].venue.name}`+" "+`${data.events[i].venue.display_location}</h1>`
//             }
//             document.getElementById("eventTitle").innerHTML = events
//         })
//         .catch(error => console.log(error));
// });