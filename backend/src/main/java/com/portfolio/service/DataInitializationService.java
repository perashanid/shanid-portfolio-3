package com.portfolio.service;

import com.portfolio.model.Project;
import com.portfolio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class DataInitializationService implements CommandLineRunner {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void run(String... args) throws Exception {
        if (projectRepository.count() == 0) {
            initializeProjects();
        }
    }

    private void initializeProjects() {
        List<Project> projects = Arrays.asList(
            // Web Applications
            new Project(
                "survease",
                "Survey Platform (Survease)",
                "A comprehensive survey platform where users can create private or public surveys with advanced analytics. Features include anonymous responses, data export in CSV/JSON formats, and survey sharing via links.",
                Arrays.asList("React", "Node.js", "MongoDB", "Express", "Chart.js"),
                "https://survease-v2-uppv.onrender.com",
                "https://github.com/perashanid/survease_v2",
                Arrays.asList(),
                Project.ProjectCategory.WEB_APPLICATION,
                true
            ),
            new Project(
                "bookmarket",
                "BookMarket",
                "A marketplace for books where people can auction, trade, or sell books at fixed prices. Users can also share books using PDFs, creating a comprehensive book trading ecosystem.",
                Arrays.asList("React", "Node.js", "MongoDB", "Express", "Socket.io"),
                "https://book-marketplace-bfo0.onrender.com",
                "https://github.com/perashanid/Book-marketplace-v2",
                Arrays.asList(),
                Project.ProjectCategory.WEB_APPLICATION,
                true
            ),

            // Frontend Projects
            new Project(
                "campaign-builder",
                "Campaign Builder",
                "A platform for creating fundraising and blood donation campaigns. Users can create campaigns and share unique links to gather support for their causes.",
                Arrays.asList("React", "TypeScript", "Tailwind CSS", "Vite"),
                "https://campaignbuilder.onrender.com",
                "https://github.com/perashanid/campaignBuilder",
                Arrays.asList(),
                Project.ProjectCategory.FRONTEND,
                false
            ),
            new Project(
                "loading-terminal",
                "Loading Terminal",
                "An interactive terminal-style loading interface with realistic typing effects and command-line aesthetics.",
                Arrays.asList("HTML", "CSS", "JavaScript", "Terminal UI"),
                "https://loading-terminal.onrender.com",
                "https://github.com/perashanid/loading-terminal",
                Arrays.asList(),
                Project.ProjectCategory.FRONTEND,
                false
            ),

            // Backend Projects
            new Project(
                "bd-stock-api",
                "BD Stock Market API",
                "The only free API for Bangladesh stock market data. Provides real-time stock information and market data for developers and financial applications.",
                Arrays.asList("Node.js", "Express", "Web Scraping", "REST API"),
                "https://bd-stock-market-api.onrender.com",
                "https://github.com/perashanid/bd-stock-market-api",
                Arrays.asList(),
                Project.ProjectCategory.BACKEND,
                true
            ),
            new Project(
                "stock-market-bd",
                "Stock Market BD",
                "A comprehensive stock market platform for Bangladesh where users can view stock profiles, top 30 stocks, register accounts, create portfolios, and check portfolio performance.",
                Arrays.asList("Node.js", "Express", "MongoDB", "JWT", "Chart.js"),
                "https://bangladesh-stock-market.onrender.com",
                "https://github.com/perashanid/stock-market",
                Arrays.asList(),
                Project.ProjectCategory.BACKEND,
                false
            ),

            // Data Science Projects
            new Project(
                "media-bias-detector",
                "Media Bias Detector",
                "An AI-powered platform that detects bias in media articles. Users can register, scrape articles by URL, run bias analysis, and save articles. Features customizable scraping parameters.",
                Arrays.asList("Python", "Machine Learning", "NLP", "Flask", "Web Scraping"),
                "https://media-bias-a9x2.onrender.com",
                "https://github.com/perashanid/Media-bias",
                Arrays.asList(),
                Project.ProjectCategory.DATA_SCIENCE,
                true
            ),
            new Project(
                "research-paper-analyzer",
                "Research Paper Analyzer",
                "An AI-powered tool that analyzes research papers from links, PDFs, or URLs to extract key information. Powered by Gemini 2.5 Pro for advanced document understanding.",
                Arrays.asList("Python", "Gemini AI", "PDF Processing", "NLP", "Flask"),
                "https://research-paper-analyzer-htn7.onrender.com",
                "https://github.com/perashanid/ai-wrapper",
                Arrays.asList(),
                Project.ProjectCategory.DATA_SCIENCE,
                false
            ),

            // Game Development
            new Project(
                "space-shooter-404",
                "404 Space Shooter",
                "A creative 404 error page featuring an interactive space shooter game built entirely in the browser. Turn your 404 errors into an engaging gaming experience.",
                Arrays.asList("JavaScript", "HTML5 Canvas", "CSS3", "Game Development"),
                "https://four04-space-shooter.onrender.com",
                "https://github.com/perashanid/404_space_shooter",
                Arrays.asList(),
                Project.ProjectCategory.GAME_DEVELOPMENT,
                false
            ),

            // Others
            new Project(
                "portfolio",
                "Personal Portfolio",
                "My personal portfolio website showcasing my projects, skills, and experience as a full-stack developer.",
                Arrays.asList("React", "TypeScript", "Tailwind CSS", "Responsive Design"),
                "https://portfolio-of-shanid.onrender.com",
                "https://github.com/perashanid/Portfolio_v1",
                Arrays.asList(),
                Project.ProjectCategory.OTHERS,
                false
            ),
            new Project(
                "algotrader",
                "AlgoTrader",
                "A platform where users can create their own trading algorithms to compete with Wall Street. Features custom buy/sell constraints, stock categorization, and backtesting with historical US stock data.",
                Arrays.asList("React", "Node.js", "Financial APIs", "Algorithm Trading"),
                "https://algotrade-v1.onrender.com",
                "https://github.com/perashanid/algotrade",
                Arrays.asList(),
                Project.ProjectCategory.OTHERS,
                true
            ),
            new Project(
                "calculator",
                "Calculator App",
                "A clean and functional calculator application with a modern interface and smooth animations.",
                Arrays.asList("JavaScript", "HTML", "CSS", "Responsive Design"),
                "https://basic-calculator-uwvp.onrender.com",
                "https://github.com/perashanid/basic_calculator",
                Arrays.asList(),
                Project.ProjectCategory.OTHERS,
                false
            ),
            new Project(
                "maintenance-page",
                "Under Maintenance Website",
                "A professional under maintenance page template with modern design and animations.",
                Arrays.asList("HTML", "CSS", "JavaScript", "Animation"),
                "https://under-maintenance-website-page.onrender.com",
                "https://github.com/perashanid/Under_maintenance_website_page",
                Arrays.asList(),
                Project.ProjectCategory.OTHERS,
                false
            )
        );

        projectRepository.saveAll(projects);
        System.out.println("Initialized " + projects.size() + " projects in the database.");
    }
}