package it.unical.mat.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AM")
public class Administrator extends User {
	
	public Administrator() {
		super();
	}

	@Override
	public void copy(DomainObject object2) {
		super.copy(object2);		
	}
	
}
