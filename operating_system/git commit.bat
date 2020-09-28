@Echo off
echo Please Input Commit message
Set /p msg=
git add .
git commit -m "%msg%"
git push
pause