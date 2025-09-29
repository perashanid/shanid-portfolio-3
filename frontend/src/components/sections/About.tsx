import React from 'react';
import { Code, Database, Brain, Rocket, Users, Award } from 'lucide-react';

const About: React.FC = () => {
  const highlights = [
    {
      icon: <Code className="w-8 h-8" />,
      title: 'Full-Stack Development',
      description: 'Proficient in both frontend and backend technologies, creating end-to-end solutions.'
    },
    {
      icon: <Database className="w-8 h-8" />,
      title: 'Data Science & AI',
      description: 'Experienced in machine learning, NLP, and building AI-powered applications.'
    },
    {
      icon: <Brain className="w-8 h-8" />,
      title: 'Algorithm Trading',
      description: 'Developed sophisticated trading algorithms and financial analysis tools.'
    },
    {
      icon: <Rocket className="w-8 h-8" />,
      title: 'Innovation Focus',
      description: 'Always exploring new technologies and creating innovative solutions to complex problems.'
    },
    {
      icon: <Users className="w-8 h-8" />,
      title: 'User-Centric Design',
      description: 'Building applications with excellent user experience and intuitive interfaces.'
    },
    {
      icon: <Award className="w-8 h-8" />,
      title: 'Quality Delivery',
      description: 'Committed to delivering high-quality, scalable, and maintainable code.'
    }
  ];

  return (
    <section id="about" className="py-20 bg-white dark:bg-gray-900">
      <div className="max-w-7xl mx-auto px-4">
        {/* Header */}
        <div className="text-center mb-16">
          <h2 className="text-4xl lg:text-5xl font-bold text-gray-900 dark:text-white mb-6">
            About Me
          </h2>
          <p className="text-xl text-gray-600 dark:text-gray-300 max-w-3xl mx-auto">
            Passionate developer with a love for creating innovative solutions 
            that make a real impact.
          </p>
        </div>

        <div className="grid grid-cols-1 lg:grid-cols-2 gap-16 items-center">
          {/* Text Content */}
          <div className="animate-fade-in">
            <h3 className="text-3xl font-bold text-gray-900 dark:text-white mb-6">
              Building the Future, One Line of Code at a Time
            </h3>
            
            <div className="space-y-6 text-gray-600 dark:text-gray-300 leading-relaxed">
              <p>
                I'm Shanid Sajjatuz Islam, a passionate full-stack developer and data scientist 
                from Bangladesh. My journey in technology began with a curiosity about how 
                things work and evolved into a deep passion for creating innovative solutions 
                that solve real-world problems.
              </p>
              
              <p>
                With expertise spanning from frontend frameworks like React to backend 
                technologies like Node.js and Python, I enjoy building complete applications 
                from concept to deployment. My experience in data science and machine learning 
                allows me to create intelligent applications that provide valuable insights.
              </p>
              
              <p>
                I've developed everything from trading algorithms that compete with Wall Street 
                to AI-powered research paper analyzers. Each project teaches me something new 
                and pushes me to explore the boundaries of what's possible with technology.
              </p>
              
              <p>
                When I'm not coding, I'm usually learning about new technologies, contributing 
                to open-source projects, or working on innovative ideas that could make a 
                difference in people's lives.
              </p>
            </div>

            <div className="mt-8">
              <a
                href="https://shanid-cv.vercel.app"
                target="_blank"
                rel="noopener noreferrer"
                className="inline-block bg-primary-600 hover:bg-primary-700 text-white px-8 py-3 rounded-lg font-semibold transition-colors duration-200 shadow-lg hover:shadow-xl"
              >
                View Full CV
              </a>
            </div>
          </div>

          {/* Highlights Grid */}
          <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
            {highlights.map((highlight, index) => (
              <div
                key={index}
                className="bg-gray-50 dark:bg-gray-800 p-6 rounded-xl hover:shadow-lg transition-all duration-300 animate-slide-up"
                style={{ animationDelay: `${index * 0.1}s` }}
              >
                <div className="text-primary-600 dark:text-primary-400 mb-4">
                  {highlight.icon}
                </div>
                <h4 className="text-lg font-semibold text-gray-900 dark:text-white mb-2">
                  {highlight.title}
                </h4>
                <p className="text-gray-600 dark:text-gray-300 text-sm leading-relaxed">
                  {highlight.description}
                </p>
              </div>
            ))}
          </div>
        </div>

        {/* Stats Section */}
        <div className="mt-20 grid grid-cols-2 md:grid-cols-4 gap-8">
          {[
            { number: '15+', label: 'Projects Completed' },
            { number: '5+', label: 'Technologies Mastered' },
            { number: '2+', label: 'Years Experience' },
            { number: '100%', label: 'Passion for Code' }
          ].map((stat, index) => (
            <div
              key={index}
              className="text-center animate-fade-in"
              style={{ animationDelay: `${index * 0.2}s` }}
            >
              <div className="text-4xl lg:text-5xl font-bold text-primary-600 dark:text-primary-400 mb-2">
                {stat.number}
              </div>
              <div className="text-gray-600 dark:text-gray-300 font-medium">
                {stat.label}
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default About;