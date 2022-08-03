
function windowLoading(){
    console.log('Loading...');
    document.getElementById('loading').style.display = 'block';
    setTimeout(function(){
        document.getElementById('loading').style.display = 'none';
        var lat, lng;

        function getLocation() {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(showPosition);
            } else {
                console.log("Geolocation is not supported by this browser.");
            }
        }

        function showPosition(position) {

            lat = position.coords.latitude;
            lng = position.coords.longitude;
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
                lat = lngLat.lat;
                lng = lngLat.lng;
                let type = document.getElementById('type').value;
                let pages = document.getElementById('pages');
                console.log(lat, lng);
                console.log(type)
//dragging pin fetch

        fetch(`https://api.seatgeek.com/2/events?lat=${lat}&lon=${long}&type=${type}&client_id=Mjc4MTcxNDZ8MTY1NzU3OTE4MC4yMjE1NzI`)
            .then(response => response.json())
            .then(data => {

                var events ='';
                var popularity = '';
                total = document.getElementById('total');
                total.innerHTML = `<h2>Total Events ${data.meta.total}</h2>`
                for(var i = 0; i < data.events.length; i++) {
                    const date = new Date(data.events[i].datetime_local);
                    const [year, month, day] = [date.getFullYear(), date.getMonth() + 1, date.getDate()];
                    const dateString = 'm/d/year'+" "+`${month}/${day}/${year}`;
                    events += `<div class='loopEvent profile-div-outer row'>${data.events[i].title}\n`+" "+`${data.events[i].venue.name}\n`+" "+`${data.events[i].venue.display_location}\n`+" Date: "+ dateString + `${data.events[i].popularity}\n`+`<div class="row"><a href="/events/${data.events[i].id}" class="col profile-btn">Host a party at ${data.events[i].title}</a>`+
                        `<a href="/parties/${data.events[i].id}" class="col profile-btn">Or see parties at ${data.events[i].title}</a></div></div></br></br>`
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
                            pageNum.innerHTML = 'Page:'
                            pages.innerHTML += `<a href="#"  onclick="page(${i})">`+" "+`${i+1}</a>`

                fetch(`https://api.seatgeek.com/2/events?lat=${lat}&lon=${lng}&type=${type}&client_id=Mjc4MTcxNDZ8MTY1NzU3OTE4MC4yMjE1NzI`)
                    .then(response => response.json())
                    .then(data => {
                        console.log(data);
                        var events ='';
                        var popularity = '';
                        total = document.getElementById('total');
                        total.innerHTML = `<h1>Total Events ${data.meta.total}</h1>`
                        for(var i = 0; i < data.events.length; i++) {
                            const date = new Date(data.events[i].datetime_local);
                            const [year, month, day] = [date.getFullYear(), date.getMonth() + 1, date.getDate()];
                            const dateString = 'm/d/year'+" "+`${month}/${day}/${year}`;
                            events += `<div><h1 class='loopEvent'>${data.events[i].title}`+" "+`${data.events[i].venue.name}`+" "+`${data.events[i].venue.display_location}`+" Date: "+dateString+" Popularity "+ `${data.events[i].popularity}`+`<a href="/events/${data.events[i].id}">Host a party at ${data.events[i].title}</a></br>`+
                                `<p class='loopEvent'><a href="/parties/${data.events[i].id}">Or see parties at ${data.events[i].title}</a></p></br></h1></div>`

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
                                console.log(lat,lng)
                                for(let i = 0; i < 11; i++){
                                    pageNum.innerHTML = 'Page:'
                                    pages.innerHTML += `<a href="#"  onclick="page(${i},${lat},${lng})">`+" "+`${i+1}</a>`

                                }

                                if(data.events <11){
                                    pages = document.getElementById('pages');
                                    pages.innerHTML = "no pages";
                                }

                            }

                            document.getElementById("eventTitle").innerHTML = events
                        }}
                    )
                    .catch(error => console.log(error));
                return (lat, lng);
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
                        total.innerHTML = `<h1 class='totalEvents '>Total Events ${data.meta.total}</h1>`
                        for(var i = 0; i < data.events.length; i++) {
                            const date = new Date(data.events[i].datetime_local);
                            const [year, month, day] = [date.getFullYear(), date.getMonth() + 1, date.getDate()];
                            const dateString = 'm/d/year'+" "+`${month}/${day}/${year}`;
                            events += `<div><h1 class='loopEvent'>${data.events[i].title}`+" "+`${data.events[i].venue.name}`+" "+`${data.events[i].venue.display_location}`+" Date: "+dateString+" Popularity "+ `${data.events[i].popularity}`+`<a href="/events/${data.events[i].id}">Host a party at ${data.events[i].title}</a></h1>`+
                                `<h2 class='loopEvent'><a href="/parties/${data.events[i].id}">Or see parties at ${data.events[i].title}</a></h2></br></div>`
                        }


//window onload
    fetch(`https://api.seatgeek.com/2/events?lat=${lat}&lon=${lng}&type=comedy&client_id=Mjc4MTcxNDZ8MTY1NzU3OTE4MC4yMjE1NzI`)
        .then(response => response.json())
        .then(data => {
                console.log(data);
                console.log('on load')
                var events ='';

                total = document.getElementById('total');
                total.innerHTML = `<h2 class='totalEvents'>Total Events ${data.meta.total}</h2>`
                for(var i = 0; i < data.events.length; i++) {
                    const date = new Date(data.events[i].datetime_local);
                    const [year, month, day] = [date.getFullYear(), date.getMonth() + 1, date.getDate()];
                    const dateString = `${month}/${day}/${year}`;
                    events += `<div class='loopEvent profile-div-outer row'>${data.events[i].title}\n`+" "+`${data.events[i].venue.name}\n`+" "+`${data.events[i].venue.display_location}\n`+" Date: "+ dateString + `${data.events[i].popularity}\n`+`<div class="row"><a href="/events/${data.events[i].id}" class="col profile-btn">Host a party at ${data.events[i].title}</a>`+
                        `<a href="/parties/${data.events[i].id}" class="col profile-btn">Or see parties at ${data.events[i].title}</a></div></div></br></br>`
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
                                for(let i = 0; i < 11; i++){
                                    pages.innerHTML += `<a href="#" onclick="page(${i})">`+" "+`${i+1}</a>`
                                }
                            }

                        }
                        document.getElementById("eventTitle").innerHTML = events
                    }
                )
                .catch(error => console.log(error));
        }

        getLocation();


    fetch(`https://api.seatgeek.com/2/events?q=${search}&type=${type}&client_id=Mjc4MTcxNDZ8MTY1NzU3OTE4MC4yMjE1NzI`)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            var events ='';

            total = document.getElementById('total');
            total.innerHTML = `<h2>Total Events ${data.meta.total}</h2>`
            for(var i = 0; i < data.events.length; i++) {
                const date = new Date(data.events[i].datetime_local);
                const [year, month, day] = [date.getFullYear(), date.getMonth() + 1, date.getDate()];
                const dateString = 'm/d/year'+" "+`${month}/${day}/${year}`;
                events += `<div class='loopEvent profile-div-outer row'>${data.events[i].title}\n`+" "+`${data.events[i].venue.name}\n`+" "+`${data.events[i].venue.display_location}\n`+" Date: "+ dateString + `${data.events[i].popularity}\n`+`<div class="row"><a href="/events/${data.events[i].id}" class="col profile-btn">Host a party at ${data.events[i].title}</a>`+
                    `<a href="/parties/${data.events[i].id}" class="col profile-btn">Or see parties at ${data.events[i].title}</a></div></div></br></br>`
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
                    total.innerHTML = `<h1>Total Events ${data.meta.total}</h1>`
                    for(var i = 0; i < data.events.length; i++) {
                        const date = new Date(data.events[i].datetime_local);
                        const [year, month, day] = [date.getFullYear(), date.getMonth() + 1, date.getDate()];
                        const dateString = 'm/d/year'+" "+`${month}/${day}/${year}`;
                        events += `<div><h1 class='loopEvent'>${data.events[i].title}`+" "+`${data.events[i].venue.name}`+" "+`${data.events[i].venue.display_location}`+" Date: "+dateString+" Popularity "+ `${data.events[i].popularity}`+`<a href="/events/${data.events[i].id}">Host a party at ${data.events[i].title}</a></h1>`+
                            `<h2 class='loopEvent'><a href="/parties/${data.events[i].id}">Or see parties at ${data.events[i].title}</a></h2></br></div>`
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

                            for(let i = 0; i < 11; i++){
                                pages.innerHTML += `<a href="#"  onclick="page(${i})">`+" "+`${i+1}</a>`

                            }

                console.log(data);
                console.log('page clicked!')
                var events ='';
                console.log(data.events);
                total = document.getElementById('total');
                total.innerHTML = `<h2>Total Events ${data.meta.total}</h2>`
                for(var i = 0; i < data.events.length; i++) {
                    const date = new Date(data.events[i].datetime_local);
                    const [year, month, day] = [date.getFullYear(), date.getMonth() + 1, date.getDate()];
                    const dateString = 'm/d/year'+" "+`${month}/${day}/${year}`;
                    events += `<div class='loopEvent profile-div-outer row'>${data.events[i].title}\n`+" "+`${data.events[i].venue.name}\n`+" "+`${data.events[i].venue.display_location}\n`+" Date: "+ dateString + `${data.events[i].popularity}\n`+`<div class="row"><a href="/events/${data.events[i].id}" class="col profile-btn">Host a party at ${data.events[i].title}</a>`+
                        `<a href="/parties/${data.events[i].id}" class="col profile-btn">Or see parties at ${data.events[i].title}</a></div></div></br></br>`
                }
                // for(var i = 0; i < 1; i++) {
                //     if(data.meta.total>=11){
                //         pages = document.getElementById('pages');
                //         let pagesCount = Math.ceil(data.meta.total/10);

                        }
                        document.getElementById("eventTitle").innerHTML = events
                    }})
                .catch(error => console.log(error));


        });
        console.log('Loaded!');
    }, 5000);
}
windowLoading();
function page(num, lat,lng){
    console.log(lat,lng)
    var type = document.getElementById("type").value
    console.log(type, num)
    var search = document.getElementById("search").value
    if (search == ""){
        fetch(`https://api.seatgeek.com/2/events?lat=${lat}&lon=${lng}&type=${type}&page=${num+1}&client_id=Mjc4MTcxNDZ8MTY1NzU3OTE4MC4yMjE1NzI`)

            .then(response => response.json())
            .then(data => {


                console.log(data);
                console.log('page clicked!')
                var events ='';
                console.log(data.events);
                total = document.getElementById('total');
                total.innerHTML = `<h2>Total Events ${data.meta.total}</h2>`
                for(var i = 0; i < data.events.length; i++) {
                    const date = new Date(data.events[i].datetime_local);
                    const [year, month, day] = [date.getFullYear(), date.getMonth() + 1, date.getDate()];
                    const dateString = 'm/d/year'+" "+`${month}/${day}/${year}`;
                    events += `<div class='loopEvent profile-div-outer row'>${data.events[i].title}\n`+" "+`${data.events[i].venue.name}\n`+" "+`${data.events[i].venue.display_location}\n`+" Date: "+ dateString + `${data.events[i].popularity}\n`+`<div class="row"><a href="/events/${data.events[i].id}" class="col profile-btn">Host a party at ${data.events[i].title}</a>`+
                        `<a href="/parties/${data.events[i].id}" class="col profile-btn">Or see parties at ${data.events[i].title}</a></div></div></br></br>`

                    console.log(data);
                    console.log('page clicked!, if statemnt')
                    var events ='';
                    console.log(data.events);
                    // total = document.getElementById('total');
                    // total.innerHTML = `<h1>Total Events ${data.meta.total}</h1>`
                    for(var i = 0; i < data.events.length; i++) {
                        // if(data.[i].p)
                        const date = new Date(data.events[i].datetime_local);
                        const [year, month, day] = [date.getFullYear(), date.getMonth() + 1, date.getDate()];
                        const dateString = 'm/d/year'+" "+`${month}/${day}/${year}`;
                        events += `<div><h1 class='loopEvent'>${data.events[i].title}`+" "+`${data.events[i].venue.name}`+" "+`${data.events[i].venue.display_location}`+" Date: "+dateString+" Popularity "+ `${data.events[i].popularity}`+`<a href="/events/${data.events[i].id}">Host a party at ${data.events[i].title}</a></h1>`+
                            `<h2 class='loopEvent'><a href="/parties/${data.events[i].id}">Or see parties at ${data.events[i].title}</a></h2></br></div>`
                    }
                    let pageNum = document.getElementById('pageNum');
                    pageNum.innerHTML = "Page" +" " + (num+1)
                    document.getElementById("eventTitle").innerHTML = events

                }
            ).catch(error => console.log(error));
    } else{
        fetch(`https://api.seatgeek.com/2/events?q=${search}&type=${type}&page=${num+1}&client_id=Mjc4MTcxNDZ8MTY1NzU3OTE4MC4yMjE1NzI`)
            .then(response => response.json())
            .then(data => {

                    console.log(data);
                    console.log('returned everything')
                    var events ='';
                    console.log(data.events);
                    total = document.getElementById('total');
                    total.innerHTML = `<h1>Total Events ${data.meta.total}</h1>`
                    for(var i = 0; i < data.events.length; i++) {
                        const date = new Date(data.events[i].datetime_local);
                        const [year, month, day] = [date.getFullYear(), date.getMonth() + 1, date.getDate()];
                        const dateString = 'm/d/year'+" "+`${month}/${day}/${year}`;
                        events += `<div><h1 class='loopEvent'>${data.events[i].title}`+" "+`${data.events[i].venue.name}`+" "+`${data.events[i].venue.display_location}`+" Date: "+dateString+" Popularity "+ `${data.events[i].popularity}`+`<a href="/events/${data.events[i].id}">Host a party at ${data.events[i].title}</a></h1>`+
                            `<h2 class='loopEvent'><a href="/parties/${data.events[i].id}">Or see parties at ${data.events[i].title}</a></h2></br></div>`
                    }
                    let pageNum = document.getElementById('pageNum');
                    pageNum.innerHTML = "Page" +" " + (num+1)
                    document.getElementById("eventTitle").innerHTML = events
                }
            ).catch(error => console.log(error));
    }
}
