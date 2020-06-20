#!/bin/bash

mvn clean package

docker build -t emilburzo/templater .

docker push emilburzo/templater
