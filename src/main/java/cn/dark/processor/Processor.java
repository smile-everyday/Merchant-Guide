package cn.dark.processor;

import cn.dark.factory.ProcessorChainFactory;
import cn.dark.processor.chain.ChainResultCache;
import cn.dark.processor.chain.TextProcessChain;

import java.io.*;
import java.util.Iterator;
import java.util.Queue;

/**
 * 解析者接口
 *
 * @author dark
 * @date 2019-03-17
 */
public class Processor {

    /**
     * 解析文件
     *
     *@param filepath 文件路径，可以自己从启动参数传入，不传则默认使用当前类路径下的input文件
     *@return void
     *@date 2019-03-18
     *
     */
    public void processFile(String filepath) throws IOException {
        // 获取解析链
        TextProcessChain chain = (TextProcessChain) new ProcessorChainFactory().getChain();

        // 开启字符流从文件中读取文本
        BufferedReader br = null;
        if (filepath == null) {
            InputStream input = Processor.class.getResourceAsStream("input.txt");
            br = new BufferedReader(new InputStreamReader(input));
        } else {
            br = new BufferedReader(new FileReader(filepath));
        }

        // 将每一行封装为一个请求，让解析链解析
        String line = null;
        while ((line = br.readLine()) != null) {
            Request request = new Request(line);
            chain.processRequest(request);
        }

        Queue<String> resultsQueue = ChainResultCache.getResultsQueue();
        Iterator<String> iterator = resultsQueue.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }
        br.close();

    }

}
