/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.inventory.reservation.system.DB;

/**
 *
 * @author dzhon
 */
public class CLASS_ITEMS {
    int id, stock;
    String name;

    public CLASS_ITEMS(int id, String name, int stock) {
        this.id = id;
        this.stock = stock;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CLASSITEMS{" + "id=" + id + ", stock=" + stock + ", name=" + name + '}';
    }
    
    
    
}
