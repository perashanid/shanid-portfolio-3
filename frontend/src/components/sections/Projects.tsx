import React, { useState } from 'react';
import { X, ExternalLink, Github, Calendar } from 'lucide-react';
import { Project } from '../../types';
import ProjectCard from '../ui/ProjectCard';

// Demo projects data from data.sql
const demoProjects: Project[] = [
  {
    id: 1,
    title: 'E-Commerce Platform',
    description: 'Full-stack e-commerce application with payment integration',
    detailedDescription: 'A comprehensive e-commerce platform built with Spring Boot and React. Features include user authentication, product catalog, shopping cart, order management, and Stripe payment integration. The application uses PostgreSQL for data persistence and is deployed on AWS with CI/CD pipeline.',
    category: 'Web Application',
    featured: true,
    createdDate: '2024-01-15',
    liveUrl: 'https://ecommerce-demo.com',
    repositoryUrl: 'https://github.com/johndoe/ecommerce-platform',
    technologies: ['Java', 'Spring Boot', 'React', 'TypeScript', 'PostgreSQL', 'AWS', 'Stripe API'],
    imageUrls: ['https://images.unsplash.com/photo-1556742049-0cfed4f6a45d?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80']
  },
  {
    id: 2,
    title: 'MERN Social Media App',
    description: 'Full-stack social media platform using MERN stack',
    detailedDescription: 'A modern social media application built with MongoDB, Express.js, React, and Node.js. Features include user authentication, real-time messaging, post creation with image uploads, likes and comments, friend system, and responsive design. Deployed on Heroku with MongoDB Atlas.',
    category: 'Web Application',
    featured: true,
    createdDate: '2024-02-20',
    liveUrl: 'https://mern-social-demo.com',
    repositoryUrl: 'https://github.com/johndoe/mern-social-app',
    technologies: ['MongoDB', 'Express.js', 'React', 'Node.js', 'Socket.io', 'JWT', 'Cloudinary', 'Heroku'],
    imageUrls: ['https://images.unsplash.com/photo-1611224923853-80b023f02d71?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80']
  },
  {
    id: 3,
    title: 'Next.js Portfolio Website',
    description: 'Modern portfolio website built with Next.js',
    detailedDescription: 'A sleek and responsive portfolio website showcasing projects and skills. Built with Next.js 14, TypeScript, and Tailwind CSS. Features server-side rendering, optimized images, dark mode, contact form with email integration, and deployed on Vercel with excellent performance scores.',
    category: 'Frontend',
    featured: true,
    createdDate: '2024-03-10',
    liveUrl: 'https://nextjs-portfolio-demo.vercel.app',
    repositoryUrl: 'https://github.com/johndoe/nextjs-portfolio',
    technologies: ['Next.js', 'TypeScript', 'Tailwind CSS', 'Vercel', 'Framer Motion', 'EmailJS'],
    imageUrls: ['https://images.unsplash.com/photo-1467232004584-a241de8bcf5d?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80']
  },
  {
    id: 4,
    title: 'Python Data Analytics Dashboard',
    description: 'Interactive dashboard for data visualization',
    detailedDescription: 'A comprehensive data analytics dashboard built with Python, Flask, and Plotly. Features include data processing with Pandas, interactive charts, real-time data updates, user authentication, and export functionality. Connects to multiple data sources including CSV, JSON, and databases.',
    category: 'Data Science',
    featured: false,
    createdDate: '2024-01-05',
    liveUrl: 'https://python-dashboard-demo.com',
    repositoryUrl: 'https://github.com/johndoe/python-dashboard',
    technologies: ['Python', 'Flask', 'Plotly', 'Pandas', 'SQLAlchemy', 'Bootstrap', 'Chart.js'],
    imageUrls: ['https://images.unsplash.com/photo-1551288049-bebda4e38f71?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80']
  },
  {
    id: 5,
    title: 'React Native Mobile App',
    description: 'Cross-platform mobile app for expense tracking',
    detailedDescription: 'A feature-rich expense tracking mobile application built with React Native and Expo. Includes user authentication, expense categorization, budget tracking, data visualization with charts, offline support, and cloud synchronization. Available for both iOS and Android platforms.',
    category: 'Mobile',
    featured: false,
    createdDate: '2023-12-15',
    repositoryUrl: 'https://github.com/johndoe/expense-tracker-mobile',
    technologies: ['React Native', 'Expo', 'AsyncStorage', 'React Navigation', 'Firebase', 'Chart.js'],
    imageUrls: ['https://images.unsplash.com/photo-1512941937669-90a1b58e7e9c?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80']
  },
  {
    id: 6,
    title: 'Spring Boot Microservices',
    description: 'Distributed system with Spring Cloud',
    detailedDescription: 'A microservices-based application demonstrating service discovery, API gateway, circuit breakers, and distributed tracing. Built with Spring Cloud, Docker, and Kubernetes. Includes monitoring with Prometheus and Grafana, centralized logging, and automated deployment pipelines.',
    category: 'Backend',
    featured: true,
    createdDate: '2023-11-05',
    repositoryUrl: 'https://github.com/johndoe/microservices-demo',
    technologies: ['Java', 'Spring Boot', 'Spring Cloud', 'Docker', 'Kubernetes', 'Prometheus', 'Grafana', 'RabbitMQ'],
    imageUrls: ['https://images.unsplash.com/photo-1558494949-ef010cbdcc31?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80']
  },
  {
    id: 7,
    title: 'JavaScript Game Engine',
    description: 'Browser-based 2D game engine',
    detailedDescription: 'A lightweight 2D game engine built with vanilla JavaScript and HTML5 Canvas. Features include sprite animation, collision detection, sound management, scene transitions, and a level editor. Includes sample games demonstrating the engine capabilities and comprehensive documentation.',
    category: 'Game Development',
    featured: false,
    createdDate: '2023-10-20',
    liveUrl: 'https://js-game-engine-demo.com',
    repositoryUrl: 'https://github.com/johndoe/js-game-engine',
    technologies: ['JavaScript', 'HTML5 Canvas', 'Web Audio API', 'CSS3', 'Webpack'],
    imageUrls: ['https://images.unsplash.com/photo-1493711662062-fa541adb3fc8?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80']
  },
  {
    id: 8,
    title: 'MongoDB Blog CMS',
    description: 'Content management system with MongoDB',
    detailedDescription: 'A modern blog CMS built with Node.js, Express, and MongoDB. Features include rich text editor, image uploads, user roles and permissions, SEO optimization, comment system, and admin dashboard. Supports multiple themes and plugins for extensibility.',
    category: 'CMS',
    featured: false,
    createdDate: '2023-09-25',
    liveUrl: 'https://mongodb-blog-demo.com',
    repositoryUrl: 'https://github.com/johndoe/mongodb-blog-cms',
    technologies: ['Node.js', 'Express.js', 'MongoDB', 'EJS', 'Multer', 'Passport.js', 'Bootstrap'],
    imageUrls: ['https://images.unsplash.com/photo-1486312338219-ce68d2c6f44d?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80']
  }
];

