/**
 * Created by AllenHu on 2016/2/23.
 */
function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("Text", ev.target.id);
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("Text");
    ev.target.appendChild(document.getElementById(data));
}

function getLocation() {
    var x = document.getElementById("demo");
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
        //x.innerHTML="该浏览器不支持获取地理位置11111111";
    } else {
        x.innerHTML = "该浏览器不支持获取地理位置";
    }

}
function showPosition(position) {
    var x = document.getElementById("demo");
    //x.innerHTML = "纬度:" + position.coords.latitude + "<br>经度:" + position.coords.longitude;
    x.innerHTML = "该浏览器不支持获取地理位置fadfasf";
}

function clickCounter(name) {
    if (typeof(Storage) !== "undefined") {
        if (localStorage.clickcount) {
            localStorage.clickcount = Number(localStorage.clickcount) + 1;
        }
        else {
            localStorage.clickcount = 1;
        }
        document.getElementById(name).innerHTML = "You have clicked the button " + localStorage.clickcount + " time(s).";
    }
    else {
        document.getElementById(name).innerHTML = "Sorry, your browser does not support web storage...";
    }
}

function clickCounter2() {
    if (typeof (Storage) !== "undefined") {
        if (sessionStorage.clickcount) {
            sessionStorage.clickcount = Number(sessionStorage.clickcount) + 1;
        } else {
            sessionStorage.clickcount = 1;
        }
        document.getElementById("btn2").innerHTML = "You have clicked the button " + sessionStorage.clickcount
            + " time(s) in this session."
    } else {
        document.getElementById("btn2").innerHTML = "Sorry, your browser does not support web storage...";
    }
}


function funFromjs(){
    document.getElementById("helloweb").innerHTML="HelloWebView,i'm from js";

}


/*
 function showPosition() {
 var x = document.getElementById("demo");
 //x.innerHTML = "纬度:" + position.coords.latitude + "<br>经度:" + position.coords.longitude;
 x.innerHTML="该浏览器不支持获取地理位置2222222";
 }*/
