package ch.zhaw.iwi.menuchooser.server.json;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import ch.zhaw.iwi.menuchooser.service.PathListEntry.Key;

public class JsonHelper {

	private final Gson gson = new GsonBuilder().registerTypeAdapter(Key.class, new PathListEntryKeyAdapter()).enableComplexMapKeySerialization().setExclusionStrategies(new JsonAnnotationExclusionStrategy()).setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
	private final JsonTransformer jsonTransformer = new JsonTransformer(gson);

	public <C> C fromJson(String json, Class<C> classOfC) {
		Map<Field, Object> foreignObjects = new HashMap<>();

		C object = null;
		try {
			object = gson.fromJson(json, classOfC);
		} catch (JsonSyntaxException e) {
			throw e;
		}
		for (Field field : foreignObjects.keySet()) {
			Object foreignObject = foreignObjects.get(field);
			try {
				// set foreign key object
				String methodNameLowerCase = "set" + field.getName().toLowerCase();
				String setterMethod = null;
				for (Method method : object.getClass().getMethods()) {
					if (method.getName().equalsIgnoreCase(methodNameLowerCase)) {
						setterMethod = method.getName();
					}
				}
				Method method = object.getClass().getMethod(setterMethod, field.getType());
				method.invoke(object, foreignObject);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new RuntimeException("Failed setting foreign key object for " + field.getName(), e);
			}
		}
		return object;
	}

	public Gson getGson() {
		return gson;
	}

	public JsonTransformer getJsonTransformer() {
		return jsonTransformer;
	}

}
