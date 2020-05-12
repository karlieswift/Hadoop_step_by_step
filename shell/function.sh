#!/bin/bash

sum=0
function sum(){
sum=$[$1 + $2]
}

read -t 10 -p "enter number" n1
read -t 10 -p "enter number" n2
sum $n1 $n2
echo "sum=$sum"

