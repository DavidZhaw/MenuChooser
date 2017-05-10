package ch.zhaw.iwi.iwitask;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.staticFiles;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.hibernate.dialect.PostgreSQL95Dialect;
import org.postgresql.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;

import ch.zhaw.iwi.iwitask.server.CorsHeaders;
import ch.zhaw.iwi.iwitask.server.Database;
import ch.zhaw.iwi.iwitask.service.exception.ExceptionRestService;
import ch.zhaw.iwi.iwitask.service.person.MonthlyRestService;
import ch.zhaw.iwi.iwitask.service.person.PersonRestService;
import ch.zhaw.iwi.iwitask.service.project.ProjectRestService;
import ch.zhaw.iwi.iwitask.service.task.TaskRestService;
import ch.zhaw.iwi.iwitask.service.user.UserRestService;
import ch.zhaw.iwi.iwitask.service.work.WorkRestService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import spark.Spark;

public class Main {
	private static final String HEROKU_PORT = "PORT";
	private static final JpaPersistModule persistModule = new JpaPersistModule("AssessmentTool");
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	private static Injector injector;

	public static void main(String[] args) {
		initFrontend();
		initDatabase();
		initServer();

		// Cache
		before((request, response) -> {
			response.header("cache-control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
			response.header("pragma", "no-cache"); // HTTP 1.0
			response.header("expires", "0"); // HTTP 1.0 proxies
		});

		// Wake Up Ping (Heroku)
		get("services/ping", (req, res) -> {
			Jws<Claims> jwt = JwtUtility.getJsonWebToken(req);
			String userId = null;
			if (jwt != null) {
				userId = jwt.getBody().getSubject();
			}
			return "{ \"status\": \"ok\", \"userId\": \"" + (userId == null ? "" : userId) + "\", \"version\": \"" + new Version().getVersion() + "\"}";
		});

		// Services
		injector.getInstance(UserRestService.class).init();
		injector.getInstance(ProjectRestService.class).init();
		injector.getInstance(PersonRestService.class).init();
		injector.getInstance(TaskRestService.class).init();
		injector.getInstance(WorkRestService.class).init();
		injector.getInstance(MonthlyRestService.class).init();

		// Exception (must be last)
		injector.getInstance(ExceptionRestService.class).init();

		logger.info("server ready and listening for requests");
	}

	private static void initFrontend() {
		try {
			File frontendDirectory = new File("../frontend/dist");
			if (!frontendDirectory.isDirectory()) {
				frontendDirectory = new File("frontend/dist");
			}
			staticFiles.externalLocation(frontendDirectory.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not resolve frontend files");
			System.exit(1);
		}
	}

	private static void initDatabase() {
		Properties properties = new Properties();
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get(HEROKU_PORT) != null) {
			try {
				URI dbUri = new URI(System.getenv("DATABASE_URL"));
			    String username = dbUri.getUserInfo().split(":")[0];
			    String password = dbUri.getUserInfo().split(":")[1];
			    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
			    
				properties.put("javax.persistence.jdbc.driver", Driver.class.getName());
				properties.put("javax.persistence.jdbc.url", dbUrl);
				properties.put("javax.persistence.jdbc.user", username);
				properties.put("javax.persistence.jdbc.password", password);
				properties.put("hibernate.dialect", PostgreSQL95Dialect.class.getName());
			} catch (URISyntaxException e) {
				e.printStackTrace();
				System.out.println("Database not found");
				System.exit(1);
			}
		}
		persistModule.properties(properties);
		injector = Guice.createInjector(persistModule);
		
		injector.getInstance(Database.class).init("9998", "9999", true);
	}

	public static void initServer() {
		Spark.port(getHerokuAssignedPort());
		CorsHeaders.init();
	}

	static int getHerokuAssignedPort() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get(HEROKU_PORT) != null) {
			return Integer.parseInt(processBuilder.environment().get(HEROKU_PORT));
		}
		return 4567; // return default port if heroku-port isn't set (i.e. on localhost)
	}
}
