# Deploy Portfolio to Render

## Prerequisites
- GitHub account
- Render account (free tier available)
- Your portfolio code pushed to GitHub

## Step-by-Step Deployment Guide

### 1. Prepare Your Repository

1. **Push your code to GitHub:**
   ```bash
   git add .
   git commit -m "Prepare for deployment"
   git push origin main
   ```

2. **Ensure your project structure:**
   ```
   portfolio-website/
   ├── frontend/
   │   ├── src/
   │   ├── public/
   │   ├── package.json
   │   ├── vite.config.ts
   │   └── _redirects
   └── README.md
   ```

### 2. Deploy on Render

1. **Go to Render Dashboard:**
   - Visit [render.com](https://render.com)
   - Sign up/Login with your GitHub account

2. **Create New Static Site:**
   - Click "New +" button
   - Select "Static Site"
   - Connect your GitHub repository

3. **Configure Build Settings:**
   - **Name:** `shanid-portfolio` (or your preferred name)
   - **Branch:** `main`
   - **Root Directory:** `frontend`
   - **Build Command:** `npm run build`
   - **Publish Directory:** `dist`

4. **Environment Variables (if needed):**
   - No environment variables needed for this static site

5. **Deploy:**
   - Click "Create Static Site"
   - Render will automatically build and deploy your site

### 3. Custom Domain (Optional)

1. **Add Custom Domain:**
   - Go to your site settings in Render
   - Click "Custom Domains"
   - Add your domain name
   - Update your domain's DNS settings as instructed

### 4. Build Commands Reference

For your Vite + React + TypeScript project:
- **Build Command:** `npm run build`
- **Start Command:** Not needed (static site)
- **Install Command:** `npm install` (automatic)

### 5. Troubleshooting

**Common Issues:**

1. **Build Fails:**
   - Check that all dependencies are in package.json
   - Ensure TypeScript compiles without errors
   - Check build logs in Render dashboard

2. **404 Errors on Refresh:**
   - Ensure `_redirects` file is in the root of your build output
   - Content: `/*    /index.html   200`

3. **Assets Not Loading:**
   - Check that all imports use relative paths
   - Ensure public assets are in the `public` folder

### 6. Automatic Deployments

Render automatically deploys when you push to your main branch:
```bash
git add .
git commit -m "Update portfolio"
git push origin main
```

Your site will be available at: `https://your-site-name.onrender.com`

## Alternative Deployment Options

### Vercel (Recommended for React)
1. Install Vercel CLI: `npm i -g vercel`
2. Run: `vercel` in your frontend directory
3. Follow the prompts

### Netlify
1. Drag and drop your `dist` folder to netlify.com
2. Or connect your GitHub repo for automatic deployments

## Performance Tips

1. **Optimize Images:** Use WebP format and proper sizing
2. **Code Splitting:** Vite handles this automatically
3. **Caching:** Render provides CDN caching automatically
4. **Lighthouse Score:** Test your deployed site with Google Lighthouse