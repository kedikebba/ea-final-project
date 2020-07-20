$(document).ready(function () {
    alert($("input[name='accsvc']").value);
    $.ajax({
        "url": "http://localhost:8081/provider/list",
        "dataType": "json",
        "type": "GET"

    }).done(function (data) {
        var country = $("#country");
        country.append('<option value="" disabled>Select Country</option>');
        $.each(data,function (k,v) {
            country.append('<option value="'+v.providerCountry+'">'+v.providerCountry+'</option>')
        })
    })
});