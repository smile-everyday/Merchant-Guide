package cn.dark.processor.chain;

import cn.dark.processor.ProcessorLevel;
import cn.dark.processor.TextConstant;

/**
 * 解析货币单位描述语句，将星际单位作为Key，
 * 罗马字符作为键存入map中，如：glob is I
 * 解析后为glob-I
 *
 * @author dark
 * @date 2019-03-16
 */
public class UnitConversionProcessor extends TextProcessChain {

    /**
     * 该处理链的级别，与request中的Level对应，相同则由该处理器处理请求
     */
    public UnitConversionProcessor() {
        this.processorLevel = new ProcessorLevel(0);
    }

    @Override
    protected void processLine(String[] arr) {
        // 将文本描述的星系单位与罗马字符的映射关系存入map
        unitsMap.put(arr[0], arr[2]);
    }

}
