package core.google.datastore.forms;

import java.util.ArrayList;
import java.util.List;

public class Key {
	
	private PartitionId partitionId;
	private List<Path> path = new ArrayList<Path>();

	public Key(PartitionId partitionId, List<Path> path) {
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
