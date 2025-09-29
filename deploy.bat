@echo off
echo 🚀 Preparing for Render deployment...

REM Check if we're in the right directory
if not exist "package.json" (
    echo ❌ Error: Run this script from the project root directory
    exit /b 1
)

REM Install dependencies
echo 📦 Installing backend dependencies...
cd backend
call npm install
if %errorlevel% neq 0 (
    echo ❌ Backend dependency installation failed
    exit /b 1
)

echo 📦 Installing frontend dependencies...
cd ..\frontend
call npm install
if %errorlevel% neq 0 (
    echo ❌ Frontend dependency installation failed
    exit /b 1
)

REM Test build
echo 🔨 Testing frontend build...
call npm run build
if %errorlevel% neq 0 (
    echo ❌ Frontend build failed
    exit /b 1
)

cd ..

echo ✅ All tests passed! Ready for Render deployment.
echo.
echo Next steps:
echo 1. Push your code to GitHub
echo 2. Follow the DEPLOYMENT_GUIDE.md instructions
echo 3. Deploy backend first, then frontend
echo.
echo Backend will be available at: https://your-backend-name.onrender.com
echo Frontend will be available at: https://your-frontend-name.onrender.com

pause