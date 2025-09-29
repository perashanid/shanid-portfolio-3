import { ExternalLink, Github, Star } from 'lucide-react';
import { Project } from '../../types';

interface ProjectCardProps {
  project: Project;
  onClick: () => void;
}

const ProjectCard: React.FC<ProjectCardProps> = ({ project, onClick }) => {
  return (
    <div className="card p-6 cursor-pointer group" onClick={onClick}>
      {/* Project Image */}
      <div className="relative mb-4 overflow-hidden rounded-lg">
        {project.images && project.images.length > 0 ? (
          <img
            src={project.images[0]}
            alt={project.title}
            className="w-full h-48 object-cover group-hover:scale-105 transition-transform duration-300"
          />
        ) : (
          <div className="w-full h-48 bg-gradient-to-br from-light-sage/50 to-light-sage/30 dark:from-medium-gray dark:to-dark-charcoal flex items-center justify-center">
            <span className="text-warm-brown font-semibold text-lg">
              {project.title}
            </span>
          </div>
        )}
        
        {/* Featured Badge */}
        {project.featured && (
          <div className="absolute top-2 right-2 bg-yellow-500 text-white px-2 py-1 rounded-full flex items-center gap-1 text-xs font-medium">
            <Star size={12} fill="currentColor" />
            Featured
          </div>
        )}
      </div>

      {/* Project Info */}
      <div className="space-y-3">
        <div>
          <h3 className="text-xl font-semibold text-dark-charcoal dark:text-light-sage group-hover:text-warm-brown transition-colors">
            {project.title}
          </h3>
          <p className="text-sm text-medium-gray dark:text-light-sage/70 capitalize">{project.category}</p>
        </div>

        <p className="text-medium-gray dark:text-light-sage/80 text-sm leading-relaxed overflow-hidden" style={{
          display: '-webkit-box',
          WebkitLineClamp: 3,
          WebkitBoxOrient: 'vertical'
        }}>
          {project.description}
        </p>

        {/* Technologies */}
        <div className="flex flex-wrap gap-2">
          {project.technologies.slice(0, 4).map((tech, index) => (
            <span
              key={index}
              className="px-2 py-1 bg-warm-brown/20 dark:bg-warm-brown/30 text-warm-brown text-xs rounded-full"
            >
              {tech}
            </span>
          ))}
          {project.technologies.length > 4 && (
            <span className="px-2 py-1 bg-light-sage/50 dark:bg-medium-gray text-medium-gray dark:text-light-sage text-xs rounded-full">
              +{project.technologies.length - 4} more
            </span>
          )}
        </div>

        {/* Action Links */}
        <div className="flex items-center justify-between pt-2">
          <button
            onClick={(e) => {
              e.stopPropagation();
              onClick();
            }}
            className="text-warm-brown hover:text-warm-brown/80 font-medium text-sm transition-colors"
          >
            View Details →
          </button>
          
          <div className="flex items-center gap-2">
            {project.liveUrl && (
              <a
                href={project.liveUrl}
                target="_blank"
                rel="noopener noreferrer"
                onClick={(e) => e.stopPropagation()}
                className="p-2 text-medium-gray dark:text-light-sage/70 hover:text-warm-brown transition-colors"
                title="Live Demo"
              >
                <ExternalLink size={16} />
              </a>
            )}
            {project.githubUrl && (
              <a
                href={project.githubUrl}
                target="_blank"
                rel="noopener noreferrer"
                onClick={(e) => e.stopPropagation()}
                className="p-2 text-medium-gray dark:text-light-sage/70 hover:text-warm-brown transition-colors"
                title="Source Code"
              >
                <Github size={16} />
              </a>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProjectCard;