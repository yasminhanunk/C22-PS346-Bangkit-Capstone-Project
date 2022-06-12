// declarations
function sayHello(){
    console.log("Hello!")
}

// invoking
sayHello();

// function can take parameters
function greetUser(userName){
console.log(`Hello ${userName}`);
}

// supply parameters
greetUser("widia"); //output: Hello widia
greetUser("dewi"); //output: Hello dewi
greetUser(); //output: Hello undefined

//array syntax
const sayGoodbye = (userName) =>{
    console.log("Goodbye!");
    console.log(`See you soon, ${userName}`);
}
sayGoodbye("widia");
sayGoodbye();

const addNumber = (num1, num2, num3) =>{
    const sum = num1 + num2 + num3;
    return sum;
}
const sum1 = addNumber(10, 10, 10);
console.log(`The sum is ${sum1}`);