package core.google.datastore.forms;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
                        //String
public class ArrayValue {
	
	private List<Value> values;
	
	public ArrayValue(List<Value> value) {
		this.values = new ArrayList(value);
	}
	
	public static ArrayValue arrayValueType(List<ArrayValue> values) {
		return new ArrayValue(values.stream().map(Value::new).collect(Collectors.toList()));
	}
	
	public static ArrayValue blobType(List<Blob> values) {
		return new ArrayValue(values.stream().map(Value::new).collect(Collectors.toList()));
	}

	public static ArrayValue booleanType(List<Boolean> values) {
		return new ArrayValue(values.stream().map(Value::new).collect(Collectors.toList()));
	}
	
	public static ArrayValue DoubleType(List<Double> values) {
		return new ArrayValue(values.stream().map(Value::new).collect(Collectors.toList()));
	}
	
	public static ArrayValue integerType(List<Integer> values) {
		return new ArrayValue(values.stream().map(Value::new).collect(Collectors.toList()));
	}
	
	public static ArrayValue keyType(List<Key> values) {
		return new ArrayValue(values.stream().map(Value::new).collect(Collectors.toList()));
	}
	
	public static ArrayValue stringType(List<String> values) {
		return new ArrayValue(values.stream().map(Value::new).collect(Collectors.toList()));
	}
	
	public void add(Value value) {
		this.values.add(value);
	}
	
	public List<Value> getValues() {
		return values;
	}

	public void setValues(List<Value> values) {
		this.values = values;
	}
	
}
