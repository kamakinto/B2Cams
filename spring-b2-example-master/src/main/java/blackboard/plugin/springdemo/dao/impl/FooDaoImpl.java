package blackboard.plugin.springdemo.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import blackboard.plugin.springdemo.dao.FooDao;
import blackboard.plugin.springdemo.model.Foo;

@Component
public class FooDaoImpl implements FooDao{
	
	@PersistenceContext(unitName="CamsHibernatePersistenceUnit")
	private EntityManager entityManagerCams;

	@Override
	@Transactional
	public List<Foo> getFoos() {
		String qlString = "SELECT p FROM Foo p";
		TypedQuery<Foo> query = entityManagerCams.createQuery(qlString, Foo.class);
		return query.getResultList();
	}
	
	public Map<String, Object> getConnectionInfo(){
	Map<String,Object> connectionInfo = entityManagerCams.getProperties();
		return connectionInfo;
	}

	@Override
	public Foo getFooByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long deleteFooByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
