package org.courses.commands.jdbc;

import javassist.bytecode.annotation.Annotation;
import org.courses.DAO.DAO;
import org.courses.commands.Command;
import org.courses.commands.CommandFormatException;
import org.courses.domain.hbm.Manufacture;
import org.courses.domain.hbm.Type;

import javax.swing.text.html.parser.Entity;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

public abstract class AbsctractCrudCommand <Tentity, Tkey>  implements Command {

    protected DAO<Tentity, Tkey> dao;
    protected Class<Tentity> entityType;
    protected String verb;
    protected String args[];

    public AbsctractCrudCommand(DAO<Tentity, Tkey> dao, Class<Tentity> entityType){
        this.dao = dao;
        this.entityType = entityType;
    }
    @Override
    public void parse(String[] args) {
        if (args.length > 0) {
            verb = args[0];
        }
        else {
            throw new CommandFormatException("Verb is not specified");
        }
        this.args = args;
    }

    @Override
    public void execute() throws ParseException {
        if("add".equals(verb)){
            add();
        }
        else if("update".equals(verb)){
            update();
        }
        else if("delete".equals(verb)){
            delete();
        }
        else if("list".equals(verb)){
            list();
        }
    }

    private void add() throws ParseException {
        Tentity entity = null;
        try{
            entity = entityType.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        readEntity(entity);
        dao.save(Arrays.asList(entity));
    }

    protected abstract void readEntity(Tentity entity) throws ParseException;
    private void update() throws ParseException {
        Tentity entity = null;
        String id = null;

        if(args.length > 1){
            id = args[1];
        }else{
            throw new CommandFormatException("Id is not specified");
        }
        entity = convertId(id);
        readEntity(entity);
        dao.save(entity);
    }
    private void delete(){
        String id = null;

        if(args.length > 1){
            id = args[1];
        }else{
            throw new CommandFormatException("Id is not specified");
        }
        dao.delete(convertId(id));

    }

    protected abstract Tentity convertId(String id);

    private void list(){
        print(dao.readAll());
    }
    protected abstract void print(Collection<Tentity> collection);
}
