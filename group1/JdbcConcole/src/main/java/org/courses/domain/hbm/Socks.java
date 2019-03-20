package org.courses.domain.hbm;

import javax.persistence.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Socks")
public class Socks {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "size")
    private double size;

    @Column(name = "colour")
    private int colour;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "manufacture")
    private Manufacture manufacture;

    @ManyToOne
    @JoinColumn(name = "type")
    private Type type;

    @OneToMany(mappedBy = "socks")
    private List<Composition> composition;

    @OneToMany(mappedBy = "socks")
    private List<Storage> storage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    public Manufacture getManufacture() {
        return manufacture;
    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return String.format(id + " " + name + " " + size + " " + Color.decode(generateColor(colour)).toString().substring(14) + " " + type.getId() + " " + manufacture.getId());
    }

    private static String generateColor(int r) {
        StringBuilder color = new StringBuilder(Integer.toHexString(r));
        while (color.length() < 6) {
            color.append("0");
        }

        return color.append("#").reverse().toString();

    }

    public List<Composition> getComposition() {
        return composition;
    }

    public void setComposition(List<Composition> composition) {
        this.composition = composition;
    }

    public List<Storage> getStorage() {
        return storage;
    }

    public void setStorage(List<Storage> storage) {
        this.storage = storage;
    }
}
