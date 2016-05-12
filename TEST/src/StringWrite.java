import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * 将文本写入c盘的321.txt文件
 * @author Administrator
 *
 */
public class StringWrite {

	public static void main(String[] args) {
		
		try {
			FileWriter fw = new FileWriter("C:/321.txt");   //创建文本写入流并定义路径
			BufferedWriter bw = new BufferedWriter(fw);     //将写入流装进一个写入缓冲区
			bw.write("1111111");                            //利用write("")方法写入文本，newLine()方法换行
			bw.newLine();
			bw.write("2222222");
			bw.newLine();
			bw.write("3333333");
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
