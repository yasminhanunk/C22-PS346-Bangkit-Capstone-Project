// untuk membuat output berada di posisi tertentu dengan memindahkan fungsi <div>
const jsOutputDiv = document.querySelector("#js-output");
console.log(jsOutputDiv);
// this return null jika tidak ada elemen

/*
jsOutputDiv.insertAdjacentHTML(
    "beforeend", 
    `
    <p>Howdy, I'm coming from the the JS</p>
    <p>halo, kamu bisa mindahin aku loh</p>
    `
);
*/

/*
const clickButton = document.querySelector("#click-button")
let numClicks = 0;
clickButton.style.backgroundColor = "hsla(188, 30%, 40%, 0.40)";
clickButton.style.text = "white";
function onButtonClick() {
    numClicks += 1;
    console.log(`You clicked ${numClicks} times`);
    clickButton.textContent = `You clicked ${numClicks} times`;
}
clickButton.addEventListener("click", onButtonClick)
*/

// untuk menampilkan input yang diberikan pada console
function output_form(){
    var nameInput = document.getElementById("name-input").value;
    var jkInput = document.getElementById("jk-input").value;
    var ageInput = document.getElementById("age-input").value;
    var poliInput = document.getElementById("poli-input").value;
    var mailInput = document.getElementById("mail-input").value;
    var phoneInput = document.getElementById("phone-input").value;
    var identityInput = document.getElementById("identity-input").value;
}
const saveButton = document.getElementById("click-button")
saveButton.addEventListener("click", ()=>{
    var nameInput = document.getElementById("name-input").value;
    var jkInput = document.getElementById("jk-input").value;
    var ageInput = document.getElementById("age-input").value;
    var poliInput = document.getElementById("poli-input").value;
    var mailInput = document.getElementById("mail-input").value;
    var phoneInput = document.getElementById("phone-input").value;
    var identityInput = document.getElementById("identity-input").value;
    
    console.log({
        nameInput,
        jkInput,
        ageInput,
        poliInput,
        mailInput,
        phoneInput,
        identityInput,
    });
});


// const saveButton = document.getElementById("#click-button");

/*
 saveButton.addEventListener("click", ()=>{
    const text = saveInput.value;
    console.log("text");
    // text input diubah menjadi voice output
//    if (text !== ""){
  //      speechSynthesis.cancel();
  //      const utterance = new SpeechSynthesisUtterance(text);
  //      utterance.pitch = 10;
  //      utterance.rate = 3; //kecepatan spelling
  //      speechSynthesis.speak(utterance);
  //  }
}) */
