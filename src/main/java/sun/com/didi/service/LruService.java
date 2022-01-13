package sun.com.didi.service;

import org.springframework.stereotype.Service;
import sun.com.didi.util.LruCacheUtil;

@Service
public class LruService {
    static   LruCacheUtil<Integer,String> lru = new LruCacheUtil<Integer,String>(4);
    private int sum=0;

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public LruCacheUtil methods(String company){

        lru.put(sum,company);
        sum=sum+1;
        setSum(sum);
        return lru;
    }
}
