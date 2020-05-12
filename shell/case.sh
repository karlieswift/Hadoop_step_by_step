#!/bin/bash

if [ $# -lt 1 ]
then echo "error"
     exit
fi


case $1 in
"1")
    echo "1-1"
;;
"2")
  echo "2-2"
;;
*)  echo "ohter"
;;
esac
