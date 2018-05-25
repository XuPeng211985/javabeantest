package keyword.inner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
public class TalkingClockAnonymous {
    public void start(int interval,final boolean beep){
        //匿名内部类实现函数的回调 这样和用户层就实现了完全解耦
        //只需要用listener接受到输出时间 和 响铃这个动作即可，而不需要关注一些实现细节
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date now = new Date();
                //根据传入等待时间的大小，不间断的输出时间
                System.out.println("现在是北京时间：" + now);
                if(beep){
                    //响铃
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        };
        Timer t =  new Timer(interval,listener);
        t.start();
    }
}
