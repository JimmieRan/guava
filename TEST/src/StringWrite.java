import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * ���ı�д��c�̵�321.txt�ļ�
 * @author Administrator
 *
 */
public class StringWrite {

	public static void main(String[] args) {
		
		try {
			FileWriter fw = new FileWriter("C:/321.txt");   //�����ı�д����������·��
			BufferedWriter bw = new BufferedWriter(fw);     //��д����װ��һ��д�뻺����
			bw.write("1111111");                            //����write("")����д���ı���newLine()��������
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
