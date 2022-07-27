function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else {
        console.log("Geolocation is not supported by this browser.");
    }
}

function mainEvent() {
    this.id;
    this.eventId;
    this.name;
    this.location;
}

function showPosition(position) {
    console.log(position);
    var lat = position.coords.latitude;
    var lon = position.coords.longitude;
    let events = getEvents(lat, lon);
}

function getEvents(lat, lon) {

    fetch(`https://api.seatgeek.com/2/events?lat=${lat}&lon=${lon}&client_id=${clientId}`)
        .then(response => response.json())
        .then(data => {
                console.log(data);
                // var events ='';
                var eventsDate = '';
                var eventsLocation = '';
                var eventsCity = '';
                var eventImage = '';
                let thisEvent = new mainEvent();
                let theseEvents = [];

                for (var i = 0; i < data.events.length; i++) {
                    thisEvent.eventId = data.events[i].id;
                    thisEvent.name = data.events[i].venue.name;
                    thisEvent.location = data.events[i].venue.display_location;
                    thisEvent.id = i;
                    theseEvents.push(thisEvent);
                }

                //         let html = `<form th:action="@{/sign-up}" method="POST" th:object="${user}">
                //     <div className="form-group">
                //         <label htmlFor="first-name">First Name:</label>
                //         <input id="first-name" className="form-control text-center" th:field="*{first_name}"/>
                //         <p th:if="${#fields.hasErrors('first_name')}" th:errors="*{first_name}"
                //            th:class="${#fields.hasErrors('first_name')}? errors"></p>
                //     </div>
                //
                //     <div className="form-group">
                //         <i className="fas fa-user prefix"></i>
                //         <label htmlFor="last-name">Last Name:</label>
                //         <input id="last-name" className="form-control text-center" th:field="*{last_name}"/>
                //         <p th:if="${#fields.hasErrors('last_name')}" th:errors="*{last_name}"
                //            th:class="${#fields.hasErrors('last_name')}? errors"></p>
                //     </div>
                //
                //     <div className="form-group">
                //         <i className="fa-solid fa-circle-user"></i>
                //         <label htmlFor="username-signup">Username:</label>
                //         <input id="username-signup" className="form-control text-center" th:field="*{username}"/>
                //         <p th:if="${#fields.hasErrors('username')}" th:errors="*{username}"
                //            th:class="${#fields.hasErrors('username')}? errors"></p>
                //     </div>
                //
                //     <div className="form-group">
                //         <i className="fas fa-envelope prefix"></i>
                //         <label htmlFor="email">Email Address:</label>
                //         <input id="email" className="form-control text-center" th:field="*{email}"/>
                //         <p th:if="${#fields.hasErrors('email')}" th:errors="*{email}"
                //            th:class="${#fields.hasErrors('email')}? errors"></p>
                //
                //     </div>
                //
                //     <div className="form-group">
                //         <i className="fas fa-tag prefix"></i>
                //         <label htmlFor="group-name">Group Name (optional):</label>
                //         <input id="group-name" className="form-control text-center" th:field="*{group_name}"/>
                //     </div>
                //
                //     <div className="form-group">
                //         <i className="fas fa-lock prefix"></i>
                //         <label htmlFor="password-signup">Password:</label>
                //         <input id="password-signup" type="password" className="form-control text-center"
                //                th:field="*{password}"/>
                //         <p th:if="${#fields.hasErrors('password')}" th:errors="*{password}"
                //            th:class="${#fields.hasErrors('password')}? errors"></p>
                //     </div>
                //
                //     <div className="form-group">
                //         <label htmlFor="profilePicUrl" className="form-label">Upload Profile Picture:</label>
                //         <input id="profilePicUrl" name="profilePicUrl" value="replaceMe" th:field="*{profilePicUrl}"
                //                type="hidden">
                //             <button id="buttonUpload" type="button" className="btn btn-danger">Upload Picture</button>
                //     </div>
                //
                //     <!--                <div class="form-group">-->
                //     <!--                    <label style="margin-left: 47px;" for="profilePicUrl" class="form-label">Upload Profile Picture (optional):</label>-->
                //     <!--                    <input id="profilePicUrl" name="profilePicUrl" value="replaceMe" type="file" th:field="*{profilePicUrl}" />-->
                //     <!--                </div>-->
                //
                //     <div className="form-group">
                //         <input value="Sign me up!" type="submit" className="btn btn-warning"/>
                //     </div>
                // </form>
                // `
                // events += `<h1>${data.events[i].title}`+" "+`${data.events[i].venue.name}`+" "+`${data.events[i].venue.display_location}</h1>`
                // }
                // document.getElementById("eventTitle").innerHTML = events
            return theseEvents;
            }
        )
        .catch(error => console.log(error));

}
let events = getLocation();

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