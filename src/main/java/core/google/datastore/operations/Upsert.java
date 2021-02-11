package core.google.datastore.operations;

import java.util.Map;

import core.google.datastore.forms.Key;
import core.google.datastore.forms.Value;

public class Upsert {

	private Key key;
	private Map<String, Value> properties;
	
	public Upsert(Key key, Map<String, Value> properties) {
		super();
		this.key = key;
		this.properties = properties;
	}
	
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public Map<String, Value> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, Value> properties) {
		this.properties = properties;
	}
	
	
}
