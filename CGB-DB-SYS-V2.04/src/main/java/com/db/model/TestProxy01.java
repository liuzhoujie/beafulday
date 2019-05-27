package com.db.model;



interface MessgaeService{
	void Sendmassage(String msg);
}

class MessageServiceImpl implements MessgaeService{

	@Override
	public void Sendmassage(String msg) {
		System.out.println(msg);
	}
	
	
	public void Sendmassage222(String msg) {
		System.out.println("hahahha");
	}
	
}
//兄弟类：
/**
 * 优势：耦合性较低，扩展类的个数会减少
 * 劣势：代码的冗余会比较高，没个接口都需要自己创建类
 * @author TEDU
 *
 */
class ProxyMessgaeServiceImpl implements MessgaeService{
   private MessgaeService messageServiceImpl;
   public ProxyMessgaeServiceImpl(MessgaeService messageServiceImpl) {
	  this.messageServiceImpl =messageServiceImpl;
   }
	@Override
	public void Sendmassage(String msg) {
		System.out.println("A");
		messageServiceImpl.Sendmassage(msg);
		System.out.println("B");
	}
	
}
public class TestProxy01 {
	public static void main(String[] args) {
		MessgaeService ms = new MessageServiceImpl();
		MessgaeService pms = new ProxyMessgaeServiceImpl(ms);
		pms.Sendmassage("gg");
	}
	
}
