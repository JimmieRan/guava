
public class Father {
	
	public void hair(){
		System.out.println("黑黑的头发");
	}

}


//定义一个鸟类
class Bird {
	public void fly(){
		System.out.println("在天空翱翔");
	}
}

//定义一个鸭子类继承鸟类
class Duck extends Bird{
	//覆盖父类的fly()
	@Override
	public void fly(){
		System.out.println("在水中扑腾几下");
	}
	
	//重载鸭子类fly()方法
	private String fly( String num ){
		System.out.println("在水中扑腾几下");
		return num;
	}
}