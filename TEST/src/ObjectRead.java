import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * �������л�
 * @author Administrator
 *
 */
public class ObjectRead {

	public static void main(String[] args) {
		
		try {
			FileInputStream input = new FileInputStream("C:/123.txt");
			ObjectInputStream read = new ObjectInputStream(input);
			Mp3Player p1 = (Mp3Player)read.readObject();    //���洢˳�����ζ�ȡ����
			Mp3Player p2 = (Mp3Player)read.readObject();	//����readObject()��ȡ�Ķ�����Object��������ǿ��ת��ΪMp3Player
			
			System.out.println(p1.title);              
			System.out.println(p2.title);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
