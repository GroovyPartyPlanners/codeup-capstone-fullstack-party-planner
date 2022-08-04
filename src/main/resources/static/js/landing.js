"use strict";
function windowLoading(){
    console.log('Loading...');
    document.getElementById('loading').style.display = 'block';
    setTimeout(function(){
        document.getElementById('loading').style.display = 'none';

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
                console.log(data)
                var events = document.getElementById("eventTitle");
                total.innerHTML = 'You have'+' '+data.meta.total+' events in your area!(based in a 50 mile radius)';

                for(var i = 0; i < data.events.length; i++) {
                    const date = new Date(data.events[i].datetime_local);
                    const [year, month, day] = [date.getFullYear(), date.getMonth() + 1, date.getDate()];
                    const dateString = `${month}/${day}/${year}`;
                    events.innerHTML += `<p class='loopEvent profile-div-outer'>${data.events[i].title}<br/>${data.events[i].venue.name}<br/>${data.events[i].venue.display_location}<br/>` + dateString + `</p><br/>`
                }
        //     events.innerHtml += `<div class='loopEvent profile-div-outer col'>${data.events[i].title}<br/>${data.events[i].venue.name}<br/>${data.events[i].venue.display_location}<br/>`+ dateString +`<br/><div class="row"><a href="/events/${data.events[i].id}" class="col profile-btn">THROW PARTY</a>`+
        //         `<a href="/parties/${data.events[i].id}" class="col profile-btn">VIEW PARTIES</a></div></div></br>`
        // }

    // profile-div-outer
    /*for(var i = 0; i < 1; i++) {
        if(data.meta.total>=11){
            pages = document.getElementById('pages');
            let pagesCount = Math.ceil(data.meta.total/10);
            console.log(pagesCount);
            for(let i = 0; i < pagesCount; i++){
                pages.innerHTML += `<a href="#" onclick="page(${i})">`+" "+`${i+1}</a>`
            }
        }
    }*/
            }

        )
        .catch(error => console.log(error));

}
getLocation();
console.log('Loaded!');
}, 5000);
}
windowLoading();
// fetch('https://api.unsplash.com/photos/random/?&topics=BJJMtteDJA4&client_id=eJIyvaW_YdQEcelyLpGcNQwYQgRo0LtO7EHZ8PstJp0')
// .then(response => response.json())
// .then(data => {
//     console.log(data);
//     document.getElementById('image').src = data.urls.regular;
// }
// )

// .catch(error => console.log(error));
// fetch('https://api.unsplash.com/topics/?page=3&client_id=eJIyvaW_YdQEcelyLpGcNQwYQgRo0LtO7EHZ8PstJp0')
// .then(response => response.json())
// .then(data => {
//     console.log(data);
// }
// )
// .catch(error => console.log(error));
