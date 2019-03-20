package org.courses.DAO.hbm;

import org.courses.domain.hbm.Manufacture;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ManufactureDao extends AbstractDao<Manufacture, Integer> {

    public ManufactureDao(SessionFactory factory) {
        super(factory);
        Entity = new Manufacture();
    }
}
