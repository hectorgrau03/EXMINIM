package edu.upc.dsa.models;
import edu.upc.dsa.util.RandomUtils;

public class Student {
    
    String id;
    String name;
    String IDinstiute;
    public Student() {
        this.setId(RandomUtils.getId());
    }
    public Student(String id, String name, String IDinstiute) {
        this.setId(id);
        this.setName(name);
        this.setIDinstiute(IDinstiute);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }   

    public void setName(String name) {
        this.name = name;
    }
    public String getIDinstiute() {
        return IDinstiute;
    }

    public void setIDinstiute(String IDinstiute) {
        this.IDinstiute = IDinstiute;
    }

    @Override
    public String toString() {
        return "Student [id="+id+", name=" + name + ", IDinstiute=" + IDinstiute +"]";
    }
}
