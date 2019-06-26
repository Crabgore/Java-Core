import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Test1{
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7},new int[]{1, 7}},
                {new int[]{1, 4, 3, 6, 4},new int[]{}},
                {new int[]{2, 2, 7, 4, 4, 7, 5, 4, 1},new int[]{1}},
                {new int[]{4, 2, 3, 2, 4, 2, 1, 6, 4, 9},new int[]{9}},
        });
    }

    private int[] arrayIn, arrayOut;

    public Test1 (int[] arrayIn, int[] arrayOut) {
        this.arrayIn = arrayIn;
        this.arrayOut = arrayOut;
    }

    private DivideArray divideArray;

    @Before
    public void init(){
        divideArray = new DivideArray();
    }

    @Test
    public void startTest1(){
        Assert.assertArrayEquals(arrayOut, divideArray.getArray(arrayIn));
    }
}