from fastapi import FastAPI, Form
from fastapi.responses import HTMLResponse
import uvicorn
import tensorflow as tf


app = FastAPI()

@app.get('/')
def index():
    return {"WELCOME": "GO TO /docs route, or /post or send post request to /predict "}


@app.get('/predict', response_class=HTMLResponse)
def take_input():
    return '''<form method="post">
    <input type="text" name="doctor" value=""/>
    <input type="text" name="gender" value=""/>
    <input type="number" name="age" value=""/>
    <input type="submit"/>
    </form>'''


@app.post('/predict')
def predict(doctor: str = Form(), gender: str = Form(), age: int = Form()):
    loaded_model = tf.keras.models.load_model('./saved_model')
    data = {
        'doctor': doctor,
        'gender': gender,
        'age': age
    }
    input_dict = {name: tf.convert_to_tensor([value]) for name, value in data.items()}
    prediction = loaded_model.predict(input_dict)
    
    return {'prediction': prediction.tolist()[0][0]}


if __name__ == '__main__':
    uvicorn.run(app, host='127.0.0.1', port=8000)