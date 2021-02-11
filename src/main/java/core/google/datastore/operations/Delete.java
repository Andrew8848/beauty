package core.google.datastore.operations;

import java.util.List;

import core.google.datastore.forms.PartitionId;
import core.google.datastore.forms.Path;

public class Delete {

	private PartitionId partitionId;
	private List<Path> path;

	public Delete(PartitionId partitionId, List<Path> path) {
		super();
		this.partitionId = partitionId;
		this.path = path;
	}

	public PartitionId getPartitionId() {
		return partitionId;
	}

	public void setPartitionId(PartitionId partitionId) {
		this.partitionId = partitionId;
	}

	public List<Path> getPath() {
		return path;
	}

	public void setPath(List<Path> path) {
		this.path = path;
	}


	
	
}
