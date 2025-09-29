const express = require('express');
const cors = require('cors');
const helmet = require('helmet');
const path = require('path');
require('dotenv').config();

const app = express();
const PORT = process.env.PORT || 10000;

// Middleware
app.use(helmet({
  contentSecurityPolicy: false, // Disable CSP for serving static files
}));
app.use(cors({
  origin: process.env.NODE_ENV === 'production' 
    ? [process.env.FRONTEND_URL, /\.onrender\.com$/]
    : 'http://localhost:3000',
  credentials: true
}));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// Serve static files from frontend build
if (process.env.NODE_ENV === 'production') {
  const frontendPath = path.join(__dirname, '../frontend/dist');
  app.use(express.static(frontendPath));
  console.log(`📁 Serving static files from: ${frontendPath}`);
}

// Routes
app.get('/api/health', (req, res) => {
  res.json({ 
    status: 'OK', 
    message: 'Portfolio API is running',
    timestamp: new Date().toISOString()
  });
});

// Portfolio data endpoint
app.get('/api/portfolio', (req, res) => {
  res.json({
    name: 'Shanid Sajjatuz Islam',
    title: 'Full Stack Developer & Data Scientist',
    bio: 'Passionate developer with expertise in modern web technologies and data science.',
    skills: [
      'React', 'TypeScript', 'Node.js', 'Express.js',
      'Python', 'Machine Learning', 'Data Analysis',
      'PostgreSQL', 'MongoDB', 'AWS'
    ],
    projects: [
      {
        id: 1,
        title: 'Portfolio Website',
        description: 'Modern portfolio built with React and Express.js',
        technologies: ['React', 'Express.js', 'TypeScript'],
        github: 'https://github.com/username/portfolio',
        live: 'https://portfolio.example.com'
      }
    ],
    contact: {
      email: 'contact@example.com',
      linkedin: 'https://linkedin.com/in/username',
      github: 'https://github.com/username'
    }
  });
});

// Contact form endpoint
app.post('/api/contact', async (req, res) => {
  try {
    const { name, email, message } = req.body;
    
    // Basic validation
    if (!name || !email || !message) {
      return res.status(400).json({
        error: 'All fields are required'
      });
    }

    // Here you would typically send an email or save to database
    console.log('Contact form submission:', { name, email, message });
    
    res.json({
      success: true,
      message: 'Thank you for your message! I\'ll get back to you soon.'
    });
  } catch (error) {
    console.error('Contact form error:', error);
    res.status(500).json({
      error: 'Failed to send message. Please try again later.'
    });
  }
});

// Serve frontend for all non-API routes (SPA support)
if (process.env.NODE_ENV === 'production') {
  app.get('*', (req, res) => {
    // Don't serve index.html for API routes
    if (req.path.startsWith('/api/')) {
      return res.status(404).json({
        error: 'API route not found'
      });
    }
    const indexPath = path.join(__dirname, '../frontend/dist/index.html');
    console.log(`📄 Serving index.html from: ${indexPath}`);
    res.sendFile(indexPath);
  });
} else {
  // 404 handler for development
  app.use('*', (req, res) => {
    res.status(404).json({
      error: 'Route not found'
    });
  });
}

// Error handler
app.use((error, req, res, next) => {
  console.error('Server error:', error);
  res.status(500).json({
    error: 'Internal server error'
  });
});

app.listen(PORT, () => {
  console.log(`🚀 Server running on http://localhost:${PORT}`);
  console.log(`📊 Health check: http://localhost:${PORT}/api/health`);
});

module.exports = app;