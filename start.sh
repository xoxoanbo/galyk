#!/bin/bash

nohup mvn spring-boot:run -X >> log/spring.log 2>&1 &
