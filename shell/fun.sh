#!/bin/bash
total=0
function add(){
total=$[$1+$2]
}
read -p "enter number" n1
read -p "enter number" n2
add $n1 $n2
echo $total
