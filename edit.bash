#!/bin/bash

ctags -R src/
vim `find src/ -type f` pom.xml
