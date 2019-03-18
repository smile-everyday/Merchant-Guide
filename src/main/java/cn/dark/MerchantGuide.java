package cn.dark;

import cn.dark.processor.Processor;
import cn.dark.processor.Request;

import java.io.IOException;

/**
 * @author dark
 * @date 2019-03-17
 */
public class MerchantGuide {

    public static void main(String[] args) {
        String filepath = null;
        if (args.length != 0) {
            filepath = args[0];
        }

        Processor processor = new Processor();
        try {
            processor.processFile(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
