@echo off
echo 🚀 Building Full-Stack Portfolio for Single Service Deployment...

REM Install frontend dependencies and build
echo 📦 Installing frontend dependencies...
cd frontend
call npm install

echo 🔨 Building frontend...
call npm run build

REM Install backend dependencies
echo 📦 Installing backend dependencies...
cd ..\backend
call npm install

echo ✅ Build complete! Ready for deployment.
echo Frontend built to: frontend/dist/
echo Backend ready to serve from: backend/

pause