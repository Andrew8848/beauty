package core.google.datastore.forms;

import com.spring.model.Product;

public class Entity {
	
	private Key key;
	private Path path;
	private Product properties;
	
	public Entity(Key key, Path path, Product properties) {
		super();
		this.key = key;
		this.path = path;
		this.properties = properties;
	}
	
	public Entity(Entity entity) {
		this.key = entity.getKey();
		this.path = entity.getPath();
	    this.properties = entity.getProperties();
	}
	
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public Path getPath() {
		return path;
	}
	public void setPath(Path path) {
		this.path = path;
	}
	public Product getProperties() {
		return properties;
	}
	public void setProperties(Product properties) {
		this.properties = properties;
	}
	
}
