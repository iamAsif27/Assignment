package co.hidochr.pune;

/*
  
1.How do you create a deadlock scenario programmatically in Java?
== creating Multiple Thread to form deadlock.

   There are two way to create Thread 
   1. using thread class we create thread but in java multiple inheritance not support in java
    we not use Thread class to create thread in multiple inheritance.
    
   2. Second way to create thread by using Runnable Interface... in interface support multiple inheritance 
    so we used Runnable Interface to create Treads. 
 
 */



public class DeadLockcode {

	public static void main(String[] args) {

		// Shared resources
		Object thread_resource1 = new Object();
		Object thread_resource2 = new Object();

		// Thread 1 obj create of task class
		Thread thread1 = new Thread(new Task(thread_resource1, thread_resource2));

		// Thread 2 object create of task class
		Thread thread2 = new Thread(new Task(thread_resource2, thread_resource1));

		// Start the threads
		thread1.start();   //try to comment first thread and check delay 5sec next thread run
		thread2.start();
	}

	static class Task implements Runnable {
		private final Object thread_resource1;
		private final Object thread_resource2;

		public Task(Object thread_resource1, Object thread_resource2) {
			this.thread_resource1 = thread_resource1;
			this.thread_resource2 = thread_resource2;
		}

		@Override  // Override run method because runnable interface have run method.
		public void run() {
			
			// resource1 is current threat then execute below line otherwise handle exception
			synchronized (thread_resource1) {
				System.out.println(Thread.currentThread().getName() + " acquired lock on resource1");

				try {
					Thread.sleep(5000); 
					// Introduce delay to increase chances of deadlock by 5sec.
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				//sychronized use for thread run synchronize one after another
				synchronized (thread_resource2) {
					System.out.println(Thread.currentThread().getName() + " acquired lock on resource2");
				}
			}
		}
	}
}
