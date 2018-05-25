package keyword.inner;
import java.util.Arrays;
public class StaticInnerClassTest {
    public static void main(String[] args) {
       double[] d = new double[20];
        for (int i = 0; i < d.length; i++) {
            d[i] = 100 * Math.random();
        }
        System.out.println(Arrays.toString(d));
        ArrayAlg.Pair pair = ArrayAlg.minmax(d);
        System.out.println("min: " + pair.getMin());
        System.out.println("max: " + pair.getMax());
    }
}
