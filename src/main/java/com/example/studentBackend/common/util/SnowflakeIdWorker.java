package com.example.studentBackend.common.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * 雪花算法工作类
 */
@Component
public class SnowflakeIdWorker {
    private final long twepoch = 1581582308098L;
    public static final long WORKER_ID_BITS = 9L;
    private final long datacenterIdBits = 1L;
    private final long maxWorkerId = 511L;
    private final long maxDatacenterId = 1L;
    private final long sequenceBits = 12L;
    private final long workerIdShift = 12L;
    private final long datacenterIdShift = 21L;
    private final long timestampLeftShift = 22L;
    private final long sequenceMask = 4095L;
    private long workerId;
    private long datacenterId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    // 无参构造函数
    public SnowflakeIdWorker() {
        this.workerId = 0L; // 默认 workerId
        this.datacenterId = 0L; // 默认 datacenterId
    }

    public SnowflakeIdWorker(long workerId, long datacenterId) {
        if (workerId <= 511L && workerId >= 0L) {
            if (datacenterId <= 1L && datacenterId >= 0L) {
                this.workerId = workerId;
                this.datacenterId = datacenterId;
            } else {
                throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", 1L));
            }
        } else {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", 511L));
        }
    }

    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (timestamp < this.lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp));
        } else {
            if (this.lastTimestamp == timestamp) {
                this.sequence = this.sequence + 1L & 4095L;
                if (this.sequence == 0L) {
                    timestamp = this.tilNextMillis(this.lastTimestamp);
                }
            } else {
                this.sequence = 0L;
            }

            this.lastTimestamp = timestamp;
            return timestamp - 1581582308098L << 22 | this.datacenterId << 21 | this.workerId << 12 | this.sequence;
        }
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp;
        for(timestamp = this.timeGen(); timestamp <= lastTimestamp; timestamp = this.timeGen()) {
        }

        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) throws Exception {
        int threadNum = 10;
        int idNum = 100;
        long startTime = System.currentTimeMillis();
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0L, 0L);
        ThreadFactory namedThreadFactory = (new ThreadFactoryBuilder()).setNameFormat("thread-snowflake-runner-%d").build();
        ExecutorService fixedThreadPool = new ThreadPoolExecutor(threadNum, 20, 200L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), namedThreadFactory);
        CountDownLatch latch = new CountDownLatch(idNum);

        for(int i = 0; i < idNum; ++i) {
            fixedThreadPool.submit(() -> {
                long id = idWorker.nextId();
                System.out.println(id);
                latch.countDown();
            });
        }

        latch.await();
        fixedThreadPool.shutdown();
        System.out.println(Thread.currentThread().getName() + "耗费时间" + (System.currentTimeMillis() - startTime));
    }
}
