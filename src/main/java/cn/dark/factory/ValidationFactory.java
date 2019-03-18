package cn.dark.factory;

import cn.dark.validation.ValidateCombationRule;
import cn.dark.validation.ValidateRepeatableRule;
import cn.dark.validation.ValidateRuleChain;

/**
 * 获取验证链
 *
 * @author dark
 * @date 2019-03-17
 */
public class ValidationFactory implements ChainFactory {

    @Override
    public Chain getChain() {
        ValidateRuleChain repeatableRule = new ValidateRepeatableRule();
        ValidateRuleChain combationRule = new ValidateCombationRule();
        repeatableRule.setNext(combationRule);

        return repeatableRule;
    }
}
