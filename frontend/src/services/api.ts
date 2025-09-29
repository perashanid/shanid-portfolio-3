import axios from 'axios';

const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:10000/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Request interceptor
api.interceptors.request.use(
  (config) => {
    console.log(`Making ${config.method?.toUpperCase()} request to ${config.url}`);
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Response interceptor
api.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    console.error('API Error:', error.response?.data || error.message);
    return Promise.reject(error);
  }
);

export interface ContactFormData {
  name: string;
  email: string;
  message: string;
}

export interface PortfolioData {
  name: string;
  title: string;
  bio: string;
  skills: string[];
  projects: Array<{
    id: number;
    title: string;
    description: string;
    technologies: string[];
    github?: string;
    live?: string;
  }>;
  contact: {
    email: string;
    linkedin: string;
    github: string;
  };
}

// API endpoints
export const portfolioApi = {
  // Health check
  healthCheck: () => api.get('/health'),

  // Get portfolio data
  getPortfolio: (): Promise<{ data: PortfolioData }> => api.get('/portfolio'),

  // Submit contact form
  submitContact: (data: ContactFormData) => api.post('/contact', data),
};

export default api;