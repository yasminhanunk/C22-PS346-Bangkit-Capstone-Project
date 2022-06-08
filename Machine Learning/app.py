from fastapi import FastAPI
import uvicorn


app = FastAPI()

@app.get('/')
def index():
    return {"WELCOME": "GO TO /docs route, or /post or send post request to /predict "}

if __name__ == '__main__':
    uvicorn.run(app, host='127.0.0.1', port=8000)