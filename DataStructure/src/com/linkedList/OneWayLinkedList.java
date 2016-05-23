package com.linkedList;

public class OneWayLinkedList {

	/**
	 * 定义一个LNode类来存储节点的数据值和下一个节点的指针引用
	 */
	class LNode{
		
		private String data;  //存储数据值
		
		private int post;    //当前节点位置
		
		private LNode next;  //指向下一节点的引用
		
		public LNode( String data ){
			this.data = data;
		}
		
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
		public LNode getNext() {
			return next;
		}
		public void setNext(LNode next) {
			this.next = next;
		}
		public int getPost() {
			return post;
		}
		public void setPost(int post) {
			this.post = post;
		}
	}
	
	private LNode head;   //定义一个节点头
	private int size;    //定义一个长度
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	//添加节点-尾插法
	 public void add( String value ){
		 LNode ptr = null;    //当前节点
		 LNode pnext = null;  //下一节点
		 if( head == null ){
			 head = new LNode(value);
			 size++;
		 }else{
			 ptr = head;
			 pnext = ptr.next;
			 while( pnext!=null ){
				 ptr = pnext;
				 pnext = ptr.next;
			 }
			 pnext = new LNode(value);
			 ptr.setNext(pnext);
			 size++;
		 }
	 }
	 
	 public void printLink(){
		 LNode pnext = null;
		 if( head != null ){
			 pnext = head.next;
			 System.out.println(head.data);
		 }
		 while( pnext != null ){
			 System.out.println(pnext.data);
			 pnext = pnext.next;
		 }
	 }
	 
	 
	 public static void main(String[] args) {
		 OneWayLinkedList owll = new OneWayLinkedList();
		 owll.add("a1");
		 owll.add("a2");
		 owll.add("a3");
		 owll.add("a4");
		 owll.add("a5");
		 owll.add("a6");
		 owll.printLink();
		 System.out.println( owll.getSize());
	}
	
}
