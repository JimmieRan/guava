import java.io.Serializable;


public class Mp3Player implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String title; //������
	public String artist; //����
	
	//������ڲ���������
	public void play(){
		System.out.println("���ڲ��Ÿ�����"+this.title);
	}
	
	//����
	public static void main(String[] args) {
		//�����������󲢳�ʼ������������䷽��
		Mp3Player song1 = new Mp3Player();
		song1.title = "���к�";
		song1.artist = "�����ֵ�";
		Mp3Player song2 = new Mp3Player();
		song2.title = "Сƻ��";
		song2.artist = "�����ֵ�";
		
		song1.play();
		song2.play();
	}

}
