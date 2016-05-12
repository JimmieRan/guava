import java.io.BufferedReader;
import java.io.FileReader;

/**
 * 读取c盘321.txt文件上的文本并输出
 * @author Administrator
 *
 */
public class StringRead {
	
	public static void main(String[] args) {
		
		try {
			FileReader fr = new FileReader("C:/321.txt");   //创建文本输出流
			BufferedReader br = new BufferedReader(fr);     //将输出流放进缓冲区
			String line = "";
			while( (line = br.readLine()) != null ){        //通过readLine()来读取一行文本，当读取到最后返回null
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
