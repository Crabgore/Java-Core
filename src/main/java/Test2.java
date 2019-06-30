public class Test2 {

    @Test(priority = 2)
    public void test1(){
        int a = 3;
        int b = 7;
        System.out.println(a+b);
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

    @Test(priority = 4)
    public void test4(){
        int a = 458;
        int b = 582;
        System.out.println(a+b);
    }

    @Test(priority = 8)
    public void test5(){
        int a = 15;
        int b = 15;
        System.out.println(a*b);
    }

    @Test(priority = 6)
    public void test6(){
        String one = "метод";
        String two = "номер 7";
        System.out.println(one + " " + two);
    }

    @Test(priority = 7)
    public void test7(){
        String one = "привет";
        String two = "джава";
        System.out.println(one + " " + two);
    }

    @Test(priority = 5)
    public void test8(){
        String one = "масло";
        String two = "маслянное";
        System.out.println(one + " " + two);
    }

    @AfterSuite
    public void AfterSuite(){
        System.out.println("End");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Start");
    }
}
