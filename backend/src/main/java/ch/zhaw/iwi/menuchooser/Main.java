package ch.zhaw.iwi.alcoholtester;

import static spark.Spark.before;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.zhaw.iwi.alcoholtester.backend.AlkServices;
import ch.zhaw.iwi.alcoholtester.server.CorsHeaders;
import ch.zhaw.iwi.alcoholtester.server.json.JsonHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import spark.Spark;

public class Main {
	private static final String HEROKU_PORT = "PORT";
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		initFrontend();
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
			return "{ \"status\": \"ok\", \"userId\": \"" + (userId == null ? "alcoholtester" : userId) + "\", \"version\": \"" + new Version().getVersion() + "\"}";
		});

		// Services
		// Login
		post("services/login", (req, res) -> {
			CredentialView credentialView = new JsonHelper().fromJson(req.body(), CredentialView.class);
			String username = credentialView.username;
			String password = credentialView.password;
			LoginView result = new LoginView();
			if (username.equalsIgnoreCase("ad") && password.equalsIgnoreCase("iop")) {
				String jwtString = JwtUtility.createJsonWebToken(username, "en");
				res.header("Authorization", jwtString);
				result.jwt = jwtString;
				result.languageCode = "en";
			} else {
				logger.info("Login denied with user " + username);
				Spark.halt(401, "Wrong user/pw");
			}
			return result;
		}, new JsonHelper().getJsonTransformer()); // TODO

		// Unsere Alkohol Tester Services
		AlkServices alkServices = new AlkServices();
		alkServices.init();

		// Exception Handler
		exception(Exception.class, (exception, request, response) -> {
			response.status(500);
			StringWriter errors = new StringWriter();
			exception.printStackTrace(new PrintWriter(errors));
			exception.printStackTrace();
		});

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

	private static class CredentialView {
		private String username;
		private String password;
	}

	@SuppressWarnings("unused")
	private static class LoginView {
		private String jwt;
		private String languageCode;
	}

}
