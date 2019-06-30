public class Test1 {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Start");
    }

    @Test(priority = 2)
    public void test1(){
        int a = 3+5;
        System.out.println(a);
    }

    @Test(priority = 1)
    public void test2(){
        String one = "1";
        String two = "2";
        System.out.println(one + two);
    }

    @Test(priority = 3)
    public void test3(){
        int a = 15;
        int b = 5;
        System.out.println(a/b);
    }

    @AfterSuite
    public void AfterSuite(){
        System.out.println("End");
    }

}
