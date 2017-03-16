$(document).ready(function () {

    $("#btnSubmitStart").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit_start();

    });

    $("#btnSubmitRed").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit_red();

    });
    $("#btnSubmitYellow").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit_yellow();

    });

});

function fire_ajax_submit_start() {

    // Get form
    var form = $('#start')[0];

    var data = new FormData(form);

    data.append("CustomField", "This is some extra data, testing");

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/salesloft/game/connectFour/start",
        data: data,
        //http://api.jquery.com/jQuery.ajax/
        //https://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            $('#result').text('');
            if (isArray(data)) {
                var f = Create2DArray(data);
                var trHTML = makeTableHTML(f);
                $('#result').append(trHTML);
            } else {
                $('#result').text(data);
            }
            console.log("SUCCESS : ", data);
            $("#btnSubmit").prop("disabled", false);

        },
        error: function (e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);

        }
    });

}
function Create2DArray(myArray) {
    var arr = [];

    for (var i = 0; i < myArray.length; i++) {
        if (i == myArray.length - 1) {
            arr[0] = myArray[myArray.length - 1];
        }
        arr[i + 1] = myArray[i];
    }

    return arr;
}


function makeTableHTML(myArray) {


    var result = "<table border=1>";
    for (var i = 0; i < myArray.length; i++) {
        result += "<tr>";
        for (var j = 0; j < myArray[i].length; j++) {
            result += "<td>" + myArray[i][j] + "</td>";
        }
        result += "</tr>";
    }
    result += "</table>";

    return result;
}

function fire_ajax_submit_red() {

    // Get form
    var form = $('#red')[0];

    var data = new FormData(form);

    data.append("CustomField", "This is some extra data, testing");

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/salesloft/game/connectFour/red",
        data: data,
        //http://api.jquery.com/jQuery.ajax/
        //https://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            $('#result').text('');
            if (isArray(data)) {
                var f = Create2DArray(data);
                var trHTML = makeTableHTML(f);
                $('#result').append(trHTML);
            } else {
                $('#result').text(data);
            }
            console.log("SUCCESS : ", data);
            $("#btnSubmit").prop("disabled", false);

        },
        error: function (e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);

        }
    });

}


function isArray(ob) {
    return ob.constructor === Array;
}

function fire_ajax_submit_yellow() {

    // Get form
    var form = $('#yellow')[0];

    var data = new FormData(form);

    data.append("CustomField", "This is some extra data, testing");

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/salesloft/game/connectFour/yellow",
        data: data,
        //http://api.jquery.com/jQuery.ajax/
        //https://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            $('#result').text('');
            if(isArray(data)) {
                var f = Create2DArray(data);
                var trHTML = makeTableHTML(f);
                $('#result').append(trHTML);
            }else{
                $('#result').text(data);
            }
            console.log("SUCCESS : ", data);
            $("#btnSubmit").prop("disabled", false);

        },
        error: function (e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);

        }
    });

}