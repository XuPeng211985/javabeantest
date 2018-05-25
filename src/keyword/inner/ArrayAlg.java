package keyword.inner;
public class ArrayAlg {
    /**
     * 静态内部类只是为了把一个类隐藏在另外一个类的内部
     * 并不需要内部类引用的外围类对象
     * 该例子中由于内部类在静态方法中被实例化，所以必须是静态的
     * 它的作用只有一个，就是把计算出的最大值和最小值包装在一起
     */
    public static class Pair{
        private double min;
        public Pair(double min, double max) {
            this.min = min;
            this.max = max;
        }

        public double getMin() {
            return min;
        }
        public void setMin(double min) {
            this.min = min;
        }
        public double getMax() {
            return max;
        }

        public void setMax(double max) {
            this.max = max;
        }
        private double max;
    }
    public static Pair minmax(double[] values){
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (double v : values) {
            if(min > v)
                 min = v;
            if(max < v)
                max = v;
        }
        return new Pair(min,max);
    }
}
