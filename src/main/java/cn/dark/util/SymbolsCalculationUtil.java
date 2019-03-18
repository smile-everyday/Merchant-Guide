package cn.dark.util;

/**
 * 罗马字符计算
 *
 * @author dark
 * @date 2019-03-17
 */
public class SymbolsCalculationUtil {

    public static int compute(String[] arr) {
        // 负数总数
        int minusCount = 0;
        // 正数总数
        int addCount = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            // 两两比较，每次将左值计入相应的计数器
            Integer left = SymbolsValueUtil.getValue(arr[i]);
            Integer right = SymbolsValueUtil.getValue(arr[i + 1]);
            if (left < right) {
                minusCount -= left;
            } else {
                addCount += left;
            }

            // 最后还剩一个始终是正数
            if (i == arr.length - 2) {
                addCount += right;
            }
        }
        return addCount + minusCount;
    }

}
