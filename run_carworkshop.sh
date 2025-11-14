#!/bin/bash

NAME="car-workshop"

echo "Bulding ${NAME}..."
mvn package
echo "running..."
mvn spring-boot:run
echo "Done!"
echo "Go to the readme-file and try the different options!"
