#!/bin/bash

cd src

#javac command to compile
echo "compiling java files..."
javac bankapp/*.java
echo "compiled successfully"

#java to run the code
java bankapp.MenuGUI

cd ..
