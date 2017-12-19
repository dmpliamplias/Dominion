#!/usr/bin/env bash

echo 'Start copying database...'
rm ~/.h2/weddingcrashers_dominion.mv.db
cp ../cleanDB/weddingcrashers_dominion.mv.db ~/.h2/
echo 'Copied database'
