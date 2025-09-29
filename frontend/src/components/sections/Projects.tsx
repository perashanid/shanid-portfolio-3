import React, { useState } from 'react';
import { ExternalLink, Github, Filter } from 'lucide-react';
import { projects } from '../../data/portfolio';
import { Project } from '../../types';

const Projects: React.FC = () => {
  const [selectedCategory, setSelectedCategory] = useState<string>('all');
  const [showFeaturedOnly, setShowFeaturedOnly] = useState(false);

  const categories = [
    { id: 'all', name: 'All Projects' },
    { id: 'web-application', name: 'Web Applications' },
    { id: 'frontend', name: 'Frontend' },
    { id: 'backend', name: 'Backend' },
    { id: 'data-science', name: 'Data Science' },
    { id: 'game-development', name: 'Game Development' },
    { id: 'others', name: 'Others' }
  ];

  const filteredProjects = projects.filter(project => {
    const categoryMatch = selectedCategory === 'all' || project.category === selectedCategory;
    const featuredMatch = !showFeaturedOnly || project.featured;
    return categoryMatch && featuredMatch;
  });

  const getCategoryColor = (category: string) => {
    const colors = {
      'web-application': 'bg-blue-100 text-blue-800 dark:bg-blue-900 dark:text-blue-200',
      'frontend': 'bg-green-100 text-green-800 dark:bg-green-900 dark:text-green-200',
      'backend': 'bg-purple-100 text-purple-800 dark:bg-purple-900 dark:text-purple-200',
      'data-science': 'bg-orange-100 text-orange-800 dark:bg-orange-900 dark:text-orange-200',
      'game-development': 'bg-red-100 text-red-800 dark:bg-red-900 dark:text-red-200',
      'others': 'bg-gray-100 text-gray-800 dark:bg-gray-900 dark:text-gray-200'
    };
    return colors[category as keyof typeof colors] || colors.others;
  };

  return (
    <section id="projects" className="py-20 bg-white dark:bg-gray-900">
      <div className="max-w-7xl mx-auto px-4">
        {/* Header */}
        <div className="text-center mb-16">
          <h2 className="text-4xl lg:text-5xl font-bold text-gray-900 dark:text-white mb-6">
            My Projects
          </h2>
          <p className="text-xl text-gray-600 dark:text-gray-300 max-w-3xl mx-auto">
            A collection of projects showcasing my skills in full-stack development, 
            data science, and innovative problem-solving.
          </p>
        </div>

        {/* Filters */}
        <div className="mb-12">
          <div className="flex flex-wrap gap-4 justify-center mb-6">
            {categories.map(category => (
              <button
                key={category.id}
                onClick={() => setSelectedCategory(category.id)}
                className={`px-6 py-3 rounded-full font-semibold transition-all duration-200 ${
                  selectedCategory === category.id
                    ? 'bg-primary-600 text-white shadow-lg'
                    : 'bg-gray-100 dark:bg-gray-800 text-gray-700 dark:text-gray-300 hover:bg-gray-200 dark:hover:bg-gray-700'
                }`}
              >
                {category.name}
              </button>
            ))}
          </div>

          <div className="flex justify-center">
            <button
              onClick={() => setShowFeaturedOnly(!showFeaturedOnly)}
              className={`flex items-center gap-2 px-4 py-2 rounded-lg font-medium transition-all duration-200 ${
                showFeaturedOnly
                  ? 'bg-yellow-100 text-yellow-800 dark:bg-yellow-900 dark:text-yellow-200'
                  : 'bg-gray-100 dark:bg-gray-800 text-gray-700 dark:text-gray-300 hover:bg-gray-200 dark:hover:bg-gray-700'
              }`}
            >
              <Filter size={18} />
              {showFeaturedOnly ? 'Show All' : 'Featured Only'}
            </button>
          </div>
        </div>

        {/* Projects Grid */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
          {filteredProjects.map((project, index) => (
            <ProjectCard key={project.id} project={project} index={index} getCategoryColor={getCategoryColor} />
          ))}
        </div>

        {filteredProjects.length === 0 && (
          <div className="text-center py-12">
            <p className="text-xl text-gray-500 dark:text-gray-400">
              No projects found for the selected filters.
            </p>
          </div>
        )}
      </div>
    </section>
  );
};

interface ProjectCardProps {
  project: Project;
  index: number;
  getCategoryColor: (category: string) => string;
}

const ProjectCard: React.FC<ProjectCardProps> = ({ project, index, getCategoryColor }) => {
  return (
    <div 
      className="bg-white dark:bg-gray-800 rounded-xl shadow-lg hover:shadow-2xl transition-all duration-300 overflow-hidden group animate-fade-in"
      style={{ animationDelay: `${index * 0.1}s` }}
    >
      {/* Project Header */}
      <div className="p-6">
        <div className="flex items-start justify-between mb-4">
          <div className="flex-1">
            <h3 className="text-xl font-bold text-gray-900 dark:text-white mb-2 group-hover:text-primary-600 dark:group-hover:text-primary-400 transition-colors">
              {project.title}
            </h3>
            <span className={`inline-block px-3 py-1 rounded-full text-sm font-medium ${getCategoryColor(project.category)}`}>
              {project.category.replace('-', ' ').replace(/\b\w/g, l => l.toUpperCase())}
            </span>
          </div>
          {project.featured && (
            <div className="ml-4">
              <span className="inline-block px-2 py-1 bg-yellow-100 text-yellow-800 dark:bg-yellow-900 dark:text-yellow-200 text-xs font-bold rounded-full">
                ⭐ Featured
              </span>
            </div>
          )}
        </div>

        <p className="text-gray-600 dark:text-gray-300 mb-6 leading-relaxed">
          {project.description}
        </p>

        {/* Technologies */}
        <div className="mb-6">
          <div className="flex flex-wrap gap-2">
            {project.technologies.map((tech, techIndex) => (
              <span
                key={techIndex}
                className="px-3 py-1 bg-gray-100 dark:bg-gray-700 text-gray-700 dark:text-gray-300 text-sm rounded-full"
              >
                {tech}
              </span>
            ))}
          </div>
        </div>

        {/* Action Buttons */}
        <div className="flex gap-3">
          {project.liveUrl && (
            <a
              href={project.liveUrl}
              target="_blank"
              rel="noopener noreferrer"
              className="flex-1 bg-primary-600 hover:bg-primary-700 text-white px-4 py-2 rounded-lg font-semibold transition-colors duration-200 flex items-center justify-center gap-2"
            >
              <ExternalLink size={16} />
              Live Demo
            </a>
          )}
          {project.githubUrl && (
            <a
              href={project.githubUrl}
              target="_blank"
              rel="noopener noreferrer"
              className="flex-1 border-2 border-gray-300 dark:border-gray-600 text-gray-700 dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-700 px-4 py-2 rounded-lg font-semibold transition-all duration-200 flex items-center justify-center gap-2"
            >
              <Github size={16} />
              Code
            </a>
          )}
        </div>
      </div>
    </div>
  );
};

export default Projects;