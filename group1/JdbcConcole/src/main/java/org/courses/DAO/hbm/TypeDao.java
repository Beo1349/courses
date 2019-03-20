package org.courses.DAO.hbm;

import org.apache.commons.validator.routines.IntegerValidator;
import org.courses.DAO.DAO;
import org.courses.domain.hbm.Type;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

public class TypeDao extends AbstractDao<Type, Integer> {

    public TypeDao(SessionFactory factory) {
        super(factory);
        Entity = new Type();
    }
}
