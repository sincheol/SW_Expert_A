package shin_unsolved;

abstract class Coffee {

    public abstract int getPrice();

    @Override
    public String toString() {
        return "test: " + this.getPrice();
    }
}

class CoffeeFactory {
    public static Coffee getCoffee(String type, int price) {
        if ("Latte".equalsIgnoreCase(type))
            return new Latte(price);
        else if ("Americano".equalsIgnoreCase(type))
            return new Americano(price);
        else {
            return new DefaultCoffee();
        }
    }
}

class DefaultCoffee extends Coffee {
    private int price;

    public DefaultCoffee() {
        this.price = -1;
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}

class Latte extends Coffee {
    private int price;

    public Latte(int price) {
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }
}

class Americano extends Coffee {
    private int price;

    public Americano(int price) {
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }
}

public class test {
    public static void main(String[] args) {
        Coffee latte = CoffeeFactory.getCoffee("Latte", 400);
        Coffee ame = CoffeeFactory.getCoffee("Americano", 300);
        System.out.println("Factory Late::" + latte);
        System.out.println("Factory Ame:: " + ame);
    }
}
