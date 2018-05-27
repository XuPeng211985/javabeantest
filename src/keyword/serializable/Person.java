package keyword.serializable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.text.SimpleDateFormat;
import java.util.Date;

class Person implements Externalizable {
    private static final long serialVersionUID = 1L;
    String userName;
    String password;
    String age;
    public Person(String userName, String password, String age) {
        super();
        this.userName = userName;
        this.password = password;
        this.age = age;
    }
    public Person() {
        super();
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * 序列化操作的扩展类
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        //增加一个新的对象
        Date date=new Date();
        out.writeObject(userName);
        out.writeObject(password);
        out.writeObject(date);
    }
    /**
     * 反序列化的扩展类
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        //注意这里的接受顺序是有限制的哦，否则的话会出错的
        // 例如上面先write的是A对象的话，那么下面先接受的也一定是A对象...
        userName=(String) in.readObject();
        password=(String) in.readObject();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=(Date)in.readObject();
        System.out.println("反序列化后的日期为:"+sdf.format(date));
    }
    @Override
    public String toString() {
        //注意这里的年龄是不会被序列化的，所以在反序列化的时候是读取不到数据的
        return "用户名:"+userName+"密 码: "+password+"年龄:"+age;
    }
}