package keyword.inner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
public class TalkingClock {
    private int interval;
    private boolean beep;
    /**
     * 全参构造方法
     * @param interval 间隔时间
     * @param beep 是否有响铃提示
     */
    public TalkingClock(int interval,boolean beep){
        this.beep = beep;
        this.interval = interval;
    }
    public void start(){
        ActionListener listener = new TimePrinter();
        Timer t = new Timer(interval,listener);
        t.start();
    }
    //内部类实现事件接口中的抽象方法 具体实现闹钟的执行逻辑
    public class TimePrinter implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Date now = new Date();
            //根据传入等待时间的大小，不间断的输出时间
            System.out.println("现在是北京时间：" + now);
            //调用外部类的私有属性beep
            if(beep){
                //响铃
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
}
