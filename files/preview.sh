#!/bin/bash

cat ./info/titile

echo "Input File Path : "

read path

num=`cut -c  1-10 ${path}`
end=" ..."

result=$num$end

echo $result