package cn.dark.validation;

import cn.dark.util.SymbolsValueUtil;

import java.util.*;

/**
 * 验证字母的组合是否符合规则
 *
 * @author dark
 * @date 2019-03-17
 */
public class ValidateCombationRule extends ValidateRuleChain {
    /**
     * 用来验证是否存在任意字符被两个或两个以上的小字符减
     */
    private Map<String, String> map = new HashMap<>();

    @Override
    protected boolean validate(String str) {
        boolean isSuccess = true;
        for (int i = 0; i < str.length() - 1; i++) {
            String childStr = str.substring(i, i +  2);

            // 如果是小在前需要验证减法规则
            String[] childArr = childStr.split("");
            Integer left = SymbolsValueUtil.getValue(childArr[0].toUpperCase());
            Integer right = SymbolsValueUtil.getValue(childArr[1].toUpperCase());
            if (left < right) {
                // 将减法组合以及被减数放入map
                map.put(childStr, str.substring(1));

                // 验证是否可以做减数
                checkSubstractable(childStr);
                // 验证减法组合
                isSuccess = checkCombation(childStr);
            }

        }

        checkSubstractedRule();

        return isSuccess;
    }

    /**
     * 检验是否满足字母的减法规则
     *
     *@param childStr 两个连续字母的子序列
     *
     */
    private boolean checkCombation(String childStr) {
        boolean isSuccess = SymbolsValueUtil.contains(childStr);
        if (!isSuccess) {
            System.out.println("不满足罗马字符的配对规则！");
            System.exit(0);
        }
        return isSuccess;
    }

    /**
     * 检验L、D、V是否做了减数
     *
     *@param childStr
     *
     */
    private void checkSubstractable(String childStr) {
        if (childStr.startsWith("L") || childStr.startsWith("D") || childStr.startsWith("V")) {
            System.out.println("V、L、D不能做减数");
            System.exit(0);
        }
    }

    /**
     * 检验同一个值不能与两个不同的值相减
     *
     */
    private void checkSubstractedRule() {
        Collection<String> values = map.values();

        // 将value放入set去重，如果两个size不同说明存在相同的被减数，
        // 但减数不同的，验证失败
        Set<String> set = new HashSet<>();
        set.addAll(values);
        if (values.size() != set.size()) {
            System.out.println("同一个被减数不能与两个不同的减数相减");
            System.exit(0);
        }
    }

}
