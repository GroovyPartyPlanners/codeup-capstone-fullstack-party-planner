
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

            var lat = position.coords.latitude;
            var lng = position.coords.longitude;
            mapboxgl.accessToken = 'pk.eyJ1Ijoia2VhdG9uaHV0dG8iLCJhIjoiY2wycWw3cWRnMDFwOTNqcGFwbDhqZTh6aCJ9.JA4KRbfaB02VWnaD8Ecs7g';
            const map = new mapboxgl.Map({
                container: 'map',
                style: 'mapbox://styles/mapbox/streets-v11',
                center: [lng, lat],
                zoom: 8
            });

            const marker = new mapboxgl.Marker({
                draggable: true
            })
                .setLngLat([lng, lat])
                .addTo(map);


            function onDragEnd() {
                const lngLat = marker.getLngLat();
                let lat = lngLat.lat;
                let long = lngLat.lng;
                let type = document.getElementById('type').value;
                let pages = document.getElementById('pages');

//dragging pin fetch
                fetch(`https://api.seatgeek.com/2/events?lat=${lat}&lon=${long}&type=${type}&client_id=Mjc4MTcxNDZ8MTY1NzU3OTE4MC4yMjE1NzI`)
                    .then(response => response.json())
                    .then(data => {

                        var events ='';
                        var popularity = '';
                        total = document.getElementById('total');
                        total.innerHTML = `<h2>${data.meta.total}</h2>`
                        for(var i = 0; i < data.events.length; i++) {
                            const date = new Date(data.events[i].datetime_local);
                            const [year, month, day] = [date.getFullYear(), date.getMonth() + 1, date.getDate()];
                            const dateString = 'm/d/year'+" "+`${month}/${day}/${year}`;
                            events += `<div class='loopEvent profile-div-outer col'>${data.events[i].title}<br/>${data.events[i].venue.name}<br/>${data.events[i].venue.display_location}<br/>`+ dateString +`<br/><div class="row"><a href="/events/${data.events[i].id}" class="col profile-btn">THROW PARTY</a>`+
                                `<a href="/parties/${data.events[i].id}" class="col profile-btn">VIEW PARTIES</a></div></div></br>`
                        }

                        for(var i = 0; i < 1; i++) {
                            if(data.meta.total <11){
                                pages = document.getElementById('pages');
                                pages.innerHTML = "";
                            }
                            if(data.meta.total>=11){
                                pages = document.getElementById('pages');
                                pages.innerHTML = "";
                                let pagesCount = Math.ceil(data.meta.total/10);
                                pageNum = document.getElementById('pageNum');
                                for(let i = 0; i < pagesCount; i++){
                                    pageNum.innerHTML = 'Page:';
                                    pages.innerHTML += `<a href="#"  onclick="page(${i})">`+" "+`${i+1}</a>`;
                                }

                                if(data.events <11){
                                    pages = document.getElementById('pages');
                                    pages.innerHTML = "no pages";
                                }
                            }
                            document.getElementById("eventTitle").innerHTML = events;
                        }}
                    )
                    .catch(error => console.log(error));
            };

            marker.on('dragend', onDragEnd);

