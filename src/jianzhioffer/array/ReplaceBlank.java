package jianzhioffer.array;
/**
 * 题目描述 ：用 20% 替换一个字符串中的空格
 *   思路1：从前往后遍历字符串，遇到一个空格就将空格后面的所有字符
 *          都往后移2位，然后在将移动后空开的三个空格替换为20%
 *          直到遍历完所有的字符为止。由于每次遇到空格都要移动一部分字符
 *          所以该方法的时间复杂度是O（n^2）
 *   思路2：首先定义两个指针，第一个指针指向该字符串的最后一个字符‘\0’
 *          第二个指针指向替换所有空格后字符串的最后一个字符所在的位置
 *          让第一个指针不断往前移动，把不等于空格的字符移动到第二个字符所在的位置
 *          然后让第二个字符向前移，当第一个指针遇到空格时，第二个指针连续向前移三位
 *          并且把20%放在三个空位的地方，直到第一个指针移动到字符串的第一个字符位置
 */
public class ReplaceBlank {
    public static void main(String[] args) {
        char[] str = new char[20];
         str[0] = 'w';
        str[1] = 'e';
        str[2] = ' ';
        str[3] = 'a';
        str[4] = 'r';
        str[5] = 'e';
        str[6] = ' ';
        str[7] = 'h';
        str[8] = 'a';
        str[9] = 'p';
        str[10] = 'p';
        str[11] = 'y';
        str[12] = '\0';
          ReplaceBlacnk(str);
    }
    public static void ReplaceBlacnk(char[] str){
         if(str == null || str.length <= 0){
             return;
         }
         int startIndex = 0;
         int blackNumber = 0;
          while(str[startIndex] != '\0'){
              startIndex++;
              if(str[startIndex] == ' ')
                  blackNumber++;
          }
          int endIndex = startIndex + 2 * blackNumber;
           if(endIndex > str.length){
               return;
           }
          while(startIndex >= 0 && endIndex > startIndex){
              if(str[startIndex] != ' '){
                  str[endIndex--] = str[startIndex];
              }else{
                  str[endIndex--] = '%';
                  str[endIndex--] = '0';
                  str[endIndex--] = '2';
              }
              startIndex--;
          }
    }
}
