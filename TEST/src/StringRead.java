import java.io.BufferedReader;
import java.io.FileReader;

/**
 * ��ȡc��321.txt�ļ��ϵ��ı������
 * @author Administrator
 *
 */
public class StringRead {
	
	public static void main(String[] args) {
		
		try {
			FileReader fr = new FileReader("C:/321.txt");   //�����ı������
			BufferedReader br = new BufferedReader(fr);     //��������Ž�������
			String line = "";
			while( (line = br.readLine()) != null ){        //ͨ��readLine()����ȡһ���ı�������ȡ����󷵻�null
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
