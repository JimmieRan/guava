
public class AddressBook {
	
	public String name;  //����
	
	public String phone; //�绰
	
	public String address; //��ַ
	
	public String viewAdress(){  //����������ȡ���ĵ�ַ
		return this.address;
	}
	
	public static void main(String[] args) {
		
		AddressBook addressBook1 = new AddressBook();
		addressBook1.name = "����";
		addressBook1.phone = "13111111111";
		addressBook1.address = "�Ĵ��ɶ�XXXXXXXXX";
		
		System.out.println(addressBook1.viewAdress());    //��ʾ�ö���ĵ�ַ
	}
}
