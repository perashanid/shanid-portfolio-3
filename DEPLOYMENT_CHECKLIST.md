# Render Deployment Checklist ✅

## Pre-Deployment Checklist

- [ ] All code committed and pushed to GitHub
- [ ] Images are in `frontend/public/` directory
- [ ] Backend runs on port 10000
- [ ] Frontend builds successfully
- [ ] Environment variables configured

## Backend Deployment Form Fields

### Service Configuration
- **Service Type**: Web Service
- **Name**: `portfolio-backend` (or your choice)
- **Region**: Oregon (US West) or closest to you
- **Branch**: `main`
- **Root Directory**: `backend`
- **Runtime**: Node

### Build & Deploy
- **Build Command**: `npm install`
- **Start Command**: `npm start`

### Environment Variables
| Key | Value |
|-----|-------|
| `NODE_ENV` | `production` |
| `PORT` | `10000` |
| `FRONTEND_URL` | `https://your-frontend-name.onrender.com` |

## Frontend Deployment Form Fields

### Service Configuration
- **Service Type**: Static Site
- **Name**: `portfolio-frontend` (or your choice)
- **Branch**: `main`
- **Root Directory**: `frontend`

### Build Settings
- **Build Command**: `npm install && npm run build`
- **Publish Directory**: `dist`

### Environment Variables
| Key | Value |
|-----|-------|
| `VITE_API_URL` | `https://your-backend-name.onrender.com/api` |

## Post-Deployment Checklist

- [ ] Backend health check works: `/api/health`
- [ ] Frontend loads without errors
- [ ] Profile image displays correctly
- [ ] Project images display correctly
- [ ] Contact form works (if implemented)
- [ ] All navigation works
- [ ] Mobile responsive design works

## URLs to Test

Replace with your actual service names:

### Backend Tests
- Health: `https://portfolio-backend.onrender.com/api/health`
- Portfolio: `https://portfolio-backend.onrender.com/api/portfolio`

### Frontend Tests
- Home: `https://portfolio-frontend.onrender.com`
- All sections load properly
- Images display correctly

## Common Issues & Solutions

### Images Not Loading
- ✅ Check images are in `frontend/public/`
- ✅ Verify paths start with `/` (e.g., `/profile.svg`)
- ✅ Rebuild and redeploy frontend

### API Calls Failing
- ✅ Check `VITE_API_URL` environment variable
- ✅ Verify backend is running and accessible
- ✅ Check CORS configuration

### Build Failures
- ✅ Check build logs in Render dashboard
- ✅ Verify all dependencies in `package.json`
- ✅ Test build locally first

## Free Tier Notes

- Services sleep after 15 minutes of inactivity
- Cold start takes 30-60 seconds
- 750 hours per month limit
- Consider paid plan for production use