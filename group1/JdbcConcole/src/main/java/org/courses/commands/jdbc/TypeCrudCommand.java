package org.courses.commands.jdbc;

import org.courses.DAO.DAO;
import org.courses.domain.hbm.Manufacture;
import org.courses.domain.hbm.Type;

import java.util.Collection;
import java.util.Scanner;

public class TypeCrudCommand extends AbsctractCrudCommand<Type, Integer> {
    private Scanner scanner;
    public TypeCrudCommand(DAO<Type, Integer> dao, Scanner scanner) {
        super(dao, Type.class);
        this.scanner = scanner;
    }


    @Override
    protected void readEntity(Type entity) {
        System.out.println("Name: ");
        if(scanner.hasNext()){
            String name = scanner.nextLine();
            entity.setName(name);
        }
    }

    @Override
    protected Type convertId(String id) {
        return dao.read(Integer.parseInt(id));
    }

    @Override
    protected void print(Collection<Type> collection) {
        for (Type type : collection) {
            System.out.println(String.format("%d\t%s", type.getId(), type.getName()));
        }
    }
}
