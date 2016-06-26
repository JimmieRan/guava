package com.linkedList;

public class OneWayLinkedList {

	/**
	 * ����һ��LNode�����洢�ڵ������ֵ����һ���ڵ��ָ������
	 */
	class LNode{
		
		private String data;  //�洢����ֵ
		
		private int post;    //��ǰ�ڵ�λ��������0��ʼ
		
		private LNode next;  //ָ����һ�ڵ������
		
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
	
	private LNode head;   //����һ���ڵ�ͷ
	private int size;    //����һ������
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	//��ʼ������ͷHEAD
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

	//��ӽڵ�-β�巨
	 public void add( String value ){
		 LNode ptr = null;    //��ǰ�ڵ�
		 LNode pnext = null;  //��һ�ڵ�
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
	 
	//��ӽڵ�-ָ��λ����ӽڵ�
	 public void add( int post, String value ){
		if( post > this.size ){
			System.out.println("Exception����IndexOutOfBoundsException��POST��"+post+", SIZE��"+size);
		}else if( post == this.size ){
			this.add(value);
		}else{
			LNode ptr = null;    //��ǰ�ڵ�
			LNode pnext = null;  //��һ�ڵ�
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
						//�����½ڵ����Ľڵ�λ������+1
						 while( pnext!=null ){
							 ptr = pnext;
							 pnext.setPost(ptr.getPost()+1);
							 pnext = ptr.next;
						 }
						 size++;
						 break;
					 }else if( pnext.post == post ){
						 newNode = new LNode(value);   //��ʼ���½ڵ�
						 newNode.setPost(pnext.post);
						 ptr.setNext(newNode);      
						 ptr.getNext().setNext(pnext);  //���º�̽ڵ��ϵ
						//�����½ڵ����Ľڵ�λ������+1
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
	 
	 
	 //ͨ��������ȡ��ǰ�ڵ�
	 public LNode get( int post ){
		 LNode ptr = null;    //��ǰ�ڵ�
		 LNode pnext = null;  //��һ�ڵ�
		 
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
	 
	 //ɾ��ָ������λ���ϵĽڵ�
	 public void remove( int post ){
		 
	 }
	 
	 public void printLink(){
		 LNode pnext = null;
		 if( head != null ){
			 pnext = head.next;
			 System.out.println("date��"+head.data+"  post��"+head.post);
		 }
		 while( pnext != null ){
			 System.out.println("date��"+pnext.data+"  post��"+pnext.post);
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
		 System.out.println("SIZE�aaaawed�" + owll.getSize());

		 LNode node = owll.get(3);
		 if( null!=node ){
			 System.out.println(node.getData());
		 }
	}
	
}
