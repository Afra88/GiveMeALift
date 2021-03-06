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
	public int hashCode() {
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DomainObject other = (DomainObject) obj;
		if (id != other.id)
			return true;
		return true;
	}
	
	

	
	
}