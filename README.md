# Portfolio Website

A modern, responsive portfolio website built with Spring Boot backend and React frontend. Features a professional design with project showcase, skills visualization, and contact functionality.

## Features

- **Responsive Design**: Works seamlessly across desktop, tablet, and mobile devices
- **Project Showcase**: Display projects with filtering by category and technology
- **Skills Visualization**: Interactive skill bars with proficiency levels
- **Contact Form**: Functional contact form with email notifications
- **Admin API**: RESTful APIs for managing portfolio content
- **Modern Tech Stack**: Spring Boot 3.x + React 18 + TypeScript + Tailwind CSS

## Tech Stack

### Backend
- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- Spring Web
- Spring Boot Validation
- Spring Boot Mail
- H2 Database (development) / PostgreSQL (production)

### Frontend
- React 18+
- TypeScript
- Tailwind CSS
- Axios for API calls
- React Router for navigation
- React Hook Form for form handling
- Lucide React for icons

## Getting Started

### Prerequisites
- Java 17 or higher
- Node.js 16 or higher
- npm or yarn

### Backend Setup

1. Navigate to the backend directory:
   ```bash
   cd backend
   ```

2. Run the Spring Boot application:
   ```bash
   ./mvnw spring-boot:run
   ```

   The backend will start on `http://localhost:8080`

3. Access H2 Console (optional):
   - URL: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:portfoliodb`
   - Username: `sa`
   - Password: `password`

### Frontend Setup

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm run dev
   ```

   The frontend will start on `http://localhost:3000`

### Full Stack Development

For concurrent development, run both backend and frontend servers:

```bash
# Terminal 1 - Backend
cd backend && ./mvnw spring-boot:run

# Terminal 2 - Frontend  
cd frontend && npm run dev
```

## API Endpoints

### Personal Information
- `GET /api/personal-info` - Get personal information
- `PUT /api/personal-info` - Update personal information

### Projects
- `GET /api/projects` - Get all projects
- `GET /api/projects/{id}` - Get project by ID
- `GET /api/projects/featured` - Get featured projects
- `GET /api/projects/categories` - Get project categories
- `POST /api/projects` - Create new project
- `PUT /api/projects/{id}` - Update project
- `DELETE /api/projects/{id}` - Delete project

### Skills
- `GET /api/skills` - Get all skills
- `GET /api/skills/categories` - Get skill categories
- `POST /api/skills` - Create new skill
- `PUT /api/skills/{id}` - Update skill
- `DELETE /api/skills/{id}` - Delete skill

### Contact
- `POST /api/contact` - Submit contact form
- `GET /api/contact/messages` - Get all contact messages (admin)

## Project Structure

```
portfolio-website/
├── backend/                 # Spring Boot application
│   ├── src/main/java/com/portfolio/
│   │   ├── controller/     # REST API controllers
│   │   ├── service/        # Business logic layer
│   │   ├── repository/     # Data access layer
│   │   ├── model/          # JPA entities
│   │   └── exception/      # Exception handling
│   └── src/main/resources/
│       ├── application.yml # Configuration
│       └── data.sql       # Sample data
├── frontend/               # React application
│   ├── src/
│   │   ├── components/     # React components
│   │   ├── services/       # API service functions
│   │   ├── types/          # TypeScript interfaces
│   │   └── styles/         # CSS and styling
│   └── public/            # Static assets
└── .kiro/                 # Kiro configuration and specs
```

## Configuration

### Backend Configuration
The backend uses `application.yml` for configuration. Key settings:

- **Database**: H2 in-memory database for development
- **CORS**: Configured for frontend origins
- **Email**: SMTP configuration for contact form

### Frontend Configuration
The frontend uses Vite for build tooling with:

- **Proxy**: API calls proxied to backend during development
- **Tailwind CSS**: Utility-first CSS framework
- **TypeScript**: Type safety and better development experience

## Sample Data

The application comes with sample data including:
- Personal information for John Doe
- 4 sample projects with different categories
- Skills across multiple categories (Programming Languages, Frameworks, Databases, Tools)

## Customization

### Adding Your Information
1. Update the sample data in `backend/src/main/resources/data.sql`
2. Or use the API endpoints to update information programmatically

### Styling
- Modify `frontend/tailwind.config.js` for theme customization
- Update `frontend/src/styles/index.css` for custom styles

### Email Configuration
Update the email settings in `application.yml`:
```yaml
spring:
  mail:
    host: your-smtp-host
    port: 587
    username: your-email
    password: your-password
```

## Deployment

### Backend Deployment
1. Build the JAR file:
   ```bash
   ./mvnw clean package
   ```

2. Run the JAR:
   ```bash
   java -jar target/portfolio-backend-0.0.1-SNAPSHOT.jar
   ```

### Frontend Deployment
1. Build for production:
   ```bash
   npm run build
   ```

2. Serve the `dist` folder using a web server

### Environment Variables
For production, set these environment variables:
- `SPRING_PROFILES_ACTIVE=prod`
- `DATABASE_URL` (for PostgreSQL)
- `MAIL_USERNAME` and `MAIL_PASSWORD`

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact

For questions or support, please contact [your-email@example.com](mailto:your-email@example.com).