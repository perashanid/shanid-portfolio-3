# 🚀 Single Web Service Deployment Guide for Render

Deploy your entire full-stack portfolio as ONE web service on Render.

## 📋 Step-by-Step Deployment Instructions

### Prerequisites
1. **Push all code to GitHub** (make sure everything is committed and pushed)
2. **Create Render account** at https://render.com (sign up with GitHub)

---

## 🔧 STEP 1: Create Single Web Service

### 1.1 Go to Render Dashboard
1. **Visit**: https://dashboard.render.com
2. **Click "New +"** button (top right)
3. **Select "Web Service"**

### 1.2 Connect Repository
1. **Select "Build and deploy from a Git repository"**
2. **Click "Connect"** next to your GitHub account
3. **Find your portfolio repository** in the list
4. **Click "Connect"** next to your repository

### 1.3 Fill Configuration Form

**Fill EXACTLY as shown below:**

#### Basic Information
| Field | Value |
|-------|-------|
| **Name** | `portfolio-fullstack` |
| **Region** | `Oregon (US West)` or closest to you |
| **Branch** | `main` |
| **Root Directory** | Leave EMPTY (blank) |
| **Runtime** | `Node` |

#### Build & Deploy Settings
| Field | Value |
|-------|-------|
| **Build Command** | `cd frontend && npm install --include=dev && npm run build && cd ..` |
| **Start Command** | `npm start` |

#### Advanced Settings
| Field | Value |
|-------|-------|
| **Instance Type** | `Free` |
| **Auto-Deploy** | ✅ Yes (checked) |

### 1.4 Environment Variables
**Click "Advanced" → "Add Environment Variable"**

Add these variables:

| Key | Value |
|-----|-------|
| `NODE_ENV` | `production` |
| `PORT` | `10000` |

### 1.5 Deploy Service
1. **Click "Create Web Service"**
2. **Wait 10-15 minutes** for deployment (builds both frontend and backend)
3. **Your app will be live at**: `https://portfolio-fullstack.onrender.com`

---

## 🧪 STEP 2: Test Your Deployment

### 2.1 Test Backend API
**Visit**: `https://portfolio-fullstack.onrender.com/api/health`

**Expected Response**:
```json
{
  "status": "OK",
  "message": "Portfolio API is running",
  "timestamp": "2025-09-29T..."
}
```

### 2.2 Test Frontend
**Visit**: `https://portfolio-fullstack.onrender.com`

**Check**:
- ✅ Website loads without errors
- ✅ Your profile image displays (`/profile.jpg`)
- ✅ Project images display
- ✅ All sections work properly
- ✅ Navigation works
- ✅ API calls work (check browser console for errors)

---

## 📝 Copy-Paste Form Values

```
Name: portfolio-fullstack
Region: Oregon (US West)
Branch: main
Root Directory: [LEAVE BLANK]
Runtime: Node
Build Command: cd frontend && npm install --include=dev && npm run build && cd ..
Start Command: npm start
Instance Type: Free
Auto-Deploy: Yes

Environment Variables:
NODE_ENV = production
PORT = 10000
```

---

## 🔧 How It Works

### Build Process:
1. **Frontend Build**: Installs dependencies and builds React app to `frontend/dist/`
2. **Backend Setup**: Installs backend dependencies
3. **Static Serving**: Backend serves frontend files from `dist/` folder
4. **API Routes**: Backend handles `/api/*` routes
5. **SPA Support**: All other routes serve `index.html` for React Router

### File Structure After Build:
```
portfolio-website/
├── frontend/
│   └── dist/           ← Built React app
│       ├── index.html
│       ├── assets/
│       └── ...
├── backend/
│   ├── server.js       ← Serves both API and static files
│   └── package.json
└── package.json        ← Root build scripts
```

---

## ⚠️ Important Notes

1. **Single URL**: Everything runs on one domain (e.g., `https://portfolio-fullstack.onrender.com`)
2. **API Endpoints**: Available at `/api/health`, `/api/portfolio`, `/api/contact`
3. **Frontend**: Served from root `/` with React Router support
4. **Build Time**: Takes longer (10-15 minutes) as it builds both frontend and backend
5. **Free Tier**: Service sleeps after 15 minutes of inactivity
6. **Cold Start**: First visit after sleep takes 30-60 seconds

---

## 🔧 Troubleshooting

### If Build Fails:
- Check build logs in Render dashboard
- Verify both `frontend/package.json` and `backend/package.json` exist
- Ensure root `package.json` has correct build script

### If Frontend Doesn't Load:
- Check that `frontend/dist/` folder was created during build
- Verify backend serves static files correctly
- Check browser console for errors

### If API Calls Fail:
- Test API endpoints directly: `/api/health`
- Check backend logs in Render dashboard
- Verify API routes are working

### If Images Don't Load:
- Ensure `profile.jpg` is in `frontend/public/`
- Check project images are in `frontend/public/projects/`
- Verify images are copied to `dist/` during build

---

## 🎉 Success!

After successful deployment, your portfolio will be live at:
**`https://portfolio-fullstack.onrender.com`**

- ✅ Frontend and backend on same domain
- ✅ No CORS issues
- ✅ Single service to manage
- ✅ Easier deployment and maintenance

Your full-stack portfolio is now deployed as a single web service!