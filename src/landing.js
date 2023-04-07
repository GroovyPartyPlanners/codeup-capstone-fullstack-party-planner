"use strict";

function getLocation() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(showPosition);
  } else {
    console.log("Geolocation is not supported by this browser.");
  }
}

function showPosition(position) {
  console.log(position);
  let lat = position.coords.latitude;
  let long = position.coords.longitude;

  fetch(`https://api.seatgeek.com/2/events?lat=${lat}&lon=${long}&client_id=${clientId}`)
    .then(response => response.json())
    .then(data => {
        console.log(data)
        let events = document.getElementById("eventTitle");
        total.innerHTML = 'You have' + ' ' + data.meta.total + ' events in your area!(based in a 50 mile radius)';

        for (let i = 0; i < data.events.length; i++) {
          const date = new Date(data.events[i].datetime_local);
          const [year, month, day] = [date.getFullYear(), date.getMonth() + 1, date.getDate()];
          const dateString = 'm/d/year' + " " + `${month}/${day}/${year}`;
          events.innerHTML += `<h1 class='loopEvent'>${data.events[i].title}` + " " + `${data.events[i].venue.name}` + " " + `${data.events[i].venue.display_location}` + " Date: " + dateString + " Popularity " + `${data.events[i].popularity}</h1>`
        }
        for (let i = 0; i < 1; i++) {
          if (data.meta.total >= 11) {
            let pages = document.getElementById('pages');
            let pagesCount = Math.ceil(data.meta.total / 10);
            console.log(pagesCount);
            for (let i = 0; i < pagesCount; i++) {
              pages.innerHTML += `<a href="#" onclick="page(${i})">` + " " + `${i + 1}</a>`
            }
          }
        }
      }
    )
    .catch(error => console.log(error));
}

getLocation();

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
