# Complete Render Deployment Guide

## Prerequisites
- GitHub account with your portfolio code pushed
- Render account (free at render.com)

## Step-by-Step Deployment Instructions

### Method 1: Deploy Both Services Separately (Recommended)

#### Step 1: Deploy Backend API

1. **Go to Render Dashboard**
   - Visit https://render.com
   - Click "Sign In" or "Get Started"
   - Sign in with GitHub

2. **Create New Web Service**
   - Click "New +" button
   - Select "Web Service"

3. **Connect Repository**
   - Select "Build and deploy from a Git repository"
   - Click "Connect" next to your GitHub account
   - Find and select your portfolio repository
   - Click "Connect"

4. **Configure Backend Service**
   Fill out the form EXACTLY as follows:

   **Basic Settings:**
   - **Name**: `portfolio-backend` (or any name you prefer)
   - **Region**: `Oregon (US West)` (or closest to you)
   - **Branch**: `main` (or your default branch)
   - **Root Directory**: `backend`
   - **Runtime**: `Node`

   **Build & Deploy:**
   - **Build Command**: `npm install`
   - **Start Command**: `npm start`

   **Advanced Settings:**
   - **Instance Type**: `Free`
   - **Auto-Deploy**: `Yes` (checked)

5. **Environment Variables**
   Click "Advanced" then add these environment variables:
   
   | Key | Value |
   |-----|-------|
   | `NODE_ENV` | `production` |
   | `PORT` | `10000` |
   | `FRONTEND_URL` | `https://your-frontend-name.onrender.com` |

   **Note**: Replace `your-frontend-name` with the name you'll use for frontend (Step 2)

6. **Deploy Backend**
   - Click "Create Web Service"
   - Wait for deployment (5-10 minutes)
   - Note the backend URL: `https://portfolio-backend.onrender.com`

#### Step 2: Deploy Frontend

1. **Create Another Web Service**
   - Go back to Render dashboard
   - Click "New +" button
   - Select "Static Site"

2. **Connect Same Repository**
   - Select "Build and deploy from a Git repository"
   - Select your portfolio repository again
   - Click "Connect"

3. **Configure Frontend Service**
   Fill out the form EXACTLY as follows:

   **Basic Settings:**
   - **Name**: `portfolio-frontend` (or any name you prefer)
   - **Branch**: `main`
   - **Root Directory**: `frontend`

   **Build Settings:**
   - **Build Command**: `npm install && npm run build`
   - **Publish Directory**: `dist`

4. **Environment Variables**
   Add this environment variable:
   
   | Key | Value |
   |-----|-------|
   | `VITE_API_URL` | `https://portfolio-backend.onrender.com/api` |

   **Note**: Replace `portfolio-backend` with your actual backend service name

5. **Deploy Frontend**
   - Click "Create Static Site"
   - Wait for deployment (5-10 minutes)

#### Step 3: Update Backend CORS

1. **Update Backend Environment**
   - Go to your backend service in Render
   - Click "Environment"
   - Update `FRONTEND_URL` to your actual frontend URL
   - Example: `https://portfolio-frontend.onrender.com`

2. **Redeploy Backend**
   - Go to "Manual Deploy"
   - Click "Deploy latest commit"

### Method 2: Deploy Using render.yaml (Alternative)

1. **Push render.yaml to Repository**
   - The `render.yaml` file is already created in your project
   - Commit and push it to GitHub

2. **Create Blueprint**
   - In Render dashboard, click "New +"
   - Select "Blueprint"
   - Connect your repository
   - Render will automatically detect the `render.yaml` file
   - Click "Apply"

## Important Configuration Details

### Backend Configuration
- **Port**: Must be `10000` (Render's default)
- **CORS**: Configured for Render domains
- **Environment**: Production settings enabled

### Frontend Configuration
- **Build Tool**: Vite (already configured)
- **Output Directory**: `dist`
- **API URL**: Points to backend service

### Environment Variables Summary

**Backend (.env):**
```
NODE_ENV=production
PORT=10000
FRONTEND_URL=https://your-frontend-name.onrender.com
```

**Frontend (.env):**
```
VITE_API_URL=https://your-backend-name.onrender.com/api
```

## Troubleshooting

### Common Issues:

1. **Images Not Loading**
   - Check that all images are in `frontend/public/` directory
   - Verify image paths start with `/` (e.g., `/profile.svg`)

2. **API Calls Failing**
   - Verify `VITE_API_URL` points to correct backend URL
   - Check CORS configuration in backend
   - Ensure backend is deployed and running

3. **Build Failures**
   - Check build logs in Render dashboard
   - Verify all dependencies are in `package.json`
   - Ensure Node.js version compatibility

4. **Backend Not Starting**
   - Check that `npm start` script exists in `backend/package.json`
   - Verify PORT environment variable is set
   - Check server logs for errors

### Testing Deployment

1. **Test Backend API**
   - Visit: `https://your-backend-name.onrender.com/api/health`
   - Should return: `{"status":"OK","message":"Portfolio API is running"}`

2. **Test Frontend**
   - Visit: `https://your-frontend-name.onrender.com`
   - Check that images load properly
   - Test contact form functionality

## Final URLs

After successful deployment, you'll have:
- **Frontend**: `https://your-frontend-name.onrender.com`
- **Backend API**: `https://your-backend-name.onrender.com`

## Free Tier Limitations

- Services sleep after 15 minutes of inactivity
- 750 hours per month (enough for one service)
- Cold start delay (30-60 seconds) when waking up
- Consider upgrading for production use

## Next Steps

1. Update your GitHub README with live URLs
2. Test all functionality on live site
3. Set up custom domain (optional, paid feature)
4. Monitor performance and logs in Render dashboard