package com.fujiang.weiji.controller.product;

import com.fujiang.weiji.feign.runoob.OrderFeign;
import com.fujiang.weiji.feign.runoob.ServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Yifan
 * @Description:
 * @date: 2020/4/25
 * Modified By:
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ServiceFeign serviceFeign;
    @Autowired
    private OrderFeign orderFeign;

    @RequestMapping(value = "getConsumer", method = RequestMethod.GET)
    public String getConsumer() {
        return serviceFeign.getProduct();
    }

    @RequestMapping(value = "insert", method = RequestMethod.GET)
    public String insert() {
        return serviceFeign.insert();
    }

    @RequestMapping(value = "insertOrder", method = RequestMethod.GET)
    public String insertOrder() {
        return orderFeign.insert();
    }

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(false);
        lock.lock();
        lock.unlock();

        /*ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<String>(2);*/

        /*LinkedBlockingQueue<String> q = new LinkedBlockingQueue<String>(10000);
        try {
            q.offer("a");
            Boolean b = q.offer("hello2",1000,TimeUnit.MILLISECONDS);
            q.put("sss");
            q.poll();
            q.take();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /*AtomicInteger a = new AtomicInteger(1);
        int b = a.getAndAdd(2);
        System.out.println(a);
        System.out.println(b);
        int c = a.getAndIncrement();
        System.out.println(a);
        System.out.println(c);
        a.compareAndSet(4, 9);
        System.out.println(a);*/
    }

    final int inc(int i) {
        return (++i == 10) ? 0 : i;
    }

}
