package Task_3;

public class Main {
    public static void main(String[] args) {
//        Box<Apple> appleBox = new Box<>(new Apple(), new Apple(), new Apple(), new Apple(), new Apple());
//        Box<Orange> orangeBox = new Box<>(new Orange(), new Orange(), new Orange());

        Box<Apple> appleBox1 = new Box<>();
        Box<Orange> orangeBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        Box<Orange> orangeBox2 = new Box<>();

        appleBox1.addFruits(new Apple(), new Apple(), new Apple(), new Apple(), new Apple(), new Apple());
        orangeBox1.addFruits(new Orange(), new Orange(), new Orange());
        orangeBox2.addFruits(new Orange());

        System.out.println("Первая коробка с яблоками весит " + appleBox1.getWeight());
        System.out.println("Первая коробка с апельсинами весит " + orangeBox1.getWeight());

        System.out.println(appleBox1.compare(orangeBox1));

        appleBox1.replaceFruits(appleBox2);
        orangeBox1.replaceFruits(orangeBox2);

        System.out.println("Первая коробка с яблоками весит " + appleBox1.getWeight());
        System.out.println("Вторая коробка с яблоками весит " + appleBox2.getWeight());
        System.out.println("Первая коробка с апельсинами весит " + orangeBox1.getWeight());
        System.out.println("Вторая коробка с апельсинами весит " + orangeBox2.getWeight());

        System.out.println(appleBox1.compare(orangeBox1));
        System.out.println(appleBox2.compare(orangeBox1));
        System.out.println(appleBox1.compare(orangeBox2));
        System.out.println(appleBox2.compare(orangeBox2));
    }
}
