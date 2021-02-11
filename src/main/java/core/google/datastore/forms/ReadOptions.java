package core.google.datastore.forms;

public class ReadOptions {
	
//    private String readConsistency;
	private String transaction;
	
	public ReadOptions(String transaction) {
		super();
		this.transaction = transaction;
	}

//	public ReadOptions(String readConsistency, String transaction) {
//		super();
//		this.readConsistency = readConsistency;
//		this.transaction = transaction;
//	}
	
//	public String getReadConsistency() {
//		return readConsistency;
//	}
//	public void setReadConsistency(String readConsistency) {
//		this.readConsistency = readConsistency;
//	}
	public String getTransaction() {
		return transaction;
	}
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	
	
}
