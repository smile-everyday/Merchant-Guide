package cn.dark.processor.chain;

import cn.dark.processor.ProcessorLevel;
import cn.dark.util.SymbolsCalculationUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 解析商品价格描述语句，如：glob glob Silver is 34 Credits
 * 解析后 II 个Silver为34Credits，即一个Silver为17Credits
 *
 * @author dark
 * @date 2019-03-16
 */
public class UnitCreditProcessor extends TextProcessChain {

    /**
     * 该处理链的级别，与request中的Level对应，相同则由该处理器处理请求
     */
    public UnitCreditProcessor() {
        this.processorLevel = new ProcessorLevel(1);
    }

    @Override
    protected void processLine(String[] arr) {
        // 检查是否已设置单位转换关系
        checkMap(unitsMap);

        // 获取罗马字符序列
        String romanStr = getRomanStr(arr);
        // 获取商品和商品总的credits
        Map<String, Integer> commodityMap = getCommodity(arr);
        // 计算得到商品总个数
        int countNum = getCountNum(romanStr);
        // 得到商品单价
        int unitCredit = getUnitCredit(commodityMap, countNum);

        // 将商品和商品单价缓存
        Map<String, Object> unitCreditMap = ChainResultCache.getUnitCreditMap();
        String commodity = (String) commodityMap.keySet().toArray()[0];
        unitCreditMap.put(commodity, unitCredit);
    }



    private String getRomanStr(String[] arr) {
        // 存储解析后的罗马字符序列
        StringBuilder sb = new StringBuilder();
        for (String str : arr) {
            // 这里不仅仅只有星系个数单位，所以需要判断
            Object unit = unitsMap.get(str);
            if (unit != null) {
                sb.append(unit);
            }
        }

        return sb.toString();
    }

    private Map<String, Integer> getCommodity(String[] arr) {
        // 商品
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            String str = arr[i];
            // is 前一个就是商品，后一个就是总credits
            if ("is".equals(str)) {
                String commodity = arr[i - 1];
                int credits = Integer.valueOf(arr[i + 1]);

                map.put(commodity, credits);
            }
        }

        return map;
    }

    private int getCountNum(String romanStr) {
        // 首先从缓存取
        Integer total = ChainResultCache.getTotal(romanStr);
        if (total != null) {
            return total;
        }

        boolean isSuccess = ruleChain.startValidate(romanStr);
        // 验证成功后根据罗马字符或数字的对应关系获取总个数
        if (isSuccess) {
            total = SymbolsCalculationUtil.compute(romanStr.split(""));
            // 验证成功的字符序列放到缓存
            ChainResultCache.put(romanStr, total);
        }

        return total;
    }

    private int getUnitCredit(Map<String, Integer> commodityMap, int count) {

        int credits = (int) commodityMap.values().toArray()[0];
        return credits / count;
    }
}
