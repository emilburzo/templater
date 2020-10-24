#!/bin/bash

NAME="templater"
IMAGE="emilburzo/templater:latest"

docker pull ${IMAGE}

docker stop ${NAME}

docker rm ${NAME}

docker run -d --restart=always --name ${NAME} -p 8968:8080 ${IMAGE}
