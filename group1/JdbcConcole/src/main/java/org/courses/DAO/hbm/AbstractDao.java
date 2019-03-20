package org.courses.DAO.hbm;

import org.apache.commons.validator.routines.IntegerValidator;
import org.courses.DAO.DAO;
import org.courses.domain.hbm.Manufacture;
import org.courses.domain.hbm.Type;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.swing.text.html.parser.Entity;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public abstract class AbstractDao<Tentity, Tkey> implements DAO<Tentity,Tkey> {

    protected Tentity Entity;
    protected IntegerValidator Int32 = IntegerValidator.getInstance();
    protected SessionFactory factory;

    public AbstractDao(SessionFactory factory) {
        this.factory = factory;
    }
    @Override
    public void save(Collection<Tentity> entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            saveTypes(session, transaction, entity);
        }
        catch (Exception e) {
            if (null != transaction)
                transaction.rollback();
            throw e;
        }
        finally {
            if (null != session)
                session.close();
        }
    }
    @Override
    public void save(Tentity entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            if (null != transaction)
                transaction.rollback();
            //throw e;
        }
        finally {
            if (null != session)
                session.close();
        }
    }
    protected void saveTypes(Session session, Transaction transaction, Collection<Tentity> entity) {
        try {
            for (Tentity e : entity) {
                session.save(e);
            }
            transaction.commit();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public Tentity read(Tkey id){
            Tentity object = null;
            Session session = null;
            try {
                session = factory.openSession();
                object = (Tentity) session.find(Entity.getClass(),id);
            }
            finally {
                if (null != session)
                    session.close();
            }
            return object;
    }

    @Override
    public Collection<Tentity> readAll() {
        Collection<Tentity> result = null;
        Session session = null;
        try {
            session = factory.openSession();
            result = session
                    .createQuery("from " + Entity.getClass().getName())
                    .list();
        }
        finally {
            if (null != session)
                session.close();
        }
        return result;
    }

    @Override
    public Collection<Tentity> find(String filter) {
        List result = null;
        Session session = null;
        try {
            session = factory.openSession();
            result = session
                    .createQuery("from " + Entity.getClass().getName() +
                            " where id = :id " +
                            " or name like :filter")
                    .setParameter("id", Int32.validate(filter))
                    .setParameter("filter", String.format("%%%s%%", filter))
                    .list();
        }
        finally {
            if (null != session)
                session.close();
        }
        return result;
    }

    @Override
    public void delete(Tentity entity) {
        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        }
        finally {
            if (null != session)
                session.close();
        }
    }
}
