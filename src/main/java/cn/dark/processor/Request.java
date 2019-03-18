package cn.dark.processor;

/**
 * 请求类，将每一行文本封装为一个请求交给链处理
 *
 * @author dark
 * @date 2019-03-17
 */
public class Request {

    private ProcessorLevel processorLevel;

    public Request(String line) {
        this.processorLevel = new ProcessorLevel(line);
    }



    public ProcessorLevel getProcessorLevel() {
        return this.processorLevel;
    }

}
