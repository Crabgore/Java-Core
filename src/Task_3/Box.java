package Task_3;

import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit> {
    ArrayList<T> fruitAmount;

    public Box(T... fruits) {
        this.fruitAmount = new ArrayList<>(Arrays.asList(fruits));
    }

    public void addFruits (T... fruits){
        fruitAmount.addAll(Arrays.asList(fruits));
    }

    public float getWeight() {
        float weight = 0;
        for (int i = 0; i < fruitAmount.size(); i++) {
            weight += fruitAmount.get(i).getWeight();
        }
        return weight;
    }

    public boolean compare (Box<? extends Fruit> box){
        return Math.abs(this.getWeight() - box.getWeight()) < 0.0001;
    }

    public void replaceFruits(Box<T> box){
        box.fruitAmount.addAll(this.fruitAmount);
        this.fruitAmount.clear();
    }
}
