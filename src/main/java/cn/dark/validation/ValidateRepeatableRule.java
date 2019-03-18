package cn.dark.validation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 重复规则验证，同一字母最多连续重复3次，如果
 * 第三个字母和第四个字母中间隔了一个更小的字母
 * 则可以重复4次（注意这里只验证了不超过4次，没
 * 有检验中间的字母是否更小）
 *
 * @author dark
 * @date 2019-03-17
 */
public class ValidateRepeatableRule extends ValidateRuleChain {

    @Override
    public boolean validate(String str) {
        String[] arr = str.split("");

        // 检验V、L、D是否有重复
        checkNonrepeatableRoman(arr);

        // 字符串长度大于3才需要进行下面的校验
        if (arr.length > 3) {
            // 重复次数超过4则验证失败
            checkRepeatableExceedFour(arr);

            // 验证是否满足重复条件
            return checkRepeatableRule(arr);
        }
        return true;
    }

    /**
     * 检验V、L、D是否有重复
     *
     *@param arr
     *
     */
    private void checkNonrepeatableRoman(String[] arr) {
        Map<String, Integer> nonrepeatableMap = new HashMap<>();
        nonrepeatableMap.put("V", 0);
        nonrepeatableMap.put("L", 0);
        nonrepeatableMap.put("D", 0);

        // 遍历每个字母，如果出现不可重复的则nonrepeatableMap中对应字母值加1
        for (String s : arr) {
            s = s.toUpperCase();
            if (nonrepeatableMap.containsKey(s)) {
                nonrepeatableMap.put(s, nonrepeatableMap.get(s) + 1);
            }
        }

        // 只要任一值大于1，说明有重复的
        Collection<Integer> values = nonrepeatableMap.values();
        for (Integer value : values) {
            if (value > 1) {
                System.out.println("V、L、D不允许重复！");
                System.exit(0);
            }
        }
    }

    /**
     * 检验最大重复数是否超过4次
     *
     *@param arr
     *
     */
    private void checkRepeatableExceedFour(String[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 记录当前字符重复次数
            int count = 0;
            String s = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                String s1 = arr[j];
                if (s1.equals(s)) {
                    count++;
                }
            }

            // 任一字符重复超过4次则退出
            if (count > 4) {
                System.out.println("字符最多只允许重复4次");
                System.exit(0);
            }
        }
    }

    /**
     * 检验是否满足最多重复4次且第三第四字母间需隔一个值
     *
     *@param arr
     *
     */
    private boolean checkRepeatableRule(String[] arr) {
        int len = arr.length;
        for (int head = 0; head < len; head++) {
            String flag = arr[head];
            for (int tail = head + 1; tail < len; tail++) {
                String cursor = arr[tail];
                // 字符不相等且重复次数未达到3次则直接进行下一轮校验
                if (!flag.equals(cursor) && tail != 3) {
                    break;
                }

                // 如果循环到第4个字符且字符相等，说明字符已经连续重复了4次，验证失败
                if (tail == head + 3 && flag.equals(cursor)) {
                    return false;
                }

                // 保证任一字符如果重复4次，只有第三和第四个重复字符之间间隔了一个字符
                if (tail == head + 4 && flag.equals(cursor)) {
                    return true;
                }
            }
        }

        return true;
    }

}
