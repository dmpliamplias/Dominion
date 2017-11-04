ECHO OFF
ECHO Start copying database...
if not exist "%userprofile%\.h2\" mkdir %userprofile%\.h2\
copy ..\cleanDB\weddingcrashers_dominion.mv.db %userprofile%\.h2\
ECHO Copied database