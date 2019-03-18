package cn.dark.factory;

import cn.dark.processor.chain.*;

/**
 * 获取解析链
 *
 * @author dark
 * @date 2019-03-18
 */
public class ProcessorChainFactory implements ChainFactory {

    @Override
    public Chain getChain() {
        TextProcessChain unitConversion = new UnitConversionProcessor();
        TextProcessChain unitCredit = new UnitCreditProcessor();
        TextProcessChain howMuch = new HowMuchProcessor();
        TextProcessChain howMany = new HowManyProcessor();

        unitConversion.setNext(unitCredit);
        unitCredit.setNext(howMuch);
        howMuch.setNext(howMany);

        return unitConversion;
    }

}
