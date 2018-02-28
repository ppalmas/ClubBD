/**
 * 
 */

function getGestion(){
    var form = document.createElement('form');
    form.method = "GET";
    form.action = "admin.htm";
    var c1 = document.createElement('input');
    c1.type = "hidden";
    c1.name = "idco";
    c1.value = document.getElementById("idco").value;
    form.appendChild(c1);
    document.body.appendChild(form);
    form.submit();
}

