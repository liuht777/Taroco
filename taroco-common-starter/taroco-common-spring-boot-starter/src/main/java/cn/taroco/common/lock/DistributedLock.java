package cn.taroco.common.lock;

/**
 * 分布式锁顶级接口
 *
 * @author liuht
 * @date 2018/5/29 14:12
 */
public interface DistributedLock {

    /**
     * 默认超时时间
     */
    long TIMEOUT_MILLIS = 3000;

    int RETRY_TIMES = 2;

    long SLEEP_MILLIS = 100;
    
    boolean lock(String key);

    boolean lock(String key, int retryTimes);

    boolean lock(String key, int retryTimes, long sleepMillis);

    boolean lock(String key, long expire);

    boolean lock(String key, long expire, int retryTimes);

    boolean lock(String key, long expire, int retryTimes, long sleepMillis);

    /**
     * 释放锁
     *
     * @param key key值
     * @return 释放结果
     */
    boolean releaseLock(String key);
}
