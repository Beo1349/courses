package org.courses.commands.jdbc;

import org.courses.DAO.DAO;
import org.courses.domain.hbm.Socks;
import org.courses.domain.hbm.Storage;
import org.courses.domain.hbm.Type;

import javax.print.attribute.standard.DateTimeAtProcessing;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StorageCrudCommand extends AbsctractCrudCommand<Storage, Integer> {
    private Scanner scanner;
    private DAO<Socks,Integer> socksDao;
    public StorageCrudCommand(DAO<Storage, Integer> dao, DAO<Socks,Integer> sockDao, Scanner scanner) {
        super(dao, Storage.class);
        this.scanner = scanner;
        this.socksDao = sockDao;
    }


    @Override
    protected void readEntity(Storage entity) throws ParseException {
        try{
            Collection<Socks> socks = socksDao.readAll();
            for (Socks sock : socks) {
                System.out.println(String.format("%s", sock.toString() ));
            }
            System.out.println("Socks(choose id): ");
            if(scanner.hasNext()){
                String sock = scanner.nextLine();
                entity.setSocks(socksDao.read(Integer.parseInt(sock)));
            }

            System.out.println("Enter dateIN: ");
            if(scanner.hasNext()){
                String date = scanner.nextLine();
                if(date.equals("null")){
                    try{
                        entity.getAdded().equals(null);
                    }
                    catch (NullPointerException e){
                        SimpleDateFormat s = new SimpleDateFormat("MM/dd/yyyy");
                        entity.setAdded(dateconvert(s.format(new Date())));
                    }
                }
                else{
                    entity.setAdded(dateconvert(date));
                }
            }

            System.out.println("Enter dateOUT: ");
            if(scanner.hasNext()){
                String date = scanner.nextLine();
                if(date.equals("null")){
                    try{
                        entity.getRetired().equals(null);
                    }
                    catch (NullPointerException e){
                        entity.setRetired(null);
                    }
                }
                else{
                    SimpleDateFormat s1 = new SimpleDateFormat("MMM dd, yyyy", Locale.UK);
                    Date dt1 = s1.parse(entity.getAdded());
                    SimpleDateFormat s2 = new SimpleDateFormat("MM/dd/yyyy");
                    Date dt2 = s2.parse(date);
                    if(dt2.after(dt1))
                    entity.setRetired(dateconvert(date));
                    else
                        entity.setRetired(null);
                }
            }

            System.out.println("Enter usage(int): ");
            if (scanner.hasNext()){
                String usage = scanner.nextLine();
                entity.setUsage(Integer.parseInt(usage));
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private String dateconvert(String date) throws ParseException {
        SimpleDateFormat s = new SimpleDateFormat("MM/dd/yyyy");
        Date dt = s.parse(date);
        SimpleDateFormat a = new SimpleDateFormat("MMM dd, yyyy", Locale.UK);
        return a.format(dt);
    }
    @Override
    protected Storage convertId(String id) {
        return dao.read(Integer.parseInt(id));
    }

    @Override
    protected void print(Collection<Storage> collection) {
        for (Storage st : collection) {
            System.out.println(String.format("%s", st.toString()));
        }
    }
}
