import React from 'react';
import { useDarkMode } from './hooks/useDarkMode';
import Header from './components/layout/Header';
import Footer from './components/layout/Footer';
import Hero from './components/sections/Hero';
import About from './components/sections/About';
import Projects from './components/sections/Projects';
import Skills from './components/sections/Skills';
import Contact from './components/sections/Contact';
import './styles/index.css';

function App() {
  const [darkMode, toggleDarkMode] = useDarkMode();

  return (
    <div className={darkMode ? 'dark' : ''}>
      <div className="min-h-screen bg-white dark:bg-gray-900 text-gray-900 dark:text-white">
        <Header darkMode={darkMode} toggleDarkMode={toggleDarkMode} />
        
        <main>
          <section id="home">
            <Hero />
          </section>
          <About />
          <Projects />
          <Skills />
          <Contact />
        </main>
        
        <Footer />
      </div>
    </div>
  );
}

export default App;