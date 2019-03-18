package cn.dark.processor.chain;

import cn.dark.factory.Chain;
import cn.dark.processor.ProcessorLevel;
import cn.dark.processor.Request;
import cn.dark.validation.ValidateRuleChain;
import cn.dark.factory.ValidationFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 以责任链的方式实现对文本的处理
 *
 * @author dark
 * @date 2019-03-16
 */
public abstract class TextProcessChain implements Chain {

    /**
     *处理级别
     */
    protected ProcessorLevel processorLevel;
    /**
     * 指向下一个处理者
     */
    protected TextProcessChain next;
    /**
     * intergalactic units 和 roman numerals的映射
     */
    protected static Map<String, Object> unitsMap = new HashMap<>();
    /**
     * 获取验证链
     */
    protected ValidateRuleChain ruleChain = (ValidateRuleChain) new ValidationFactory().getChain();

    public void setNext(TextProcessChain next) {
        this.next = next;
    }

    /**
     * 根据level级别自动判断该由谁处理请求
     *
     *@param request 请求对象
     *
     */
    public void processRequest(Request request) {
        TextProcessChain current = this;

        // 将文本拆为数组
        String[] lineArr = request.getProcessorLevel().getLine().split(" ");

        // 判断是否应该由当前对象处理
        int requestLevel = request.getProcessorLevel().getLevel();
        int chainLevel = current.processorLevel.getLevel();
        if (requestLevel == chainLevel) {
            current.processLine(lineArr);
        } else {
            // 没有下一个节点且文本未被处理，采用默认的处理方式
            if (next == null) {
                new DefaultProcessor().processLine((String[]) null);
            } else {
                current = next;
                current.processRequest(request);
            }
        }
    }

    protected void checkMap(Map map) {
        if (map.size() == 0) {
            System.out.println("不合法的流程，请确保已提供正确的信息！");
            System.exit(0);
        }
    }

    /**
     * 文本分析处理，由子类实现
     *
     *@param arr 字符串数组
     *
     */
    protected abstract void processLine(String[] arr);
}
