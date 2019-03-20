package org.courses.commands.jdbc;

import org.courses.DAO.DAO;
import org.courses.domain.hbm.Manufacture;
import org.courses.domain.hbm.Socks;
import org.courses.domain.hbm.Type;

import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class SocksCrudCommand extends AbsctractCrudCommand<Socks, Integer> {

    private Scanner scanner;
    private DAO<Type, Integer> typeDao;
    private DAO<Manufacture, Integer> manDao;
    public SocksCrudCommand(DAO<Socks, Integer> dao, DAO<Type, Integer> typeDao, DAO<Manufacture,Integer> manDao, Scanner scanner) {
        super(dao, Socks.class);
        this.scanner = scanner;
        this.typeDao = typeDao;
        this.manDao = manDao;
    }


    @Override
    protected void readEntity(Socks entity) {
        try{
            System.out.println("Name: ");
            if(scanner.hasNext()){
                String name = scanner.nextLine();
                entity.setName(name);
            }
            System.out.println("Size: ");
            if(scanner.hasNext()){
                String size = scanner.nextLine();
                entity.setSize(Double.parseDouble(size));
            }

            Collection<Type> types = null;
            types = typeDao.readAll();
            for (Type type : types) {
                System.out.println(String.format("%d\t%s", type.getId(), type.getName()));
            }
            System.out.println("Type(choose id): ");
            if(scanner.hasNext()){

                String type = scanner.nextLine();
                entity.setType(typeDao.read(Integer.parseInt(type)));
            }

            System.out.println("Color(int): ");
            if(scanner.hasNext()){
                String color = scanner.nextLine();
                entity.setColour(Integer.parseInt(color));
            }

            Collection<Manufacture> man = null;
            man = manDao.readAll();
            for (Manufacture manufacture : man) {
                System.out.println(String.format("%d\t%s",manufacture.getId(), manufacture.getName()));
            }
            System.out.println("Manufacture(int): ");
            if(scanner.hasNext()){
                String manufacture = scanner.nextLine();
                entity.setManufacture(manDao.read(Integer.parseInt(manufacture)));
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected Socks convertId(String id) {
        return dao.read(Integer.parseInt(id));
    }

    @Override
    protected void print(Collection<Socks> collection) {
        for (Socks socks : collection) {
            System.out.println(String.format("%s", socks.toString() ));
        }
    }
}
