import java.io.Serializable;


public class Mp3Player implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String title; //歌曲名
	public String artist; //歌手
	
	//输出正在播出歌曲名
	public void play(){
		System.out.println("正在播放歌曲："+this.title);
	}
	
	//测试
	public static void main(String[] args) {
		//创建两个对象并初始化变量后调用其方法
		Mp3Player song1 = new Mp3Player();
		song1.title = "老男孩";
		song1.artist = "筷子兄弟";
		Mp3Player song2 = new Mp3Player();
		song2.title = "小苹果";
		song2.artist = "筷子兄弟";
		
		song1.play();
		song2.play();
	}

}
