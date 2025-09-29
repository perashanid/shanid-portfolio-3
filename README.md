# Shanid Sajjatuz Islam - Portfolio Website

A modern, responsive portfolio website showcasing my projects, skills, and experience as a Full-Stack Developer and Data Scientist. Built with Express.js backend and React frontend.

## Features

- **Responsive Design**: Works seamlessly across desktop, tablet, and mobile devices
- **Project Showcase**: Display projects with filtering by category and technology
- **Skills Visualization**: Interactive skill bars with proficiency levels
- **Contact Form**: Functional contact form with email notifications
- **RESTful API**: Clean Express.js APIs for portfolio data
- **Modern Tech Stack**: Express.js + React 18 + TypeScript + Tailwind CSS

## Tech Stack

### Backend
- Node.js 18+
- Express.js 4.x
- CORS for cross-origin requests
- Helmet for security
- Nodemailer for email functionality
- Environment variables with dotenv

### Frontend
- React 18+
- TypeScript
- Tailwind CSS
- Axios for API calls
- React Router for navigation
- React Hook Form for form handling
- Lucide React for icons

## Getting Started

### Prerequisites
- Node.js 18 or higher
- npm or yarn

### Quick Start

1. Install all dependencies:
   ```bash
   npm run install:all
   ```

2. Start both backend and frontend:
   ```bash
   npm run dev
   ```

   - Backend will start on `http://localhost:8080`
   - Frontend will start on `http://localhost:3000`

### Individual Setup

#### Backend Setup

1. Navigate to the backend directory:
   ```bash
   cd backend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm run dev
   ```

#### Frontend Setup

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm run dev
   ```

## API Endpoints

### Health Check
- `GET /api/health` - Check API status

### Portfolio Data
- `GET /api/portfolio` - Get complete portfolio information including projects, skills, and contact info

### Contact
- `POST /api/contact` - Submit contact form
  ```json
  {
    "name": "John Doe",
    "email": "john@example.com", 
    "message": "Hello, I'd like to discuss a project..."
  }
  ```

## Project Structure

```
portfolio-website/
├── backend/                # Express.js application
│   ├── server.js          # Main server file
│   ├── package.json       # Backend dependencies
│   ├── .env               # Environment variables
│   └── .env.example       # Environment template
├── frontend/              # React application
│   ├── src/
│   │   ├── components/    # React components
│   │   ├── services/      # API service functions
│   │   ├── types/         # TypeScript interfaces
│   │   ├── data/          # Static data
│   │   └── styles/        # CSS and styling
│   ├── public/           # Static assets
│   ├── .env              # Frontend environment variables
│   └── .env.example      # Environment template
├── package.json          # Root package.json for scripts
└── .kiro/               # Kiro configuration and specs
```

## Configuration

### Backend Configuration
The backend uses environment variables for configuration:

```bash
PORT=8080
FRONTEND_URL=http://localhost:3000
NODE_ENV=development

# Email configuration (optional)
EMAIL_HOST=smtp.gmail.com
EMAIL_PORT=587
EMAIL_USER=your-email@gmail.com
EMAIL_PASS=your-app-password
```

### Frontend Configuration
The frontend uses Vite with environment variables:

```bash
VITE_API_URL=http://localhost:8080/api
```

## Customization

### Adding Your Information
1. Update the portfolio data in `backend/server.js`
2. Modify the static data in `frontend/src/data/portfolio.ts`

### Styling
- Modify `frontend/tailwind.config.js` for theme customization
- Update component styles in the respective component files

### Email Configuration
Update the email settings in `backend/.env`:
```bash
EMAIL_HOST=smtp.gmail.com
EMAIL_PORT=587
EMAIL_USER=your-email@gmail.com
EMAIL_PASS=your-app-password
```

## Deployment

### Quick Deployment to Render

1. **Prepare for deployment:**
   ```bash
   # Windows
   deploy.bat
   
   # Linux/Mac
   ./deploy.sh
   ```

2. **Follow the detailed guide:**
   - See `DEPLOYMENT_GUIDE.md` for complete step-by-step instructions
   - Use `DEPLOYMENT_CHECKLIST.md` for form field values

### Manual Deployment Steps

#### Backend (Web Service)
1. Create new Web Service on Render
2. Connect your GitHub repository
3. Configure:
   - Root Directory: `backend`
   - Build Command: `npm install`
   - Start Command: `npm start`
   - Environment Variables:
     - `NODE_ENV=production`
     - `PORT=10000`
     - `FRONTEND_URL=https://your-frontend-url.onrender.com`

#### Frontend (Static Site)
1. Create new Static Site on Render
2. Connect same GitHub repository
3. Configure:
   - Root Directory: `frontend`
   - Build Command: `npm install && npm run build`
   - Publish Directory: `dist`
   - Environment Variables:
     - `VITE_API_URL=https://your-backend-url.onrender.com/api`

### Environment Variables
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

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🚀 Live Demo

Visit the live website: [Portfolio Website](https://portfolio-of-shanid.onrender.com)

## 📞 Contact

- **Email**: [shanidsajjatuz@gmail.com](mailto:shanidsajjatuz@gmail.com)
- **GitHub**: [github.com/perashanid](https://github.com/perashanid)
- **CV**: [shanid-cv.vercel.app](https://shanid-cv.vercel.app)
- **Location**: Bangladesh

---

**Made with ❤️ and lots of coffee by Shanid Sajjatuz Islam**