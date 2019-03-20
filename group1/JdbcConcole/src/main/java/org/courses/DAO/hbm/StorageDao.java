package org.courses.DAO.hbm;

import org.courses.domain.hbm.Storage;
import org.hibernate.SessionFactory;

public class StorageDao extends AbstractDao<Storage, Integer> {
    public StorageDao(SessionFactory factory) {
        super(factory);
        Entity = new Storage();
    }
}
