package oopfeature.polymorphic;

public class Person {
    public void f1(){
        System.out.println("Person-->f1");
    }
    //静态方法不能被子类覆盖重写
    public static void f2(int i){
        System.out.println("Person-->f2 :" + i);
    }
}
