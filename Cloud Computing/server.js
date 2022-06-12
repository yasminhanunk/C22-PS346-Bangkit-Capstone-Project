const { default: mongoose } = require("mongoose");

const db = mongoose.connection;
db.on("error",console.error.bind(console, "connection error: "));
db.once("open", function () {
    console.log("connected");
});

app.use(Router);

app.listen(3000, ()=>{
    console.log("server is runnning")
});