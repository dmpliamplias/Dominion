#!/usr/bin/env bash

echo 'Start copying database...'
mkdir -p ~/.h2/
cp ../cleanDB/weddingcrashers_dominion.mv.db ~/.h2/
echo 'Copied database'
