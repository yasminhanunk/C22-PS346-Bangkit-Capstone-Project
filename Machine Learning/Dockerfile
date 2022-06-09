FROM python:3.8-slim

COPY . ./
ENV APP_HOME /app
WORKDIR $APP_HOME

RUN pip install -r requirements.txt
CMD exec gunicorn --bind :$PORT --workers 1 --worker-class uvicorn.workers.UvicornWorker  --threads 8 app:app