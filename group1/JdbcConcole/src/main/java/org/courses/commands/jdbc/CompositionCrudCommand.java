package org.courses.commands.jdbc;

import org.courses.DAO.DAO;
import org.courses.domain.hbm.Composition;
import org.courses.domain.hbm.Manufacture;
import org.courses.domain.hbm.Material;
import org.courses.domain.hbm.Socks;

import java.util.Collection;
import java.util.Scanner;

public class CompositionCrudCommand extends AbsctractCrudCommand<Composition, Integer> {

    private Scanner scanner;
    private DAO<Socks,Integer> socksDao;
    private DAO<Material, Integer> materialDao;
    public CompositionCrudCommand(DAO<Composition, Integer> dao, DAO<Socks,Integer> socksDao, DAO<Material, Integer> materialDao, Scanner scanner) {
        super(dao, Composition.class);
        this.scanner = scanner;
        this.socksDao = socksDao;
        this.materialDao = materialDao;
    }


    @Override
    protected void readEntity(Composition entity) {
        Collection<Socks> socks = socksDao.readAll();
        for (Socks sock : socks) {
            System.out.println(String.format("%s", sock.toString() ));
        }
        System.out.println("Socks(choose id): ");
        if(scanner.hasNext()){
            String sock = scanner.nextLine();
            entity.setSocks(socksDao.read(Integer.parseInt(sock)));
        }

        Collection<Material> materials = materialDao.readAll();
        for (Material material : materials) {
            System.out.println(String.format("%d\t%s", material.getId(), material.getName()));
        }
        System.out.println("Material(choose material): ");
        if(scanner.hasNext()) {
            String material = scanner.nextLine();
            entity.setMaterial(materialDao.read(Integer.parseInt(material)));
        }

        System.out.println("Percentage: ");
        if(scanner.hasNext()){
            String percentage = scanner.nextLine();
            entity.setPercentage(Integer.parseInt(percentage));
        }
    }

    @Override
    protected Composition convertId(String id) {
        return dao.read(Integer.parseInt(id));
    }

    @Override
    protected void print(Collection<Composition> collection) {
        for (Composition composition : collection) {
            System.out.println(String.format("%s", composition.toString()));
        }
    }
}
