import { Project, Skill, ContactInfo } from '../types';

export const projects: Project[] = [
  // Web Applications
  {
    id: 'survease',
    title: 'Survey Platform (Survease)',
    description: 'A comprehensive survey platform where users can create private or public surveys with advanced analytics. Features include anonymous responses, data export in CSV/JSON formats, and survey sharing via links.',
    technologies: ['React', 'Node.js', 'MongoDB', 'Express', 'Chart.js'],
    liveUrl: 'https://survease-v2-uppv.onrender.com',
    githubUrl: 'https://github.com/perashanid/survease_v2',
    images: ['/projects/survease.svg'],
    category: 'web-application',
    featured: true
  },
  {
    id: 'bookmarket',
    title: 'BookMarket',
    description: 'A marketplace for books where people can auction, trade, or sell books at fixed prices. Users can also share books using PDFs, creating a comprehensive book trading ecosystem.',
    technologies: ['React', 'Node.js', 'MongoDB', 'Express', 'Socket.io'],
    liveUrl: 'https://book-marketplace-bfo0.onrender.com',
    githubUrl: 'https://github.com/perashanid/Book-marketplace-v2',
    images: ['/projects/book-marketplace.svg'],
    category: 'web-application',
    featured: true
  },

  // Frontend Projects
  {
    id: 'campaign-builder',
    title: 'Campaign Builder',
    description: 'A platform for creating fundraising and blood donation campaigns. Users can create campaigns and share unique links to gather support for their causes.',
    technologies: ['React', 'TypeScript', 'Tailwind CSS', 'Vite'],
    liveUrl: 'https://campaignbuilder.onrender.com',
    githubUrl: 'https://github.com/perashanid/campaignBuilder',
    images: ['/projects/default.svg'],
    category: 'frontend'
  },
  {
    id: 'loading-terminal',
    title: 'Loading Terminal',
    description: 'An interactive terminal-style loading interface with realistic typing effects and command-line aesthetics.',
    technologies: ['HTML', 'CSS', 'JavaScript', 'Terminal UI'],
    liveUrl: 'https://loading-terminal.onrender.com',
    githubUrl: 'https://github.com/perashanid/loading-terminal',
    images: [],
    category: 'frontend'
  },

  // Backend Projects
  {
    id: 'bd-stock-api',
    title: 'BD Stock Market API',
    description: 'The only free API for Bangladesh stock market data. Provides real-time stock information and market data for developers and financial applications.',
    technologies: ['Node.js', 'Express', 'Web Scraping', 'REST API'],
    liveUrl: 'https://bd-stock-market-api.onrender.com',
    githubUrl: 'https://github.com/perashanid/bd-stock-market-api',
    images: ['/projects/bd-stock-api.svg'],
    category: 'backend',
    featured: true
  },
  {
    id: 'stock-market-bd',
    title: 'Stock Market BD',
    description: 'A comprehensive stock market platform for Bangladesh where users can view stock profiles, top 30 stocks, register accounts, create portfolios, and check portfolio performance.',
    technologies: ['Node.js', 'Express', 'MongoDB', 'JWT', 'Chart.js'],
    liveUrl: 'https://bangladesh-stock-market.onrender.com',
    githubUrl: 'https://github.com/perashanid/stock-market',
    images: [],
    category: 'backend'
  },

  // Data Science Projects
  {
    id: 'media-bias-detector',
    title: 'Media Bias Detector',
    description: 'An AI-powered platform that detects bias in media articles. Users can register, scrape articles by URL, run bias analysis, and save articles. Features customizable scraping parameters.',
    technologies: ['Python', 'Machine Learning', 'NLP', 'Flask', 'Web Scraping'],
    liveUrl: 'https://media-bias-a9x2.onrender.com',
    githubUrl: 'https://github.com/perashanid/Media-bias',
    images: ['/projects/default.svg'],
    category: 'data-science',
    featured: true
  },
  {
    id: 'research-paper-analyzer',
    title: 'Research Paper Analyzer',
    description: 'An AI-powered tool that analyzes research papers from links, PDFs, or URLs to extract key information. Powered by Gemini 2.5 Pro for advanced document understanding.',
    technologies: ['Python', 'Gemini AI', 'PDF Processing', 'NLP', 'Flask'],
    liveUrl: 'https://research-paper-analyzer-htn7.onrender.com',
    githubUrl: 'https://github.com/perashanid/ai-wrapper',
    images: [],
    category: 'data-science'
  },

  // Game Development
  {
    id: 'space-shooter-404',
    title: '404 Space Shooter',
    description: 'A creative 404 error page featuring an interactive space shooter game built entirely in the browser. Turn your 404 errors into an engaging gaming experience.',
    technologies: ['JavaScript', 'HTML5 Canvas', 'CSS3', 'Game Development'],
    liveUrl: 'https://four04-space-shooter.onrender.com',
    githubUrl: 'https://github.com/perashanid/404_space_shooter',
    images: [],
    category: 'game-development'
  },

  // Others
  {
    id: 'portfolio',
    title: 'Personal Portfolio',
    description: 'My personal portfolio website showcasing my projects, skills, and experience as a full-stack developer.',
    technologies: ['React', 'TypeScript', 'Tailwind CSS', 'Responsive Design'],
    liveUrl: 'https://portfolio-of-shanid.onrender.com',
    githubUrl: 'https://github.com/perashanid/Portfolio_v1',
    images: [],
    category: 'others'
  },
  {
    id: 'algotrader',
    title: 'AlgoTrader',
    description: 'A platform where users can create their own trading algorithms to compete with Wall Street. Features custom buy/sell constraints, stock categorization, and backtesting with historical US stock data.',
    technologies: ['React', 'Node.js', 'Financial APIs', 'Algorithm Trading'],
    liveUrl: 'https://algotrade-v1.onrender.com',
    githubUrl: 'https://github.com/perashanid/algotrade',
    images: [],
    category: 'others',
    featured: true
  },
  {
    id: 'calculator',
    title: 'Calculator App',
    description: 'A clean and functional calculator application with a modern interface and smooth animations.',
    technologies: ['JavaScript', 'HTML', 'CSS', 'Responsive Design'],
    liveUrl: 'https://basic-calculator-uwvp.onrender.com',
    githubUrl: 'https://github.com/perashanid/basic_calculator',
    images: [],
    category: 'others'
  },
  {
    id: 'maintenance-page',
    title: 'Under Maintenance Website',
    description: 'A professional under maintenance page template with modern design and animations.',
    technologies: ['HTML', 'CSS', 'JavaScript', 'Animation'],
    liveUrl: 'https://under-maintenance-website-page.onrender.com',
    githubUrl: 'https://github.com/perashanid/Under_maintenance_website_page',
    images: [],
    category: 'others'
  }
];

