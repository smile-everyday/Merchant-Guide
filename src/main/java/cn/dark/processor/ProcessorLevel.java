package cn.dark.processor;

/**
 * 解析链依赖于此级别，请求级别与解析者级别相同，解析者
 * 才能处理该请求
 *
 * @author dark
 * @date 2019-03-17
 */
public class ProcessorLevel {

    private int level = -1;
    private String line;

    public ProcessorLevel(int level) {
        this.level = level;
    }

    public ProcessorLevel(String line) {
        this.line = line.replace("?", "");

        String[] arr = line.split(" ");
        if (arr.length == 3 && arr[1].equals(TextConstant.IS)) {
            this.level = 0;
        }

        String newLine = line.toLowerCase();
        if (newLine.endsWith(TextConstant.CREDITS)) {
            this.level = 1;
        }

        if (newLine.startsWith(TextConstant.HOW_MUCH_IS)) {
            this.level = 2;
        }

        if (newLine.startsWith(TextConstant.HOW_MANY_CREDITS_IS)) {
            this.level = 3;
        }

    }

    public int getLevel() {
        return this.level;
    }

    public String getLine() {
        return this.line;
    }

}
