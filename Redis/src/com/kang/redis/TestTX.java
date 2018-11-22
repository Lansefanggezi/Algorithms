package com.kang.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTX {
	public boolean transMethod() throws InterruptedException {
	     Jedis jedis = new Jedis("127.0.0.1", 6379);
	     int balance;// 鍙敤浣欓
	     int debt;// 娆犻
	     int amtToSubtract = 10;// 瀹炲埛棰濆害

	     jedis.watch("balance");
	     //jedis.set("balance","5");//姝ゅ彞涓嶈鍑虹幇锛岃璇炬柟渚裤�傛ā鎷熷叾浠栫▼搴忓凡缁忎慨鏀逛簡璇ユ潯鐩�
	     Thread.sleep(7000);
	     balance = Integer.parseInt(jedis.get("balance"));
	     if (balance < amtToSubtract) {
	       jedis.unwatch();
	       System.out.println("modify");
	       return false;
	     } else {
	       System.out.println("***********transaction");
	       Transaction transaction = jedis.multi();
	       transaction.decrBy("balance", amtToSubtract);
	       transaction.incrBy("debt", amtToSubtract);
	       transaction.exec();
	       balance = Integer.parseInt(jedis.get("balance"));
	       debt = Integer.parseInt(jedis.get("debt"));

	       System.out.println("*******" + balance);
	       System.out.println("*******" + debt);
	       return true;
	     }
	  }

	  public static void main(String[] args) throws InterruptedException {
	     TestTX test = new TestTX();
	     boolean retValue = test.transMethod();
	     System.out.println("main retValue-------: " + retValue);
	  }	
}