//window onload
            fetch(`https://api.seatgeek.com/2/events?lat=${lat}&lon=${lng}&type=comedy&client_id=Mjc4MTcxNDZ8MTY1NzU3OTE4MC4yMjE1NzI`)
                .then(response => response.json())
                .then(data => {
                        console.log(data);
                        console.log('on load')
                        var events ='';

                        total = document.getElementById('total');
                        total.innerHTML = `<label class='totalEvents'>Total Events Near You ${data.meta.total}</label>`;
                        for(var i = 0; i < data.events.length; i++) {
                            const date = new Date(data.events[i].datetime_local);
                            const [year, month, day] = [date.getFullYear(), date.getMonth() + 1, date.getDate()];
                            const dateString = `${month}/${day}/${year}`;
                            events += `<div class='loopEvent profile-div-outer col'>${data.events[i].title}<br/>${data.events[i].venue.name}<br/>${data.events[i].venue.display_location}<br/>`+ dateString +`<br/><div class="row"><a href="/events/${data.events[i].id}" class="col profile-btn">THROW PARTY</a>`+
                                `<a href="/parties/${data.events[i].id}" class="col profile-btn">VIEW PARTIES</a></div></div></br>`
                        }

                        for(var i = 0; i < 1; i++) {
                            if(data.meta.total <11){
                                console.log('no pages')
                                pages = document.getElementById('pages');
                                pages.innerHTML = "";
                            }
                            if(data.meta.total>=11){
                                pages = document.getElementById('pages');
                                let pagesCount = Math.ceil(data.meta.total/10);
                                pages.innerHTML = "";
                                for(let i = 0; i < pagesCount; i++){
                                    pages.innerHTML += `<a href="#" onclick="pin(${i})">`+" "+`${i+1}</a>`
                                }
                            }
                        }
                        document.getElementById("eventTitle").innerHTML = events
                    }
                )
                .catch(error => console.log(error));
        }

        getLocation();
