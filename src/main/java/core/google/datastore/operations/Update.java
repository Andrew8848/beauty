package core.google.datastore.operations;

import java.util.Map;

import core.google.datastore.forms.Key;
import core.google.datastore.forms.Value;

public class Update {

	private Key key;
	private Map<String, Value> properties;
	
	public Update(Map<String, Value> properties, Key key) {
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
