
public class AddressBook {
	
	public String name;  //姓名
	
	public String phone; //电话
	
	public String address; //地址
	
	public String viewAdress(){  //根据姓名获取他的地址
		return this.address;
	}
	
	public static void main(String[] args) {
		
		AddressBook addressBook1 = new AddressBook();
		addressBook1.name = "张三";
		addressBook1.phone = "13111111111";
		addressBook1.address = "四川成都XXXXXXXXX";
		
		System.out.println(addressBook1.viewAdress());    //显示该对象的地址
	}
}
