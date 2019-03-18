package cn.dark.processor.chain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 责任链共享对象
 *
 * @author dark
 * @date 2019-03-16
 */
public class ChainResultCache {

    /**
     *单个商品的credit
     */
    private static Map<String, Object> unitCreditMap = new HashMap<>();

    /**
     * 计算结果队列
     */
    private static Queue<String> resultsQueue = new LinkedList();

    /**
     * 缓存已经验证过的序列和计算的结果
     */
    private static Map<String, Integer> cacheMap = new HashMap<>();

    static Map<String, Object> getUnitCreditMap() {
        return unitCreditMap;
    }

    static void put(String romanStr, int totalUnit) {
        cacheMap.put(romanStr, totalUnit);
    }

    static Integer getTotal(String romanStr) {
        return cacheMap.get(romanStr);
    }

    public static Queue<String> getResultsQueue() {
        return resultsQueue;
    }

}
