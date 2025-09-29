#!/bin/bash

echo "🚀 Building Full-Stack Portfolio for Single Service Deployment..."

# Install frontend dependencies and build
echo "📦 Installing frontend dependencies..."
cd frontend
npm install

echo "🔨 Building frontend..."
npm run build

# Install backend dependencies
echo "📦 Installing backend dependencies..."
cd ../backend
npm install

echo "✅ Build complete! Ready for deployment."
echo "Frontend built to: frontend/dist/"
echo "Backend ready to serve from: backend/"