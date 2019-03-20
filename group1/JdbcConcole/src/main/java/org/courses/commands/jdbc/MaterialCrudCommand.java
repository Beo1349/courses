package org.courses.commands.jdbc;

import org.courses.DAO.DAO;
import org.courses.DAO.hbm.MaterialDao;
import org.courses.domain.hbm.Material;

import java.util.Collection;
import java.util.Scanner;

public class MaterialCrudCommand extends AbsctractCrudCommand<Material, Integer> {

    private Scanner scanner;
    public MaterialCrudCommand(DAO<Material, Integer> dao, Scanner scanner) {
        super(dao, Material.class);
        this.scanner = scanner;
    }

    @Override
    protected void readEntity(Material entity) {
        System.out.println("Name: ");
        if(scanner.hasNext()){
            String name = scanner.nextLine();
            entity.setName(name);
        }
    }

    @Override
    protected Material convertId(String id) {
        return dao.read(Integer.parseInt(id));
    }

    @Override
    protected void print(Collection<Material> collection) {
        for (Material material : collection) {
            System.out.println(String.format("%d\t%s", material.getId(), material.getName()));
        }
    }
}
