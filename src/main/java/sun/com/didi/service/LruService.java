package sun.com.didi.service;

import org.springframework.stereotype.Service;
import sun.com.didi.util.LruCacheUtil;

@Service
public class LruService {
    static   LruCacheUtil cache = new LruCacheUtil(2);
    private int sum=1;
    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public LruCacheUtil methods(String company){
        cache.put(sum,company);
        sum=sum+1;
        setSum(sum);
        return cache;
    }
}
