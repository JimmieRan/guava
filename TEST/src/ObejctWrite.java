import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * �������л�
 * @author Administrator
 *
 */
public class ObejctWrite {
	
	public static void main(String[] args) {
		//��������Mp3Player���󲢳�ʼ��title
		Mp3Player p1 = new Mp3Player();
		p1.title = "Сƻ��";
		Mp3Player p2 = new Mp3Player();
		p2.title = "���к�";
		
		//���л���������
		try {
			FileOutputStream out = new FileOutputStream("C:/123.txt");
			ObjectOutputStream writer = new ObjectOutputStream(out);
			writer.writeObject(p1);
			writer.writeObject(p2);
			writer.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//�����޸����������titleֵ�Ա���Դ���ʱ״̬
		p1.title = "Сƻ��222";
		p2.title = "���к�222";
	}

}
