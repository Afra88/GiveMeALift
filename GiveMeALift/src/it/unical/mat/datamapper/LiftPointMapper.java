package it.unical.mat.datamapper;

import it.unical.mat.domain.DomainObject;
import it.unical.mat.domain.LiftPoint;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LiftPointMapper extends AbstractMapper {

	public List<LiftPoint> findLiftPointByLocation(String string) {
		List<LiftPoint> res = new LinkedList<LiftPoint>();
		
		String findStatement = "from LiftPoint"
								+ " where "
								+ "(difference(city,:par1) >=1 "
								+ "or difference(street,:par1) >=1 "
								+ "or difference(state, :par1) >=1 "
								+ "or difference(province, :par1) >=1 "
								+ "or difference(region, :par1) >=1 )"
								;
		
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("par1", string);
		
		Collection<DomainObject> objects=find(findStatement, parameters,false);
		for (DomainObject object : objects) {
			res.add((LiftPoint) object);
		}		

		return res;	
	}

}
