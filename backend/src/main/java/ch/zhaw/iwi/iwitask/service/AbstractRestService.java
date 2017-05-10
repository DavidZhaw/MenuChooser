package ch.zhaw.iwi.iwitask.service;

import com.google.inject.Inject;
import com.google.inject.Injector;

import ch.zhaw.iwi.iwitask.server.json.JsonHelper;
import ch.zhaw.iwi.iwitask.server.json.JsonTransformer;

public abstract class AbstractRestService<T extends AbstractDatabaseService> {

	@Inject
	JsonHelper jsonHelper;
	
	private T databaseService;

	public AbstractRestService(Injector injector, Class<T> databaseServiceClass) {
		super();
		this.databaseService = injector.getInstance(databaseServiceClass);
	}

	public abstract void init();

	protected <C> C fromJson(String json, Class<C> classOfC) {
		return jsonHelper.fromJson(json, classOfC);
	}

	protected JsonTransformer getJsonTransformer() {
		return jsonHelper.getJsonTransformer();
	}

	protected T getDatabaseService() {
		return databaseService;
	}
	
}
