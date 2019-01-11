package linkjb.webSocket;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import java.util.List;
import java.util.concurrent.*;

public class JsonConfigUtils {
    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(
            50,   //核心池大小
            100,   //最大
            3, TimeUnit.SECONDS,    //回收空闲时间
            new ArrayBlockingQueue<Runnable>(5), //缓冲池
            new ThreadPoolExecutor.AbortPolicy());



    public static JsonConfig getJsonConfig(final List<String> propertys){

        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setJsonPropertyFilter(new PropertyFilter(){
            public boolean apply(Object object, String property, Object value) {
                if(object instanceof OnlineUser ){
                    if (propertys.contains(property)){
                        //返回true属性不会被序列化
                        return true;
                    }
                }
                return false;
            }
        });
        return jsonConfig;
    }
    public static void main(String[] args){
        ExecutorService pool = Executors.newCachedThreadPool();


    }

}
