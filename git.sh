#!/bin/sh
clear
git pull
echo "command > "
read command
git status -s 
git add .
git commit -m ${command}
git push