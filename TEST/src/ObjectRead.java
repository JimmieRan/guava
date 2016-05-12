import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * 对象反序列化
 * @author Administrator
 *
 */
public class ObjectRead {

	public static void main(String[] args) {
		
		try {
			FileInputStream input = new FileInputStream("C:/123.txt");
			ObjectInputStream read = new ObjectInputStream(input);
			Mp3Player p1 = (Mp3Player)read.readObject();    //按存储顺序依次读取对象
			Mp3Player p2 = (Mp3Player)read.readObject();	//由于readObject()读取的对象是Object类型所以强制转换为Mp3Player
			
			System.out.println(p1.title);              
			System.out.println(p2.title);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
