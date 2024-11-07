package hibernateOTM;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String owner;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)  // One-to-many mapping
    @JoinColumn(name = "cart_id")  // Foreign key in the Item table
    private List<Item> items = new ArrayList<>();

    // Constructors
    public Cart() {}

    public Cart(String owner) {
        this.owner = owner;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    // Helper methods to add/remove items
    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", items=" + items +
                '}';
    }
}
