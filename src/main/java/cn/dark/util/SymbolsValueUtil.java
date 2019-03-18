package cn.dark.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 罗马字母工具类
 *
 * @author dark
 * @date 2019-03-16
 */
public class SymbolsValueUtil {

    /**
     * 罗马字符与数字的对应关系
     */
    private static Map<String, Integer> symbolsPools;

    /**
     * 可组合的字符
     */
    private static Set<String> symbolsCombination;

    static {
        symbolsPools = new HashMap<>();
        symbolsPools.put("I", 1);
        symbolsPools.put("V", 5);
        symbolsPools.put("X", 10);
        symbolsPools.put("L", 50);
        symbolsPools.put("C", 100);
        symbolsPools.put("D", 500);
        symbolsPools.put("M", 1000);

        symbolsCombination = new HashSet<>();
        symbolsCombination.add("IV");
        symbolsCombination.add("IX");
        symbolsCombination.add("XL");
        symbolsCombination.add("XC");
        symbolsCombination.add("CD");
        symbolsCombination.add("CM");
    }

    public static Integer getValue(String symbol) {
        return symbolsPools.get(symbol);
    }

    public static boolean contains(String combationSymbols) {
        return symbolsCombination.contains(combationSymbols);
    }

}
