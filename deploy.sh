#!/bin/bash

# Portfolio Deployment Script for Render

echo "🚀 Preparing for Render deployment..."

# Check if we're in the right directory
if [ ! -f "package.json" ]; then
    echo "❌ Error: Run this script from the project root directory"
    exit 1
fi

# Install dependencies
echo "📦 Installing backend dependencies..."
cd backend && npm install
if [ $? -ne 0 ]; then
    echo "❌ Backend dependency installation failed"
    exit 1
fi

echo "📦 Installing frontend dependencies..."
cd ../frontend && npm install
if [ $? -ne 0 ]; then
    echo "❌ Frontend dependency installation failed"
    exit 1
fi

# Test build
echo "🔨 Testing frontend build..."
npm run build
if [ $? -ne 0 ]; then
    echo "❌ Frontend build failed"
    exit 1
fi

cd ..

# Test backend
echo "🧪 Testing backend..."
cd backend
timeout 10s npm start &
sleep 5
curl -f http://localhost:10000/api/health > /dev/null 2>&1
if [ $? -eq 0 ]; then
    echo "✅ Backend test passed"
else
    echo "❌ Backend test failed"
fi
pkill -f "node server.js" 2>/dev/null

cd ..

echo "✅ All tests passed! Ready for Render deployment."
echo ""
echo "Next steps:"
echo "1. Push your code to GitHub"
echo "2. Follow the DEPLOYMENT_GUIDE.md instructions"
echo "3. Deploy backend first, then frontend"
echo ""
echo "Backend will be available at: https://your-backend-name.onrender.com"
echo "Frontend will be available at: https://your-frontend-name.onrender.com"