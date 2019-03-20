package org.courses.DAO.hbm;

import org.courses.DAO.DAO;
import org.courses.domain.hbm.Composition;
import org.hibernate.SessionFactory;

public class CompositionDao extends AbstractDao<Composition, Integer>{
    public CompositionDao(SessionFactory factory) {
        super(factory);
        Entity = new Composition();
    }
}
