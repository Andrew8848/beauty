package core.google.datastore.forms;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Blob;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Value {
	 
	private ArrayValue arrayValue;
	private Blob blobValue;
	private Boolean booleanValue;
	private Double doubleValue;
	private EntityValue entityValue;
	private Boolean excludeFromIndexes;
	private Integer integerValue;
	private Value values;
	private Key keyValue; 
	private Integer meaning;
	private String nullValue;
	private String stringValue;
	private String timestampValue;
	
	public Value(ArrayValue arrayValue) {
		super();
		this.arrayValue = arrayValue;
	}
		
	public Value(Blob blobValue) {
		super();
		this.blobValue = blobValue;
	}

	public Value(Boolean booleanValue) {
		super();
		this.booleanValue = booleanValue;
	}
	
	public Value(Double doubleValue) {
		super();
		this.doubleValue = doubleValue;
	}
	
	public Value(EntityValue entityValue) {
		super();
		this.entityValue = entityValue;
	}

	public Value(Integer integerValue) {
		super();
		this.integerValue = integerValue;
	}
	
	public Value(Key keyValue) {
		super();
		this.keyValue = keyValue;
	}
	
	public Value(String stringValue) {
		super();
		 this.stringValue = stringValue;
	     this.excludeFromIndexes = true;
	}
	
	public Value(String stringValue, boolean excludeFromIndexes) {
		super();
		if(excludeFromIndexes) {
	      this.stringValue = stringValue;
	      this.excludeFromIndexes = true;
		}else {
		  this.stringValue = stringValue;
		}
	}
	
	public Map<String, Type> getExistingType() {
		return Arrays.asList(this.getClass().getDeclaredFields()).stream().filter(x -> {
			try {
				return x.get(this) != null && x.getName() != "excludeFromIndexes";
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return booleanValue;
		}).collect(Collectors.toMap(Field::getName, v -> {
			try {
				return v.get(this).getClass();
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return v.getClass();
		}));
	}
	
	public Object getExistingValue() throws IllegalArgumentException, IllegalAccessException{
		return Arrays.asList(this.getClass().getDeclaredFields()).stream()
                .filter(x -> {
					try {
						return x.get(this) != null && x.getName() != "excludeFromIndexes";
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return booleanValue;
				}).map(m -> {
					try {
						return m.get(this);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return m;
				}).findFirst().get();
	}
	
	public ArrayValue getArrayValue() {
		return arrayValue;
	}
	public void setArrayValue(ArrayValue arrayValue) {
		this.arrayValue = arrayValue;
	}
	public Blob getBlobValue() {
		return blobValue;
	}
	public void setBlobValue(Blob blobValue) {
		this.blobValue = blobValue;
	}
	public Boolean isBooleanValue() {
		return booleanValue;
	}
	public void setBooleanValue(Boolean booleanValue) {
		this.booleanValue = booleanValue;
	}
	public Double getDoubleValue() {
		return doubleValue;
	}
	public void setDoubleValue(Double doubleValue) {
		this.doubleValue = doubleValue;
	}
	public EntityValue getEntityValue() {
		return entityValue;
	}

	public void setEntityValue(EntityValue entityValue) {
		this.entityValue = entityValue;
	}

	public Boolean isExcludeFromIndexes() {
		return excludeFromIndexes;
	}
	public void setExcludeFromIndexes(Boolean excludeFromIndexes) {
		this.excludeFromIndexes = excludeFromIndexes;
	}
	public Integer getIntegerValue() {
		return integerValue;
	}
	public void setIntegerValue(Integer integerValue) {
		this.integerValue = integerValue;
	}
	public Value getValues() {
		return values;
	}
	public void setValues(Value values) {
		this.values = values;
	}
	public Key getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(Key keyValue) {
		this.keyValue = keyValue;
	}
	public Integer getMeaning() {
		return meaning;
	}
	public void setMeaning(Integer meaning) {
		this.meaning = meaning;
	}
	public String getNullValue() {
		return nullValue;
	}
	public void setNullValue(String nullValue) {
		this.nullValue = nullValue;
	}
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	public String getTimestampValue() {
		return timestampValue;
	}
	public void setTimestampValue(String timestampValue) {
		this.timestampValue = timestampValue;
	}
	
}