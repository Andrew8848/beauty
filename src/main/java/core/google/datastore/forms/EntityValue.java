package core.google.datastore.forms;

import java.sql.Blob;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class EntityValue {

	private Map<String, Value> properties;
	
//	private Key key;
//	
//	public EntityValue(Map<String, Value> properties, Key key) {
//		super();
//		this.properties = properties;
//		this.key = key;
//	}

	public EntityValue(Map<String, Value> properties) {
		super();
		this.properties = properties;
	}
	
	
	
	public static EntityValue arrayValueType(Map<String, ArrayValue> properties) {
		return new EntityValue(properties.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> new Value(e.getValue()))));
	}

	public static EntityValue blobType(Map<String, Blob> properties) {
		return new EntityValue(properties.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> new Value(e.getValue()))));
	}
	
	public static EntityValue booleanType(Map<String, Boolean> properties) {
		return new EntityValue(properties.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> new Value(e.getValue()))));
	}
	
	public static EntityValue DoubleType(Map<String, Double> properties) {
		return new EntityValue(properties.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> new Value(e.getValue()))));
	}
	
	public static EntityValue integerType(Map<String, Integer> properties) {
		return new EntityValue(properties.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> new Value(e.getValue()))));
	}
	
	public static EntityValue keyType(Map<String, Key> properties) {
		return new EntityValue(properties.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> new Value(e.getValue()))));
	}
	
	public static EntityValue stringType(Map<String, String> properties) {
		return new EntityValue(properties.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> new Value(e.getValue()))));
	}
	
	
	
	public Map<String, Value> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Value> properties) {
		this.properties = properties;
	}

//	public Key getKey() {
//		return key;
//	}
//
//	public void setKey(Key key) {
//		this.key = key;
//	}
	
	
	
}
