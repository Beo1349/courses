package org.courses.commands.jdbc;

import org.courses.DAO.DAO;
import org.courses.domain.hbm.Manufacture;
import org.courses.domain.hbm.Material;

import java.util.Collection;
import java.util.Scanner;

public class ManufactureCrudCommand extends AbsctractCrudCommand<Manufacture, Integer> {

    private Scanner scanner;
    public ManufactureCrudCommand(DAO<Manufacture, Integer> dao, Scanner scanner) {
        super(dao, Manufacture.class);
        this.scanner = scanner;
    }


    @Override
    protected void readEntity(Manufacture entity) {
        System.out.println("Name: ");
        if(scanner.hasNext()){
            String name = scanner.nextLine();
            entity.setName(name);
        }
    }

    @Override
    protected Manufacture convertId(String id) {
        return dao.read(Integer.parseInt(id));
    }

    @Override
    protected void print(Collection<Manufacture> collection) {
        for (Manufacture manufacture : collection) {
            System.out.println(String.format("%d\t%s", manufacture.getId(), manufacture.getName()));
        }
    }
}
