package core.google.datastore.forms;

public class PartitionId {

	private String namespaceId;
	private String projectId;
	
	public PartitionId(String namespaceId, String projectId) {
		super();
		this.namespaceId = namespaceId;
		this.projectId = projectId;
	}

	public String getNamespaceId() {
		return namespaceId;
	}

	public void setNamespaceId(String namespaceId) {
		this.namespaceId = namespaceId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	
}
