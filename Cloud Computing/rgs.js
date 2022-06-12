const Hapi = require('@hapi/hapi');
const Mongoose = require('mongoose');
const Joi = require('joi');
const { string } = require('joi');

const server = new Hapi.Server({
    "host": "localhost",
    "port": 3000
});

Mongoose.connect("mongodb://localhost/ubook");

const userModel = Mongoose.model("user", {
    name: String,
    Gender: String,
    Age: Number,
    identity: Number,
    phoneNumber: Number,
    doctor: String
});

const userVal = Joi.object({
    name: Joi.string().required(),
    Gender: Joi.string().required(),
    Age: Joi.number().required(),
    identity: Joi.number().required().min(9),
    phoneNumber: Joi.number().required().min(10),
    doctor: Joi.string().required()
})

// POST/create user data
server.route({
    method: "POST",
    path:"/user",
    options: {
        validate: {
            payload: userVal
        },
    },
    handler: async (request, h) =>{
        try {
            var user = new userModel(request.payload)
            var result = await user.save();
            return h.response(result);
        } catch (error) {
            return h.response(error).code(500)
        }
    }
});

// GET al users
server.route({
    method: "GET",
    path: "/users",
    handler: async (request, h) =>{
        try {
            var users = await userModel.find().exec();
            return h.response(users);
        } catch (error) {
            return h.response(error).code(500)
        }
    }
});

// GET a user by id
server.route({
    method: "GET",
    path: "/user/{id}",
    handler: async (request, h) =>{
        try {
            var user = await userModel.findById(request.params.id).exec();
            return h.response(user);
        } catch (error) {
            return h.response(error).code(500)
        }
    }
});

// PUT a user
server.route({
    method: "PUT",
    path: "/user/{id}",
    options: {
        validate: {
            payload: userVal,
        },
    },
    handler: async (request, h) =>{
        try {
            var user = await userModel.findByIdAndUpdate(request.params.id, request.payload, {new: true});
            return h.response(user);
        } catch (error) {
            return h.response(error).code(500)
        }
    }
});

// DELETE
server.route({
    method: "DELETE",
    path: "/user/{id}",
    handler: async (request, h) =>{
        try {
            var user = await userModel.findByIdAndDelete(request.params.id);
            return h.response(user);
        } catch (error) {
            return h.response(error).code(500)
        }
    }
});

server.start();