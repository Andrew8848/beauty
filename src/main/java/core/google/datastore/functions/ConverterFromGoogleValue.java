package core.google.datastore.functions;

import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import core.google.datastore.forms.Value;

public class ConverterFromGoogleValue {

	private Map<String, Value> gValueType;

	public ConverterFromGoogleValue(Map<String, Value> gValueType) {
		super();
		this.gValueType = gValueType;
	}
	
	private Object filter(Value value) throws IllegalArgumentException, IllegalAccessException {
		return value.getExistingType().entrySet().stream()
				.map(x -> {
					switch (x.getKey()) {
					case "arrayValue": {
						return value.getArrayValue().getValues().stream().map(v -> {
							try {
								return v.getExistingValue();
							} catch (IllegalArgumentException | IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return v;
						}).collect(Collectors.toList());
					}
					case "entityValue": {
						return value.getEntityValue().getProperties().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, v->{
							try {
								return v.getValue().getExistingValue();
							} catch (IllegalArgumentException | IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return v;
						}));
					}
					default: {
						try {
							return value.getExistingValue();
						} catch (IllegalArgumentException | IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					}
					return x;
				}).findFirst().get();
	}
	
	public Map<String, Object> toSimplify() {
		return this.gValueType.entrySet().stream()
		          .collect(Collectors.toMap(Map.Entry::getKey,  v -> {
					try {
						return filter(new Gson().fromJson(new Gson().toJson(v.getValue()), Value.class));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return v;
				}));
	}

	
	public Map<String, Value> getgValueType() {
		return gValueType;
	}

	public void setgValueTupe(Map<String, Value> gValueType) {
		this.gValueType = gValueType;
	}

}
