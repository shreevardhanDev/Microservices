#!/bin/bash

echo 'Starting gradle build'
sh build.sh
echo 'completed build.sh'

echo 'Starting image build'
sh docker-image-creation.sh
echo 'completed docker-image-creation.sh'
