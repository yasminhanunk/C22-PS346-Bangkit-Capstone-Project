// another way to use comment command (in a line)
// alert ("Hello World!")

// console.log("Hello Console");

// document.body.insertAdjacentHTML("beforeend", "<p>Hello from JS<p>")

/*
// variables
const userAge = 10; // number
const welcomeMessage = "Welcome to the site!" //string
const pasientName = 'nama';
const keluhan = `keluhan`;
const jenisKelamin = true; // boolean
const firstPerson = ["nama", "keluhan"] // array
*/

const userName = prompt("What is your name?");
document.body.insertAdjacentHTML("beforeend", `<h1>Welcome, ${userName}</h1>`);

const userAge = prompt("How old are you?");
console.log(`Umur pasien yaitu ${userAge}`);

// let command // variabel dapat didefinisikan ulang
// null/undefined untuk mengisi nilai variabel yang masih belum pasti

