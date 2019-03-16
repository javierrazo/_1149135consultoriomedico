 today = new Date();
 currentMonth = today.getMonth();
 currentYear = today.getFullYear();
 selectYear = document.getElementById("year");
 selectMonth = document.getElementById("month");
 //doctores = document.getElementById("doctores");
 jsonCitas = JSON.parse(document.getElementById("infoJson").getAttribute("value"));
 jsonDoctores = JSON.parse(document.getElementById("docJson").getAttribute("value"));

 //alert(jsonCitas);

 months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

 monthAndYear = document.getElementById("monthAndYear");
showCalendar(currentMonth, currentYear);


function next() {
    currentYear = (currentMonth === 11) ? currentYear + 1 : currentYear;
    currentMonth = (currentMonth + 1) % 12;
    showCalendar(currentMonth, currentYear);
}

function previous() {
    currentYear = (currentMonth === 0) ? currentYear - 1 : currentYear;
    currentMonth = (currentMonth === 0) ? 11 : currentMonth - 1;
    showCalendar(currentMonth, currentYear);
}

function jump() {
    currentYear = parseInt(selectYear.value);
    currentMonth = parseInt(selectMonth.value);
    showCalendar(currentMonth, currentYear);
}

function showCalendar(month, year) {

     firstDay = (new Date(year, month)).getDay();
     daysInMonth = 32 - new Date(year, month, 32).getDate();

     tbl = document.getElementById("calendar-body"); // body of the calendar

    // clearing all previous cells
    tbl.innerHTML = "";

    // filing data about month and in the page via DOM.
    monthAndYear.innerHTML = months[month] + " " + year;
    selectYear.value = year;
    selectMonth.value = month;

    // creating all cells
     date = 1;
    for ( i = 0; i < 6; i++) {
        // creates a table row
         row = document.createElement("tr");

        //creating individual cells, filing them up with data.
        for ( j = 0; j < 7; j++) {
            if (i === 0 && j < firstDay) {
                 cell = document.createElement("td");
                 cellText = document.createTextNode("");
                cell.appendChild(cellText);
                row.appendChild(cell);
            }
            else if (date > daysInMonth) {
                break;
            }

            else {
                 cell = document.createElement("td");

                 //Recorro el arreglo de citas
                for(var z = 0; z < jsonCitas.length; z++) {
                    var obj = jsonCitas[z];
                    var d = new Date(obj.fecha);

                    //Si la cita tiene la fecha de la celda que se esta creando, se añade el boton
                    if (date === d.getDate() && month === d.getMonth() && year === d.getFullYear()) {
                        //Creo un boton
                        var btn = document.createElement("BUTTON");
                        var t = document.createTextNode("Ver");       // Create a text node
                        btn.appendChild(t);
                        //Creo un atributo para que sea capaz de ejecutar el metodo
                        var att = document.createAttribute("onclick");
                        att.value = "imprimir(" + obj.idCita + ")";
                        //Al boton le añado el atributo
                        btn.setAttributeNode(att);
                        //Añado el boton a la celda
                        cell.appendChild(btn);
                    }
                }
                //Se crea el texto de la celda y se le añade
                cellText = document.createTextNode(date);
                cell.appendChild(cellText);
                 //cellText = document.createTextNode(date);
                if (date === today.getDate() && year === today.getFullYear() && month === today.getMonth()) {
                    cell.classList.add("bg-info");
                } // color today's date
                //alert("celda a agregar " + cellText.valueOf() );
                //cell.appendChild(cellText);
                row.appendChild(cell);
                date++;
                bandera = false;
            }


        }

        tbl.appendChild(row); // appending each row into calendar body.
    }
}

function imprimir(id) {
    for(var i = 0; i < jsonCitas.length; i++) {
        var obj = jsonCitas[i];
        if(id === obj.idCita){
            var d = new Date(obj.fecha);
            alert( "Cita el: " + d.getDate() + " de " + d.getMonth() + " del " + d.getFullYear() + " a las "+ d.getHours() +":" + d.getMinutes() + "\n" +
                    "Paciente: " + obj.nombre + " " +  obj.apellidoP + " " + obj.apellidoM + " " + "\n" +
                    "Asunto: " + obj.asunto + "\n" +
                    "Con el doctor: " + nombreDoctor(obj.idDoctor));
        }
    }
}

function nombreDoctor(idDoctor) {
    for(var i = 0; i < jsonDoctores.length; i++) {
        var obj = jsonDoctores[i];
        if(idDoctor === obj.idDoctor){
            return "Dr. " + obj.nombre + " " + obj.apellidoP + " " + obj.apellidoM;
        }
    }
}
