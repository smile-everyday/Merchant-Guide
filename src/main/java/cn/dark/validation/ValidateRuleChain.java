package cn.dark.validation;

import cn.dark.factory.Chain;

/**
 * 验证链抽象类
 *
 * @author dark
 * @date 2019-03-16
 */
public abstract class ValidateRuleChain implements Chain {

    protected ValidateRuleChain next;

    /**
     * 开始验证
     *
     *@param str 需要验证的语句
     *
     */
    public boolean startValidate(String str) {
        boolean isSuccess = this.validate(str);
        if (next != null && isSuccess) {
            isSuccess = next.startValidate(str);
        }

        return isSuccess;
    }

    /**
     * 具体的验证过程
     *
     *@param str 需要验证的语句
     */
    protected abstract boolean validate(String str);

    public void setNext(ValidateRuleChain next) {
        this.next = next;
    }

}
