import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Test2{
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}, true},
                {new int[]{2, 8, 3, 6, 95}, false},
                {new int[]{2, 2, 7, 4, 4, 7, 5, 4, 1}, true},
                {new int[]{4, 2, 3, 2, 4, 2, 1, 6, 4, 9}, true},
        });
    }

    private int[] arrayIn;
    private boolean result;

    public Test2 (int[] arrayIn, boolean result) {
        this.arrayIn = arrayIn;
        this.result = result;
    }

    @Test
    public void startTest1(){
        Assert.assertSame(result, CheckArray.checkArray(arrayIn));
    }
}