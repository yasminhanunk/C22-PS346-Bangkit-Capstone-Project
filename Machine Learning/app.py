from fastapi import FastAPI, Form
from fastapi.responses import HTMLResponse
import uvicorn


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


if __name__ == '__main__':
    uvicorn.run(app, host='127.0.0.1', port=8000)