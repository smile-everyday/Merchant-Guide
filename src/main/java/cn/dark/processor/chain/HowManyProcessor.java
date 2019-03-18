package cn.dark.processor.chain;

import cn.dark.processor.ProcessorLevel;
import cn.dark.util.SymbolsCalculationUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * 解析how many语句，得到商品总价
 *
 * @author dark
 * @date 2019-03-17
 */
public class HowManyProcessor extends TextProcessChain {
    /**
     * 该处理链的级别，与request中的Level对应，相同则由该处理器处理请求
     */
    public HowManyProcessor() {
        this.processorLevel = new ProcessorLevel(3);
    }

    @Override
    protected void processLine(String[] arr) {
        checkMap(unitsMap);

        int len = arr.length;

        Map<String, String> map = getRomanWithDescription(arr);
        String romanStr = (String) map.keySet().toArray()[0];
        String descriptionStr = (String) map.values().toArray()[0];

        // 验证罗马序列是否符合规则并计算总个数
        int count = getCount(romanStr);
        // 获取最终语句
        String resultStr = getDescriptionStr(arr[len - 1], descriptionStr, count);

        // 将结果存入队列
        Queue<String> resultsQueue = ChainResultCache.getResultsQueue();
        resultsQueue.offer(resultStr);
    }

    private Map<String, String> getRomanWithDescription(String[] arr) {
        Map<String, String> map = new HashMap<>();

        StringBuilder romanUnitStr = new StringBuilder();
        StringBuilder descriptionStr = new StringBuilder();
        // 从第4个开始就是星系单位
        for (int i = 4; i < arr.length; i++) {
            // 最后一个是商品
            if (i < arr.length - 1) {
                romanUnitStr.append(unitsMap.get(arr[i]));
            }
            descriptionStr.append(arr[i] + " ");
        }
        map.put(romanUnitStr.toString(), descriptionStr.toString());

        return map;
    }

    private int getCount(String romanStr) {
        //首先从缓存中取
        Integer totalUnit = ChainResultCache.getTotal(romanStr);
        if (totalUnit != null) {
            return totalUnit;
        }

        boolean isSuccess = ruleChain.startValidate(romanStr);
        if (isSuccess) {
            // 计算总数并放入缓存
            totalUnit = SymbolsCalculationUtil.compute(romanStr.split(""));
            ChainResultCache.put(romanStr, totalUnit);
        }
        return totalUnit;
    }

    private String getDescriptionStr(String key, String descriptionStr, int count) {
        Map<String, Object> unitCreditMap = ChainResultCache.getUnitCreditMap();
        checkMap(unitCreditMap);

        // 根据单价和个数求出总credits，并加入结果队列
        int credit = (int) unitCreditMap.get(key);
        int totalCredits = count * credit;
        descriptionStr = descriptionStr + "is " + totalCredits + " Credits";
        return descriptionStr;
    }
}
