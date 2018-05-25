package oopfeature.abstresctorinterface;

public class Son extends AbstractTest implements  InterfaceTest {
    @Override
    void fun() {
        System.out.println("这个是父类的抽象方法实现");
    }

    @Override
    public void fun2() {
        System.out.println("这个是接口抽象方法的具体实现");
    }
}
