package ch.zhaw.iwi.indoortracking.service;

import static org.junit.Assert.assertEquals;
import static spark.Spark.port;
import static spark.Spark.stop;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.io.CharStreams;
import com.google.gson.reflect.TypeToken;

import ch.zhaw.iwi.indoortracking.AbstractDatabaseUnitTest;
import ch.zhaw.iwi.iwitask.model.project.AbstractEntity;
import ch.zhaw.iwi.iwitask.server.json.JsonHelper;
import ch.zhaw.iwi.iwitask.service.AbstractCrudDatabaseService;
import ch.zhaw.iwi.iwitask.service.AbstractCrudRestService;
import ch.zhaw.iwi.iwitask.service.PathListEntry;

public abstract class AbstractCrudRestServiceTest<E extends AbstractEntity, KEYTYPE> extends AbstractDatabaseUnitTest {

	private AbstractCrudRestService<E, KEYTYPE> service;

	private int port = 4568;

	protected abstract Class<?> getService();

	protected abstract Class<E> getEntityClass();
	
	protected void beforeCreate(E entity) {
	}

	@SuppressWarnings("unchecked")
	@Before
	public void before() throws Exception {
		port = 4568;
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get("PORT") != null) {
			port = Integer.parseInt(processBuilder.environment().get("PORT"));
		}
		port(port);

		service = (AbstractCrudRestService<E, KEYTYPE>) getInjector().getInstance(getService());
		service.init();
	}
	
	@After
	public void after() {
		stop();
	}

	@Test
	@Ignore
	public void testList() throws Exception {
		E entity = getInjector().getInstance(getEntityClass());
		Object svcObject = service.getCrudDatabaseService();
		@SuppressWarnings("unchecked")
		AbstractCrudDatabaseService<E, KEYTYPE> svc = (AbstractCrudDatabaseService<E, KEYTYPE>) svcObject;
		beforeCreate(entity);
		svc.create(entity);

		String jsonResult = testGet();

		TypeToken<ArrayList<PathListEntry<Long>>> token = new TypeToken<ArrayList<PathListEntry<Long>>>() {};
		List<PathListEntry<Long>> result = getInjector().getInstance(JsonHelper.class).getGson().fromJson(jsonResult, token.getType());
		assertEquals(1, result.size());

		svc.delete(entity);
	}

	private String testGet() throws IOException, ClientProtocolException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet("http://localhost:" + port + "/services/" + getEntityClass().getSimpleName().toLowerCase());
		HttpResponse getResponse = httpClient.execute(getRequest);
		HttpEntity getResponseEntity = getResponse.getEntity();
		Reader reader = new InputStreamReader(getResponseEntity.getContent());

		String jsonResult = CharStreams.toString(reader);
		return jsonResult;
	}
	
}