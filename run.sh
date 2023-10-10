#!/bin/bash

echo "Digite o nome da classe:"
read className

javac $className.java -cp javassist.jar; java -cp .:javassist.jar $className