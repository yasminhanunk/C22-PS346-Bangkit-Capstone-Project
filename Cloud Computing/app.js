const Hapi = require('@hapi/hapi');
const Mongoose = require('mongoose');
const Joi = require('joi');
const { string } = require('joi');

const server = new Hapi.Server({
    "host": "localhost",
    "port": 3000
});

Mongoose.connect("mongodb://localhost/pbook");

const patientModel = Mongoose.model("patient", {
    name: String,
    doctor: String,
    Gender: String,
    Age: Number,
    st: Number
});

const validator = Joi.object({
    name: Joi.string().required(),
    doctor: Joi.string().required(),
    Gender: Joi.string().required(),
    Age: Joi.number().required(),
    st: Joi.number()
})

// POST/create patient data
server.route({
    method: "POST",
    path:"/patient",
    options: {
        validate: {
            payload: validator
        },
    },
    handler: async (request, h) =>{
        try {
            var patient = new patientModel(request.payload)
            var result = await patient.save();
            return h.response(result);
        } catch (error) {
            return h.response(error).code(500)
        }
    }
});

// GET al patients
server.route({
    method: "GET",
    path: "/patients",
    handler: async (request, h) =>{
        try {
            var patients = await patientModel.find().exec();
            return h.response(patients);
        } catch (error) {
            return h.response(error).code(500)
        }
    }
});

// GET a patient by id
server.route({
    method: "GET",
    path: "/patient/{id}",
    handler: async (request, h) =>{
        try {
            var patient = await patientModel.findById(request.params.id).exec();
            return h.response(patient);
        } catch (error) {
            return h.response(error).code(500)
        }
    }
});

// PUT a patient
server.route({
    method: "PUT",
    path: "/patient/{id}",
    options: {
        validate: {
            payload: validator,
        },
    },
    handler: async (request, h) =>{
        try {
            var patient = await patientModel.findByIdAndUpdate(request.params.id, request.payload, {new: true});
            return h.response(patient);
        } catch (error) {
            return h.response(error).code(500)
        }
    }
});

// DELETE
server.route({
    method: "DELETE",
    path: "/patient/{id}",
    handler: async (request, h) =>{
        try {
            var patient = await patientModel.findByIdAndDelete(request.params.id);
            return h.response(patient);
        } catch (error) {
            return h.response(error).code(500)
        }
    }
});

server.start();