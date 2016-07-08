import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * 对象序列化
 * @author Administrator
 *
 */
public class ObejctWrite {
	
	public static void main(String[] args) {
		//创建两个Mp3Player对象并初始化title
		Mp3Player p1 = new Mp3Player();
		p1.title = "小苹果";
		Mp3Player p2 = new Mp3Player();
		p2.title = "老男孩";
		
		//序列化两个对象
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
		//重新修改两个对象的title值以便测试储存时状态
		p1.title = "小苹果222";
		p2.title = "老男孩222";
	}

}
