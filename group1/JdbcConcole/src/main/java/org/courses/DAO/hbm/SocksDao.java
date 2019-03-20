package org.courses.DAO.hbm;

import org.courses.domain.hbm.Socks;
import org.hibernate.SessionFactory;

public class SocksDao extends AbstractDao<Socks, Integer> {
    public SocksDao(SessionFactory factory) {
        super(factory);
        Entity = new Socks();
    }
}
