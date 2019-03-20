package org.courses.DAO.hbm;

import org.courses.DAO.DAO;
import org.courses.domain.hbm.Material;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;

public class MaterialDao extends AbstractDao<Material, Integer> {

    public MaterialDao(SessionFactory factory) {
        super(factory);
        Entity = new Material();
    }
}
