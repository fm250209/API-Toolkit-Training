#!/bin/bash

mvn clean dependency:copy-dependencies -U
docker-compose build
