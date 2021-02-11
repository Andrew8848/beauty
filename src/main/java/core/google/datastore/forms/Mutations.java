package core.google.datastore.forms;

import com.fasterxml.jackson.annotation.JsonInclude;

import core.google.datastore.operations.Delete;
import core.google.datastore.operations.Insert;
import core.google.datastore.operations.Update;
import core.google.datastore.operations.Upsert;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mutations {

	private Integer baseVersion; 
	private Insert insert;
	private Delete delete;
	private Update update;
	private Upsert upsert;
	
	public Mutations(Insert insert, Delete delete, Update update, Upsert upsert) {
		super();
		this.insert = insert;
		this.delete = delete;
		this.update = update;
		this.upsert = upsert;
	}

	public Integer getBaseVersion() {
		return baseVersion;
	}

	public void setBaseVersion(Integer baseVersion) {
		this.baseVersion = baseVersion;
	}

	public Insert getInsert() {
		return insert;
	}

	public void setInsert(Insert insert) {
		this.insert = insert;
	}

	public Delete getDelete() {
		return delete;
	}

	public void setDelete(Delete delete) {
		this.delete = delete;
	}

	public Update getUpdate() {
		return update;
	}

	public void setUpdate(Update update) {
		this.update = update;
	}

	public Upsert getUpsert() {
		return upsert;
	}

	public void setUpsert(Upsert upsert) {
		this.upsert = upsert;
	}
	
}
