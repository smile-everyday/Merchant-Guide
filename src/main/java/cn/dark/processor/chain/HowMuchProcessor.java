package cn.dark.processor.chain;

import cn.dark.processor.ProcessorLevel;
import cn.dark.util.SymbolsCalculationUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * 解析how much语句，计算总个数
 *
 * @author dark
 * @date 2019-03-17
 */
public class HowMuchProcessor extends TextProcessChain {

    /**
     * 该处理链的级别，与request中的Level对应，相同则由该处理器处理请求
     */
    public HowMuchProcessor() {
        this.processorLevel = new ProcessorLevel(2);
    }

    @Override
    protected void processLine(String[] arr) {
        checkMap(unitsMap);

        // 获取罗马序列和描述语句
        Map<String, String> map = getRomanWithDescription(arr);


        String romanStr = (String) map.keySet().toArray()[0];
        String descriptionStr = (String) map.values().toArray()[0];

        // 验证罗马序列是否符合规则并计算总个数，首先从缓存取
        Integer totalUnit = ChainResultCache.getTotal(romanStr);
        if (totalUnit == null) {
            boolean isSuccess = ruleChain.startValidate(romanStr);
            if (isSuccess) {
                // 计算总个数
                totalUnit = SymbolsCalculationUtil.compute(romanStr.split(""));

                // 验证成功，放入缓存
                ChainResultCache.put(romanStr, totalUnit);
            } else {
                System.out.println("验证失败！");
                System.exit(0);
            }
        }

        // 将结果存入队列
        descriptionStr = descriptionStr + "is " + totalUnit;
        Queue<String> resultsQueue = ChainResultCache.getResultsQueue();
        resultsQueue.offer(descriptionStr);

    }

    private Map<String, String> getRomanWithDescription(String[] arr) {
        Map<String, String> map = new HashMap<>();

        StringBuilder romanUnitStr = new StringBuilder();
        StringBuilder descriptionStr = new StringBuilder();
        // 从第3个开始就是星系单位
        for (int i = 3; i < arr.length; i++) {
            romanUnitStr.append(unitsMap.get(arr[i]));
            descriptionStr.append(arr[i] + " ");
        }
        map.put(romanUnitStr.toString(), descriptionStr.toString());

        return map;
    }

}
