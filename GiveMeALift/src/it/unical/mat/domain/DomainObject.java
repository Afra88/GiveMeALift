package it.unical.mat.domain;

public abstract class DomainObject {
	
	protected long id;
	
	public DomainObject() {
		this.id=0L;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public abstract void copy(DomainObject object2);

}