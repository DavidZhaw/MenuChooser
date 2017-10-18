package ch.zhaw.iwi.menuchooser.server.json;


import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import ch.zhaw.iwi.menuchooser.service.PathListEntry.Key;

@SuppressWarnings("rawtypes")
public class PathListEntryKeyAdapter implements JsonSerializer<Key> {

	@Override
	public JsonElement serialize(Key key, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.addProperty("name", key.getName());
		result.addProperty("key", String.valueOf(key.getKey()));
		return result;
	}

}