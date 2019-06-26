package Additional_HW;

import java.util.ArrayList;
import java.util.Arrays;

class Path {
    private ArrayList<Swim> swims;

    ArrayList<Swim> getSwims() {
        return swims;
    }

    Path (Swim... swims){
        this.swims = new ArrayList<>(Arrays.asList(swims));
    }
}
