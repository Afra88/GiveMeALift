package it.unical.mat.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DomainObject {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="ID")
	private long id;
	
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
