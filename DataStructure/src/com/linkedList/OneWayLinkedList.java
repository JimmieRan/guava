package com.linkedList;

public class OneWayLinkedList {

	/**
	 * 定义一个LNode类来存储节点的数据值和下一个节点的指针引用
	 */
	class LNode{
		
		private String data;  //存储数据值
		
		private int post;    //当前节点位置索引从0开始
		
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
	
	//初始化链表头HEAD
	public boolean initHeadNode( String value ){
		if( head == null ){
			 head = new LNode(value);
			 head.setPost(0);
			 size++;
			 return false;
		 }else{
			 return true;
		 }
	}

	//添加节点-尾插法
	 public void add( String value ){
		 LNode ptr = null;    //当前节点
		 LNode pnext = null;  //下一节点
		 if( initHeadNode(value) ){
			 ptr = head;
			 pnext = ptr.next;
			 while( pnext!=null ){
				 ptr = pnext;
				 pnext = ptr.next;
			 }
			 pnext = new LNode(value);
			 pnext.setPost(ptr.getPost()+1);
			 ptr.setNext(pnext);
			 size++;
		 }
	 }
	 
	//添加节点-指定位置添加节点
	 public void add( int post, String value ){
		if( post > this.size ){
			System.out.println("Exception——IndexOutOfBoundsException：POST："+post+", SIZE："+size);
		}else if( post == this.size ){
			this.add(value);
		}else{
			LNode ptr = null;    //当前节点
			LNode pnext = null;  //下一节点
			LNode newNode = null;
			if( initHeadNode(value) ){
				 ptr = head;
				 pnext = ptr.next;
				 while( pnext!=null ){
					 if( post == 0 ){
						 head = new LNode(value);
						 head.setPost(0);
						 head.setNext(ptr);
						 pnext = head.next;
						//更新新节点后面的节点位置索引+1
						 while( pnext!=null ){
							 ptr = pnext;
							 pnext.setPost(ptr.getPost()+1);
							 pnext = ptr.next;
						 }
						 size++;
						 break;
					 }else if( pnext.post == post ){
						 newNode = new LNode(value);   //初始化新节点
						 newNode.setPost(pnext.post);
						 ptr.setNext(newNode);      
						 ptr.getNext().setNext(pnext);  //更新后继节点关系
						//更新新节点后面的节点位置索引+1
						 while( pnext!=null ){
							 ptr = pnext;
							 pnext.setPost(ptr.getPost()+1);
							 pnext = ptr.next;
						 }
						 size++;
						 break;
					 }
					 ptr = pnext;
					 pnext = ptr.next;
				 }
			}
		}
	 }
	 
	 
	 //通过索引获取当前节点
	 public LNode get( int post ){
		 LNode ptr = null;    //当前节点
		 LNode pnext = null;  //下一节点
		 
		 ptr = head;
		 pnext = ptr.next;
		 while( ptr!=null ){
			 if( ptr.post == post ){
				 return ptr;
			 }
			 ptr = pnext;
			 pnext = ptr.next;
		 }
		 return null;
	 }
	 
	 //删除指定索引位置上的节点
	 public void remove( int post ){
		 
	 }
	 
	 public void printLink(){
		 LNode pnext = null;
		 if( head != null ){
			 pnext = head.next;
			 System.out.println("date："+head.data+"  post："+head.post);
		 }
		 while( pnext != null ){
			 System.out.println("date："+pnext.data+"  post："+pnext.post);
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
		 owll.add(3,"a6");
		 owll.printLink();
		 System.out.println("SIZE：" + owll.getSize());
		 
		 LNode node = owll.get(3);
		 if( null!=node ){
			 System.out.println(node.getData());
		 }
	}
	
}
