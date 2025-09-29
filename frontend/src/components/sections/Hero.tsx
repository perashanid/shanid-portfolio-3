import React from 'react';
import { Github, ExternalLink, Mail, MapPin } from 'lucide-react';
import { contactInfo } from '../../data/portfolio';

const Hero: React.FC = () => {
  return (
    <section className="min-h-screen flex items-center justify-center bg-gradient-to-br from-primary-50 to-primary-100 dark:from-gray-900 dark:to-gray-800 px-4">
      <div className="max-w-6xl mx-auto grid grid-cols-1 lg:grid-cols-2 gap-12 items-center">
        {/* Text Content */}
        <div className="text-center lg:text-left animate-fade-in">
          <h1 className="text-5xl lg:text-7xl font-bold text-gray-900 dark:text-white mb-6">
            <span className="block">Shanid</span>
            <span className="block text-primary-600 dark:text-primary-400">
              Sajjatuz Islam
            </span>
          </h1>
          
          <p className="text-xl lg:text-2xl text-gray-600 dark:text-gray-300 mb-8 leading-relaxed">
            Full-Stack Developer & Data Scientist
          </p>
          
          <p className="text-lg text-gray-500 dark:text-gray-400 mb-8 max-w-2xl">
            Passionate about creating innovative web applications, trading algorithms, 
            and AI-powered solutions. I build scalable applications that solve real-world problems.
          </p>

          {/* Contact Info */}
          <div className="flex flex-wrap gap-4 justify-center lg:justify-start mb-8">
            <div className="flex items-center gap-2 text-gray-600 dark:text-gray-300">
              <Mail size={18} />
              <span>{contactInfo.email}</span>
            </div>
            <div className="flex items-center gap-2 text-gray-600 dark:text-gray-300">
              <MapPin size={18} />
              <span>{contactInfo.location}</span>
            </div>
          </div>

          {/* Action Buttons */}
          <div className="flex flex-wrap gap-4 justify-center lg:justify-start">
            <a
              href="#projects"
              className="bg-primary-600 hover:bg-primary-700 text-white px-8 py-3 rounded-lg font-semibold transition-colors duration-200 shadow-lg hover:shadow-xl"
            >
              View My Work
            </a>
            
            <a
              href={contactInfo.website}
              target="_blank"
              rel="noopener noreferrer"
              className="border-2 border-primary-600 text-primary-600 hover:bg-primary-600 hover:text-white px-8 py-3 rounded-lg font-semibold transition-all duration-200 flex items-center gap-2"
            >
              <ExternalLink size={18} />
              View CV
            </a>
            
            <a
              href={contactInfo.github}
              target="_blank"
              rel="noopener noreferrer"
              className="border-2 border-gray-300 dark:border-gray-600 text-gray-700 dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-700 px-8 py-3 rounded-lg font-semibold transition-all duration-200 flex items-center gap-2"
            >
              <Github size={18} />
              GitHub
            </a>
          </div>
        </div>

        {/* Profile Image */}
        <div className="flex justify-center lg:justify-end animate-slide-up">
          <div className="relative">
            <div className="w-80 h-80 lg:w-96 lg:h-96 rounded-full overflow-hidden shadow-2xl border-8 border-white dark:border-gray-700">
              <img
                src="/profile.jpg"
                alt="Shanid Sajjatuz Islam"
                className="w-full h-full object-cover"
              />
            </div>
            
            {/* Floating Elements */}
            <div className="absolute -top-4 -right-4 w-20 h-20 bg-primary-500 rounded-full flex items-center justify-center shadow-lg animate-bounce-slow">
              <span className="text-white font-bold text-sm">Full-Stack</span>
            </div>
            
            <div className="absolute -bottom-4 -left-4 w-16 h-16 bg-green-500 rounded-full flex items-center justify-center shadow-lg animate-bounce-slow" style={{ animationDelay: '0.5s' }}>
              <span className="text-white font-bold text-xs">AI/ML</span>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Hero;