import React from 'react';
import { skills } from '../../data/portfolio';
import { Skill } from '../../types';

const Skills: React.FC = () => {
  const skillCategories = [
    { id: 'frontend', name: 'Frontend', color: 'text-blue-600 dark:text-blue-400' },
    { id: 'backend', name: 'Backend', color: 'text-green-600 dark:text-green-400' },
    { id: 'database', name: 'Database', color: 'text-purple-600 dark:text-purple-400' },
    { id: 'tools', name: 'Tools & DevOps', color: 'text-orange-600 dark:text-orange-400' },
    { id: 'other', name: 'Other', color: 'text-red-600 dark:text-red-400' }
  ];

  const getSkillsByCategory = (category: string) => {
    return skills.filter(skill => skill.category === category);
  };

  return (
    <section id="skills" className="py-20 bg-gray-50 dark:bg-gray-800">
      <div className="max-w-7xl mx-auto px-4">
        {/* Header */}
        <div className="text-center mb-16">
          <h2 className="text-4xl lg:text-5xl font-bold text-gray-900 dark:text-white mb-6">
            Skills & Technologies
          </h2>
          <p className="text-xl text-gray-600 dark:text-gray-300 max-w-3xl mx-auto">
            A comprehensive overview of my technical skills and proficiency levels 
            across different domains of software development.
          </p>
        </div>

        {/* Skills Grid */}
        <div className="grid grid-cols-1 lg:grid-cols-2 xl:grid-cols-3 gap-8">
          {skillCategories.map((category, categoryIndex) => {
            const categorySkills = getSkillsByCategory(category.id);
            if (categorySkills.length === 0) return null;

            return (
              <div
                key={category.id}
                className="bg-white dark:bg-gray-900 rounded-xl shadow-lg p-6 animate-fade-in"
                style={{ animationDelay: `${categoryIndex * 0.1}s` }}
              >
                <h3 className={`text-2xl font-bold mb-6 ${category.color}`}>
                  {category.name}
                </h3>
                
                <div className="space-y-4">
                  {categorySkills.map((skill, skillIndex) => (
                    <SkillBar key={skillIndex} skill={skill} />
                  ))}
                </div>
              </div>
            );
          })}
        </div>

        {/* Additional Info */}
        <div className="mt-16 text-center">
          <div className="bg-white dark:bg-gray-900 rounded-xl shadow-lg p-8 max-w-4xl mx-auto">
            <h3 className="text-2xl font-bold text-gray-900 dark:text-white mb-4">
              Always Learning
            </h3>
            <p className="text-gray-600 dark:text-gray-300 leading-relaxed">
              Technology evolves rapidly, and I'm committed to continuous learning. 
              I regularly explore new frameworks, tools, and methodologies to stay 
              current with industry trends and best practices.
            </p>
          </div>
        </div>
      </div>
    </section>
  );
};

interface SkillBarProps {
  skill: Skill;
}

const SkillBar: React.FC<SkillBarProps> = ({ skill }) => {
  return (
    <div className="group">
      <div className="flex justify-between items-center mb-2">
        <span className="font-semibold text-gray-900 dark:text-white">
          {skill.name}
        </span>
        <span className="text-sm text-gray-500 dark:text-gray-400">
          {skill.level}%
        </span>
      </div>
      
      <div className="w-full bg-gray-200 dark:bg-gray-700 rounded-full h-3 overflow-hidden">
        <div
          className="h-full bg-gradient-to-r from-primary-500 to-primary-600 rounded-full transition-all duration-1000 ease-out group-hover:from-primary-400 group-hover:to-primary-500"
          style={{ 
            width: `${skill.level}%`,
            animation: 'fillBar 1.5s ease-out forwards'
          }}
        />
      </div>
    </div>
  );
};

export default Skills;