export const skills: Skill[] = [
  // Frontend
  { name: 'React', level: 90, category: 'frontend' },
  { name: 'TypeScript', level: 85, category: 'frontend' },
  { name: 'JavaScript', level: 95, category: 'frontend' },
  { name: 'HTML/CSS', level: 95, category: 'frontend' },
  { name: 'Tailwind CSS', level: 90, category: 'frontend' },
  { name: 'Vue.js', level: 75, category: 'frontend' },

  // Backend
  { name: 'Node.js', level: 90, category: 'backend' },
  { name: 'Express.js', level: 85, category: 'backend' },
  { name: 'Python', level: 85, category: 'backend' },
  { name: 'Flask', level: 80, category: 'backend' },


  // Database
  { name: 'MongoDB', level: 85, category: 'database' },
  { name: 'PostgreSQL', level: 80, category: 'database' },
  { name: 'MySQL', level: 75, category: 'database' },

  // Tools & Others
  { name: 'Git', level: 90, category: 'tools' },
  { name: 'Docker', level: 75, category: 'tools' },
  { name: 'AWS', level: 70, category: 'tools' },
  { name: 'Machine Learning', level: 80, category: 'other' },
  { name: 'Data Analysis', level: 85, category: 'other' },
];

export const contactInfo: ContactInfo = {
  email: 'shanidsajjatuz@gmail.com',
  location: 'Bangladesh',
  github: 'https://github.com/perashanid',
  website: 'https://shanid-cv.vercel.app'
};