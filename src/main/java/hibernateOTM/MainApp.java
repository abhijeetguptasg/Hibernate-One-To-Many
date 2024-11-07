package hibernateOTM;



import jakarta.persistence.*;

public class MainApp {

    public static void main(String[] args) {
        
        // Create EntityManagerFactory and EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cartItem");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        
        try {
            // Begin transaction
            transaction.begin();
            
            // Create Cart and Items
            Cart cart = new Cart("John Doe");
            
            Item item1 = new Item("Laptop", 1000.00);
            Item item2 = new Item("Smartphone", 500.00);
            
            // Add items to cart
            cart.addItem(item1);
            cart.addItem(item2);
            
            // Persist the cart (Items will be automatically persisted due to CascadeType.ALL)
            em.persist(cart);
            
            // Commit the transaction
            transaction.commit();
            
            System.out.println("Cart and Items saved successfully!");
            
            // Retrieve Cart
            Cart retrievedCart = em.find(Cart.class, cart.getId());
            System.out.println("Retrieved Cart: " + retrievedCart);
//
//            // Update Cart (Add another item)
//            transaction.begin();
//            Item item3 = new Item("Tablet", 300.00);
//            retrievedCart.addItem(item3);
//            em.merge(retrievedCart);  // Merging changes
//            transaction.commit();
//            System.out.println("Cart updated with new Item!");
//
//            // Remove an item from the cart
//            transaction.begin();
//            retrievedCart.removeItem(item1);
//            em.merge(retrievedCart);  // Merging changes after removal
//            transaction.commit();
//            System.out.println("Item removed from Cart!");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
