package org.basic.dao.adapter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.bmb.dao.entity.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.basic.dao.TableidDao;
import com.basic.db.Tableid;
import com.global.App;
@Repository @Transactional
public  class Dao<T,I extends Serializable> extends Session{
	protected Class<T> domainClass;
	public Dao(Class<T> domainClass) {
		this.domainClass=domainClass;
	}
	
	protected String defaultOrderToString="";//" order by id asc ";
	protected Order defaultOrder=Order.asc("id");

	@Transactional(readOnly=false)
	public void save(T domain) {
		sessionFactory.getCurrentSession().save(domain);
	}


	@SuppressWarnings("unchecked")
	public T getById(I id) {
		return (T) sessionFactory.getCurrentSession().get(domainClass, id);
	}
	
	@SuppressWarnings("unchecked")
	public T getById(String id) {
		return (T) sessionFactory.getCurrentSession().get(domainClass, id);
	}


	public void delete(T domain) {
		sessionFactory.getCurrentSession().delete(domain);
	}

	@SuppressWarnings("unchecked")
	public I count() {
		return (I) sessionFactory
				.getCurrentSession()
				.createQuery(
						"select count(*) from " + domainClass.getName() + " t")
				.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public I countByColumn(String column, String value) {
		String w=" where lower("+column+") like ? ";
		return (I) sessionFactory
				.getCurrentSession()
				.createQuery(
						"select count(*) from " + domainClass.getName() + " t "+w).setString(0, value.toLowerCase())
				.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public I countByColumn(String column, BigDecimal value) {
		String w=" where "+column+" = ? ";
		return (I) sessionFactory
				.getCurrentSession()
				.createQuery(
						"select count(*) from " + domainClass.getName() + " t "+w).setBigDecimal(0, value)
				.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public I countByColumn(String column, int value) {
		String w=" where "+column+" = ? ";
		return (I) sessionFactory
				.getCurrentSession()
				.createQuery(
						"select count(*) from " + domainClass.getName() + " t "+w).setInteger(0, value)
				.uniqueResult();
	}
	
	

	@SuppressWarnings("unchecked")
	public List getAll() {
		return sessionFactory.getCurrentSession().createCriteria(domainClass).addOrder(defaultOrder).list();
//				.createQuery("from " + domainClass.getName()+" t left join fetch t.mobils "+defaultOrderToString).list();
//		.createQuery("from " + domainClass.getName()+" t left join t.mobils as m "+defaultOrderToString).list();
		//from Document fetch all properties order by name
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll(int start, int num) {
		return sessionFactory.getCurrentSession()
				.createQuery("from " + domainClass.getName() +" t "+defaultOrderToString)
				.setFirstResult(start).setMaxResults(num).list();
	}
	@SuppressWarnings("unchecked")
	public List<T> getAllByColumn(String column,String value) {
		return sessionFactory.getCurrentSession()
				.createCriteria(domainClass).add( Restrictions.ilike(column, value)).addOrder( defaultOrder ).list();
	}
	@SuppressWarnings("unchecked")
	public List<T> getAllByColumn(String column,BigDecimal value) {
		return sessionFactory.getCurrentSession()
				.createCriteria(domainClass).add( Restrictions.eq(column, value)).addOrder( defaultOrder ).list();
	}
	@SuppressWarnings("unchecked")
	public List<T> getAllByColumn(String column,int value) {
		return sessionFactory.getCurrentSession()
				.createCriteria(domainClass).add( Restrictions.eq(column, value)).addOrder( defaultOrder ).list();
	}
	@SuppressWarnings("unchecked")
	public List<T> getAllByColumn(String column,String value,int start, int num) {
		return sessionFactory.getCurrentSession()
				.createCriteria(domainClass).add( Restrictions.ilike(column, value)).addOrder( defaultOrder )
				.setFirstResult(start).setMaxResults(num).list();
	}
	@SuppressWarnings("unchecked")
	public List<T> getAllByColumn(String column,BigDecimal value,int start, int num) {
		return sessionFactory.getCurrentSession()
				.createCriteria(domainClass).add( Restrictions.eq(column, value)).addOrder( defaultOrder )
				.setFirstResult(start).setMaxResults(num).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAllByColumn(String column,int value,int start, int num) {
		return sessionFactory.getCurrentSession()
				.createCriteria(domainClass).add( Restrictions.eq(column, value)).addOrder( defaultOrder )
				.setFirstResult(start).setMaxResults(num).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAllByColumn(String column,String value,String column2,String value2,int start, int num) {
		return sessionFactory.getCurrentSession()
				.createCriteria(domainClass)
				.add( Restrictions.ilike(column, value))
				.add( Restrictions.ilike(column2, value2))
				.addOrder( defaultOrder )
				.setFirstResult(start).setMaxResults(num).list();
	}

	@SuppressWarnings("unchecked")
	public T getOneByColumn(String column,Object value) {
		return (T) sessionFactory.getCurrentSession()
				.createCriteria(domainClass).add( Restrictions.like(column, value)).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public T getOneByColumn(String column1,Object value1,String column2,Object value2) {
		return (T) sessionFactory.getCurrentSession()
				.createCriteria(domainClass)
				.add( Restrictions.like(column1, value1))
				.add( Restrictions.like(column2, value2))
				.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public T getOneByColumn(
			String column1,
			Object value1,
			String column2,
			Object value2,
			String column3,
			Object value3
			) {
		return (T) sessionFactory.getCurrentSession()
				.createCriteria(domainClass)
				.add( Restrictions.like(column1, value1))
				.add( Restrictions.like(column2, value2))
				.add( Restrictions.like(column3, value3))
				.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public Tableid getTableid(String table) {
		TableidDao d=App.getTableidDao();
		Tableid tableid=d.getById(table);
		if (tableid==null) {
			tableid=new Tableid(table, 1, 1, "000000", 1);
			d.save(tableid);
		}
		return tableid;
	}
	
//	public BigInteger getNewId(String table) {
//		return (BigInteger) sessionFactory
//				.getCurrentSession()
//				.createSQLQuery("select currval('s"+table+"')")
//				.uniqueResult();
//	}
	
	public void runSql(String sql){
		sessionFactory
		.getCurrentSession()
		.createSQLQuery(sql).executeUpdate();
	}


	public Class<T> getDomainClass() {
		return domainClass;
	}


	public void setDomainClass(Class<T> domainClass) {
		this.domainClass = domainClass;
	}
	
}
