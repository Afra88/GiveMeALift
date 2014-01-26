package it.unical.mat.datamapper;

import it.unical.mat.domain.Administrator;
import it.unical.mat.domain.DomainObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AdministratorMapper extends AbstractMapper {

	public List<Administrator> findAdministrators(){
		List<Administrator> list=new LinkedList<Administrator>();
		String statement="from Administrator";
		Collection<DomainObject> res=find(statement, null, false);
		for (DomainObject domainObject : res) {
			list.add((Administrator)domainObject);
		}
		return list;
	}

	public Administrator findAdministratorByEmailAndPassword(String email,String password) {
		List<Administrator> list=new LinkedList<Administrator>();
		String statement="from Administrator where email=:par1 and password=:par2 ";
		Map<String, Object> parameters=new HashMap<String,Object>();
		parameters.put("par1",email);
		parameters.put("par2",password);
		Collection<DomainObject> res=find(statement, parameters, false);
		for (DomainObject domainObject : res) {
			list.add((Administrator)domainObject);
		}
		if(list.size()==1)
			return list.get(0);
		return null;
	}
	
}
