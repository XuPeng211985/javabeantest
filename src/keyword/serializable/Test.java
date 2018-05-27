package keyword.serializable;

import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * 序列化是把java对象以字节流的形式写入硬盘或传给网络中的其他计算机。
 * 方式一：实现Serializable接口。该接口是一个空借口，仅用于标识该对象可以被序列化，以便jre对其做封装。
 *
 * 方式二：实现 Externalizable接口。该接口继承自Serializable借口，添加了两个方法
 * writeExternal()/readExternal(),在writeExternal()方法中可以指定需要序列化的属性，
 * 实现部分序列化
 *
 * 项目中的引用：在使用tomcat开发JavaEE相关项目的时候，我们关闭tomcat后，相应的session中的对象就存储在了硬盘上，如果我们想要在tomcat重启的
 *          时候能够从tomcat上面读取对应session中的内容，那么保存在session中的内容就必须实现相关的序列化操作。
 */
public class Test{
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        Operate operate=new Operate();
        Person person = new Person("小鹏  ","123456","20");
        System.out.println("序列化之前的相关数据如下:\n"+person.toString());
        operate.serializable(person);
        Person newPerson = operate.deSerializable();
        System.out.println("-------------------------------------------------------");
        System.out.println("反序列化之后的相关数据如下:\n"+newPerson.toString());
    }
}