//
//submit button
        document.getElementById('search-btn').addEventListener('click', function (e){
            e.preventDefault();

            var type = document.getElementById("type").value
            var search = document.getElementById("search").value

            fetch(`https://api.seatgeek.com/2/events?q=${search}&type=${type}&client_id=Mjc4MTcxNDZ8MTY1NzU3OTE4MC4yMjE1NzI`)
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    var events ='';

                    total = document.getElementById('total');
                    total.innerHTML = `<label class='totalEvents'>Total Events Near You ${data.meta.total}</label>`;
                    for(var i = 0; i < data.events.length; i++) {
                        const date = new Date(data.events[i].datetime_local);
                        const [year, month, day] = [date.getFullYear(), date.getMonth() + 1, date.getDate()];
                        const dateString = 'm/d/year'+" "+`${month}/${day}/${year}`;
                        events += `<div class='loopEvent profile-div-outer col'>${data.events[i].title}<br/>${data.events[i].venue.name}<br/>${data.events[i].venue.display_location}<br/>`+ dateString +`<br/><div class="row"><a href="/events/${data.events[i].id}" class="col profile-btn">THROW PARTY</a>`+
                            `<a href="/parties/${data.events[i].id}" class="col profile-btn">VIEW PARTIES</a></div></div></br>`
                    }
                    for(var i = 0; i < 1; i++) {
                        if(data.meta.total <11){
                            pages = document.getElementById('pages');
                            pages.innerHTML = "";
                        }
                        if(data.meta.total>=11){
                            pages = document.getElementById('pages');
                            let pagesCount = Math.ceil(data.meta.total/10);
                            pages.innerHTML = "";

                            for(let i = 0; i < pagesCount; i++){
                                pages.innerHTML += `<a href="#"  onclick="page(${i})">`+" "+`${i+1}</a>`

                            }

                        }
                        document.getElementById("eventTitle").innerHTML = events
                    }})
                .catch(error => console.log(error));
        });

        function pin(num){
//     var type = document.getElementById("type").value
//     mapboxgl.accessToken = 'pk.eyJ1Ijoia2VhdG9uaHV0dG8iLCJhIjoiY2wycWw3cWRnMDFwOTNqcGFwbDhqZTh6aCJ9.JA4KRbfaB02VWnaD8Ecs7g';

// const map = new mapboxgl.Map({
// container: 'map',
// style: 'mapbox://styles/mapbox/streets-v11',
// center: [0, 0],
// zoom: 2
// });

// const marker = new mapboxgl.Marker({
// draggable: true
// })
// .setLngLat([0, 0])
// .addTo(map);

// function onDragEnd() {
// const lngLat = marker.getLngLat();
// console.log(lngLat);

            fetch(`https://api.seatgeek.com/2/events?lat=${lat}&lon=${long}&type=${type}&client_id=Mjc4MTcxNDZ8MTY1NzU3OTE4MC4yMjE1NzI`)
                .then(response => response.json())
                .then(data => {

                        console.log(data);
                        console.log('page clicked!')
                        var events ='';
                        console.log(data.events);
                        total = document.getElementById('total');
                        total.innerHTML = `<label class='totalEvents'>Total Events Near You ${data.meta.total}</label>`;
                        for(var i = 0; i < data.events.length; i++) {
                            const date = new Date(data.events[i].datetime_local);
                            const [year, month, day] = [date.getFullYear(), date.getMonth() + 1, date.getDate()];
                            const dateString = 'm/d/year'+" "+`${month}/${day}/${year}`;
                            events += `<div class='loopEvent profile-div-outer col'>${data.events[i].title}<br/>${data.events[i].venue.name}<br/>${data.events[i].venue.display_location}<br/>`+ dateString +`<br/><div class="row"><a href="/events/${data.events[i].id}" class="col profile-btn">THROW PARTY</a>`+
                                `<a href="/parties/${data.events[i].id}" class="col profile-btn">VIEW PARTIES</a></div></div></br>`
                        }
                        // for(var i = 0; i < 1; i++) {
                        //     if(data.meta.total>=11){
                        //         pages = document.getElementById('pages');
                        //         let pagesCount = Math.ceil(data.meta.total/10);

                        //         pages.innerHTML = "";
                        //         for(let i = 0; i < pagesCount; i++){
                        //             pages.innerHTML += `<a href="" onclick="page(${i})">`+" "+`${i+1}</a>`
                        //         }
                        //     }
                        document.getElementById("eventTitle").innerHTML = events
                        let pageNum = document.getElementById('pageNum');
                        pageNum.innerHTML = "Page" +" " + (num+1)
                        document.getElementById("eventTitle").innerHTML = events
                    }
                )
                .catch(error => console.log(error));
        }

        function page(num){
            var type = document.getElementById("type").value
            var search = document.getElementById("search").value
            fetch(`https://api.seatgeek.com/2/events?q=${search}&type=${type}&page=${num+1}&client_id=Mjc4MTcxNDZ8MTY1NzU3OTE4MC4yMjE1NzI`)
                .then(response => response.json())
                .then(data => {

                        console.log(data);
                        console.log('page clicked!')
                        var events ='';
                        console.log(data.events);
                        total = document.getElementById('total');
                        total.innerHTML = `<label class='totalEvents'>Total Events Near You ${data.meta.total}</label>`;
                        for(var i = 0; i < data.events.length; i++) {
                            const date = new Date(data.events[i].datetime_local);
                            const [year, month, day] = [date.getFullYear(), date.getMonth() + 1, date.getDate()];
                            const dateString = 'm/d/year'+" "+`${month}/${day}/${year}`;
                            events += `<div class='loopEvent profile-div-outer col'>${data.events[i].title}<br/>${data.events[i].venue.name}<br/>${data.events[i].venue.display_location}<br/>`+ dateString +`<br/><div class="row"><a href="/events/${data.events[i].id}" class="col profile-btn">THROW PARTY</a>`+
                                `<a href="/parties/${data.events[i].id}" class="col profile-btn">VIEW PARTIES</a></div></div></br>`
                        }
                        // for(var i = 0; i < 1; i++) {
                        //     if(data.meta.total>=11){
                        //         pages = document.getElementById('pages');
                        //         let pagesCount = Math.ceil(data.meta.total/10);

                        //         pages.innerHTML = "";
                        //         for(let i = 0; i < pagesCount; i++){
                        //             pages.innerHTML += `<a href="" onclick="page(${i})">`+" "+`${i+1}</a>`
                        //         }
                        //     }
                        let pageNum = document.getElementById('pageNum');
                        pageNum.innerHTML = "Page" +" " + (num+1);
                        document.getElementById("eventTitle").innerHTML = events;
                    }
                ).catch(error => console.log(error));
        }
        console.log('Loaded!');
    }, 5000);
}
windowLoading();
