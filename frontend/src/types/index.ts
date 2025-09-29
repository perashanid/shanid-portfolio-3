export interface Project {
  id: string;
  title: string;
  description: string;
  technologies: string[];
  liveUrl?: string;
  githubUrl?: string;
  images: string[];
  category: 'web-application' | 'frontend' | 'backend' | 'data-science' | 'game-development' | 'others';
  featured?: boolean;
}

export interface Experience {
  id: string;
  title: string;
  company: string;
  duration: string;
  description: string[];
  technologies: string[];
}

export interface Skill {
  name: string;
  level: number;
  category: 'frontend' | 'backend' | 'database' | 'tools' | 'other';
}

export interface ContactInfo {
  email: string;
  phone?: string;
  location: string;
  linkedin?: string;
  github?: string;
  website?: string;
}