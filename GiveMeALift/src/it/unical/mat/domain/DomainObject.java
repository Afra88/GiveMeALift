package it.unical.mat.domain;

public abstract class DomainObject {
	
	protected long id;
	
	public DomainObject() {
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public abstract void copy(DomainObject object2);
	
	@Override
	public boolean equals(Object obj) {
		return true;
	}

}