package it.unical.mat.datamapper;

import it.unical.mat.domain.DomainObject;
import it.unical.mat.domain.Lift;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class LiftMapper extends AbstractMapper {
	
	public List<Lift> findFromTo(String CityFrom, String CityTo){
		List<Lift> result = new LinkedList<Lift>();
		Collection<DomainObject> objects = super.find("from Lift l, Lift_Point l1, Lift_Point l2,"
				+ "where l.pick_up = l1.id and l.drop_off=l2.id");
		for (DomainObject object : objects) {
			result.add((Lift) object);
		}
		return result;
	}

}
