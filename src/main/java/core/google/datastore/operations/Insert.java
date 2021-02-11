package core.google.datastore.operations;

import java.util.Map;

import core.google.datastore.forms.Key;
import core.google.datastore.forms.Value;

public class Insert {

	private Map<String, Value> properties;
	private Key key;
	
	public Insert(Map<String, Value> properties, Key key) {
		super();
		this.properties = properties;
		this.key = key;
	}
	
	public Map<String, Value> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, Value> properties) {
		this.properties = properties;
	}
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	
}
