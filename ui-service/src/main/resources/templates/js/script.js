// alert("working");
// $(document).ready(function () {
//     alert("data");
//     $.ajax({
//         "url": "http://localhost:8081/provider/list",
//         "dataType": "json",
//         "type": "GET"
//
//     }).done(function (data) {
//         console.log(data);
//         alert(data);
//         var country = $("#country");
//         country.append('<option value="" disabled>Select Country</option>');
//         $.each(data,function (k,v) {
//             country.append('<option value="'+v.providerCode+'">'+v.providerName+'</option>')
//         })
//     })
// });