@echo off
setlocal
set "SDK="
if exist "%LOCALAPPDATA%\Android\Sdk\platform-tools\adb.exe" set "SDK=%LOCALAPPDATA%\Android\Sdk"
if not defined SDK if exist "%USERPROFILE%\AppData\Local\Android\Sdk\platform-tools\adb.exe" set "SDK=%USERPROFILE%\AppData\Local\Android\Sdk"
if not defined SDK (
  echo Khong tim thay Android SDK o duong dan mac dinh.
  echo Mo Android Studio ^> Tools ^> SDK Manager va sao chep Android SDK Location.
  echo Sau do tao file local.properties o thu muc goc voi noi dung:
  echo sdk.dir=C\:\\duong\\dan\\toi\\Android\\Sdk
  pause
  exit /b 1
)
(
  echo sdk.dir=%SDK:\=\\%
) > "%~dp0local.properties"
echo Da tao local.properties:
type "%~dp0local.properties"
echo.
echo Hay mo lai project trong Android Studio va chon File ^> Sync Project with Gradle Files.
pause
