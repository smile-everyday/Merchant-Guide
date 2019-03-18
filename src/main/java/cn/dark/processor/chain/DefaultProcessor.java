package cn.dark.processor.chain;

/**
 * @author dark
 * @date 2019-03-17
 */
public class DefaultProcessor extends TextProcessChain {

    @Override
    protected void processLine(String[] arr) {
        ChainResultCache.getResultsQueue().offer("I have no idea what you are talking about");
    }
}
