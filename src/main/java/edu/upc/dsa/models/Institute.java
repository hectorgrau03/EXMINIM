package edu.upc.dsa.models;
import edu.upc.dsa.util.RandomUtils;

public class Institute {
    String id;
    String name;
    int operationsCount;

    public Institute() {
        this.setId(RandomUtils.getId());
    }

    public Institute(String id, String name, int operationsCount) {
        this.setId(id);
        this.setName(name);
        this.setOperationsCount(operationsCount);
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

    @Override
    public String toString() {
        return "Institute [id="+id+", name=" + name + "]";
    }
    public int getOperationsCount() {
        return operationsCount;
    }
    public void setOperationsCount(int operationsCount) {
        this.operationsCount = operationsCount;
    }
    
}
