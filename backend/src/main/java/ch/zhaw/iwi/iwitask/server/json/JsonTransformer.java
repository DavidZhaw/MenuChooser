package ch.zhaw.iwi.iwitask.server.json;

import com.google.gson.Gson;

import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

	private Gson gson;
	
	public JsonTransformer(Gson gson) {
		super();
		this.gson = gson;
	}

	@Override
	public String render(Object model) {
		return gson.toJson(model);
	}

}