
public class Father {
	
	public void hair(){
		System.out.println("�ںڵ�ͷ��");
	}

}


//����һ������
class Bird {
	public void fly(){
		System.out.println("����հ���");
	}
}

//����һ��Ѽ����̳�����
class Duck extends Bird{
	//���Ǹ����fly()
	@Override
	public void fly(){
		System.out.println("��ˮ�����ڼ���");
	}
	
	//����Ѽ����fly()����
	private String fly( String num ){
		System.out.println("��ˮ�����ڼ���");
		return num;
	}
}