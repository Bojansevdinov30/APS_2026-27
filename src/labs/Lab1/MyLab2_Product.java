package labs.Lab1;

import java.util.*;

/*Write a program for a warehouse in which products are stored in containers.

The class Product has:

name (String)
price (double)
The method finalPrice() returns the price. The method toString() returns a string in the format NAME price=PRICE final=FINAL, with both prices printed to 2 decimals.

The class Food extends Product and has the attribute daysToExpiry (int). If there are 3 or fewer days until expiry, the final price is 50% of the price. toString() appends days=D.

The class Electronics extends Product and has the attribute warrantyMonths (int). If the warranty is longer than 24 months, the final price is increased by 15%. toString() appends warranty=W.

The generic class Pair<K, V> stores a key and a value. It has a constructor and the methods getKey() and getValue().

The generic class Container<T extends Product> stores products of type T. The notation extends Product means T must be Productor one of its subclasses, which is why the container can call finalPrice(). A container has a name, a capacity and an ArrayList<T>. It must have the following methods:

boolean add(T item) – adds a product; returns false if the container is full
int size()
double totalValue() – the sum of the final prices
T mostExpensive() – the product with the highest final price; on a tie, return the first one, and if the container is empty, return null
Pair<String, Double> summary() – returns a pair of (container name, total value)
void print() – prints === NAME (SIZE/CAPACITY) ===, then all products, then Total: X, then Most expensive: NAME (PRICE) or Most expensive: none
The main class creates three containers:

a Container<Food> named Fridge
a Container<Electronics> named Tech
a Container<Product> named Overflow
Food goes into Fridge and electronics into Tech. If the matching container is full, the product goes into Overflow. This works because both Food and Electronics are a Product. If Overflow is also full, print Rejected: NAME. Casting is not allowed.

Input:

the first line contains the capacities of Fridge, Tech and Overflow
the second line contains N, the number of products
then follow N lines in the format F name price days or E name price warranty
Output:

the Rejected messages (in reading order)
print() for Fridge, Tech and Overflow, in that order
Most valuable container: NAME (TOTAL), computed with summary() (on a tie, print the first one)
Top food expires in D days, if Fridge is not empty
Top electronics warranty: W months, if Tech is not empty
Items 4 and 5 are obtained directly from mostExpensive(), without casting.*/
public class MyLab2_Product {

    static class Product {
        protected String name;
        protected double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public double finalPrice() {
            return price;
        }

        @Override
        public String toString() {
            return String.format("%s price=%.2f final=%.2f", name, price, finalPrice());
        }
    }

    static class Food extends Product {
        private int daysToExpiry;

        public Food(String name, double price, int daysToExpiry) {
            super(name, price);
            this.daysToExpiry = daysToExpiry;
        }

        @Override
        public double finalPrice() {
            if (daysToExpiry <= 3) {
                return super.finalPrice() * 0.50;
            }
            return super.finalPrice();
        }

        @Override
        public String toString() {
            return String.format("%s days=%d", super.toString(), daysToExpiry);
        }
    }

    static class Electronics extends Product {
        private int warrantyMonths;

        public Electronics(String name, double price, int warrantyMonths) {
            super(name, price);
            this.warrantyMonths = warrantyMonths;
        }

        @Override
        public double finalPrice() {
            if (warrantyMonths > 24) {
                return super.finalPrice() * 1.15;
            }

            return super.finalPrice();
        }

        @Override
        public String toString() {
            return String.format("%s warranty=%d", super.toString(), warrantyMonths);
        }
    }

    static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    static class Container<T extends Product> {
        private String name;
        private int capacity;
        private ArrayList<T> products;

        public Container(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
            this.products = new ArrayList<T>();
        }

        public boolean add(T item) {
            if (products.size() >= capacity) {
                return false;
            }

            products.add(item);
            return true;
        }

        public int size() {
            return products.size();
        }

        public double totalValue() {
            double total = 0;

            for (int i = 0; i < products.size(); i++) {
                total += products.get(i).finalPrice();
            }

            return total;
        }

        public T mostExpensive() {
            if (products.isEmpty()) {
                return null;
            }

            T mostExpensive = products.get(0);

            for (int i = 1; i < products.size(); i++) {
                if (products.get(i).finalPrice() > mostExpensive.finalPrice()) {
                    mostExpensive = products.get(i);
                }
            }

            return mostExpensive;
        }

        public Pair<String, Double> summary() {
            return new Pair<String, Double>(name, totalValue());
        }

        public void print() {
            System.out.println("=== " + name + " (" + size() + "/" + capacity + ") ===");

            for (int i = 0; i < products.size(); i++) {
                System.out.println(products.get(i).toString());
            }

            System.out.printf("Total: %.2f%n", totalValue());

            T mostExpensive = mostExpensive();

            if (mostExpensive == null) {
                System.out.println("Most expensive: none");
            } else {
                System.out.printf("Most expensive: %s (%.2f)%n", mostExpensive.name, mostExpensive.finalPrice());
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int fridgeCapacity = sc.nextInt();
        int techCapacity = sc.nextInt();
        int overflowCapacity = sc.nextInt();

        Container<Food> fridge = new Container<Food>("Fridge", fridgeCapacity);

        Container<Electronics> tech = new Container<Electronics>("Tech", techCapacity);

        Container<Product> overflow = new Container<Product>("Overflow", overflowCapacity);

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            double price = sc.nextDouble();

            if (type.equals("F")) {
                int days = sc.nextInt();

                Food food = new Food(name, price, days);

                if (!fridge.add(food)) {
                    if (!overflow.add(food)) {
                        System.out.println("Rejected: " + name);
                    }
                }

            } else if (type.equals("E")) {
                int warranty = sc.nextInt();

                Electronics electronics = new Electronics(name, price, warranty);

                if (!tech.add(electronics)) {
                    if (!overflow.add(electronics)) {
                        System.out.println("Rejected: " + name);
                    }
                }
            }
        }

        fridge.print();
        tech.print();
        overflow.print();

        Pair<String, Double> fridgeSummary = fridge.summary();
        Pair<String, Double> techSummary = tech.summary();
        Pair<String, Double> overflowSummary = overflow.summary();

        Pair<String, Double> mostValuable = fridgeSummary;

        if (techSummary.getValue() > mostValuable.getValue()) {
            mostValuable = techSummary;
        }

        if (overflowSummary.getValue() > mostValuable.getValue()) {
            mostValuable = overflowSummary;
        }

        System.out.printf("Most valuable container: %s (%.2f)%n", mostValuable.getKey(), mostValuable.getValue());

        if (fridge.size() > 0) {
            Food topFood = fridge.mostExpensive();
            System.out.println("Top food expires in " + topFood.daysToExpiry + " days");
        }

        if (tech.size() > 0) {
            Electronics topElectronics = tech.mostExpensive();
            System.out.println("Top electronics warranty: " + topElectronics.warrantyMonths + " months");
        }

    }
}
