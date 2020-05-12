#!/bin/bash

for i in a b c
do 
  echo "taylor $i"
done

echo "---------------*--------------"

for j in "$*"
do 
echo "taylor $j"
done

echo "--------------@---------------"
for k in "$@"
  do 
     echo "taylor $k"
  done
