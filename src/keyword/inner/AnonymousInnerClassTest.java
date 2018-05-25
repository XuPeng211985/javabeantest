package keyword.inner;
import javax.swing.*;
public class AnonymousInnerClassTest {
    public static void main(String[] args) {
        TalkingClockAnonymous clockAnonymous = new TalkingClockAnonymous();
        clockAnonymous.start(1000,true);
        JOptionPane.showConfirmDialog(null,"Quit program");
        System.exit(0);
    }
}