const Projects: React.FC = () => {
  const [selectedCategory, setSelectedCategory] = useState<string>('all');
  const [selectedProject, setSelectedProject] = useState<Project | null>(null);
  
  const projects = demoProjects;
  const categories = ['all', 'Web Application', 'Frontend', 'Backend', 'Mobile', 'Data Science', 'Game Development', 'CMS'];

  const filteredProjects = selectedCategory === 'all' 
    ? projects 
    : projects.filter(project => project.category === selectedCategory);

  const handleProjectClick = (project: Project) => {
    setSelectedProject(project);
  };

  const closeModal = () => {
    setSelectedProject(null);
  };



  return (
    <section id="projects" className="section-padding bg-light-sage/30 dark:bg-medium-gray">
      <div className="container-max">
        {/* Section Header */}
        <div className="text-center mb-12">
          <h2 className="text-3xl md:text-4xl font-bold mb-4 text-dark-charcoal dark:text-light-sage">My Projects</h2>
          <div className="w-20 h-1 bg-warm-brown mx-auto mb-8"></div>
          <p className="text-medium-gray dark:text-light-sage/80 max-w-2xl mx-auto">
            Here are some of the projects I've worked on. Each project represents a unique challenge and learning experience.
          </p>
        </div>

        {/* Category Filter */}
        <div className="flex flex-wrap justify-center gap-4 mb-12">
          {categories.map((category) => (
            <button
              key={category}
              onClick={() => setSelectedCategory(category)}
              className={`px-6 py-2 rounded-full font-medium transition-colors capitalize ${
                selectedCategory === category
                  ? 'bg-warm-brown text-light-sage'
                  : 'bg-white dark:bg-dark-charcoal text-medium-gray dark:text-light-sage hover:bg-warm-brown/10 dark:hover:bg-dark-charcoal/80'
              }`}
            >
              {category}
            </button>
          ))}
        </div>

        {/* Projects Grid */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
          {filteredProjects.map((project) => (
            <ProjectCard
              key={project.id}
              project={project}
              onClick={() => handleProjectClick(project)}
            />
          ))}
        </div>

        {filteredProjects.length === 0 && (
          <div className="text-center py-12">
            <p className="text-medium-gray dark:text-light-sage/70 text-lg">No projects found in this category.</p>
          </div>
        )}

        {/* Project Detail Modal */}
        {selectedProject && (
          <div className="fixed inset-0 bg-black/50 flex items-center justify-center p-4 z-50">
            <div className="bg-white dark:bg-medium-gray rounded-lg max-w-4xl w-full max-h-[90vh] overflow-y-auto">
              {/* Modal Header */}
              <div className="flex items-center justify-between p-6 border-b border-medium-gray/30 dark:border-light-sage/30">
                <h3 className="text-2xl font-bold text-dark-charcoal dark:text-light-sage">{selectedProject.title}</h3>
                <button
                  onClick={closeModal}
                  className="p-2 hover:bg-light-sage/20 dark:hover:bg-dark-charcoal rounded-full transition-colors text-medium-gray dark:text-light-sage"
                >
                  <X size={24} />
                </button>
              </div>

              {/* Modal Content */}
              <div className="p-6">
                {/* Project Image */}
                {selectedProject.imageUrls && selectedProject.imageUrls.length > 0 && (
                  <div className="mb-6">
                    <img
                      src={selectedProject.imageUrls[0]}
                      alt={selectedProject.title}
                      className="w-full h-64 object-cover rounded-lg"
                    />
                  </div>
                )}

                {/* Project Info */}
                <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
                  <div className="lg:col-span-2">
                    <h4 className="text-lg font-semibold mb-3 text-dark-charcoal dark:text-light-sage">Description</h4>
                    <p className="text-medium-gray dark:text-light-sage/80 leading-relaxed mb-6">
                      {selectedProject.detailedDescription || selectedProject.description}
                    </p>

                    <h4 className="text-lg font-semibold mb-3 text-dark-charcoal dark:text-light-sage">Technologies Used</h4>
                    <div className="flex flex-wrap gap-2 mb-6">
                      {selectedProject.technologies.map((tech, index) => (
                        <span
                          key={index}
                          className="px-3 py-1 bg-warm-brown/20 dark:bg-warm-brown/30 text-warm-brown rounded-full text-sm"
                        >
                          {tech}
                        </span>
                      ))}
                    </div>
                  </div>

                  <div>
                    <h4 className="text-lg font-semibold mb-3 text-dark-charcoal dark:text-light-sage">Project Details</h4>
                    <div className="space-y-3">
                      <div className="flex items-center gap-2 text-sm text-medium-gray dark:text-light-sage/80">
                        <Calendar size={16} />
                        {new Date(selectedProject.createdDate).toLocaleDateString()}
                      </div>
                      
                      <div className="text-sm text-medium-gray dark:text-light-sage/80">
                        <span className="font-medium">Category:</span>
                        <span className="ml-2 capitalize">{selectedProject.category}</span>
                      </div>

                      {selectedProject.featured && (
                        <div className="inline-flex items-center gap-1 px-2 py-1 bg-warm-brown/20 text-warm-brown rounded-full text-xs">
                          ⭐ Featured Project
                        </div>
                      )}
                    </div>

                    {/* Action Links */}
                    <div className="mt-6 space-y-3">
                      {selectedProject.liveUrl && (
                        <a
                          href={selectedProject.liveUrl}
                          target="_blank"
                          rel="noopener noreferrer"
                          className="flex items-center gap-2 btn-primary w-full justify-center"
                        >
                          <ExternalLink size={16} />
                          View Live Demo
                        </a>
                      )}
                      {selectedProject.repositoryUrl && (
                        <a
                          href={selectedProject.repositoryUrl}
                          target="_blank"
                          rel="noopener noreferrer"
                          className="flex items-center gap-2 btn-secondary w-full justify-center"
                        >
                          <Github size={16} />
                          View Source Code
                        </a>
                      )}
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        )}
      </div>
    </section>
  );
};

export default Projects;