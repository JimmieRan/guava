package com.linkedList;

public class OneWayLinkedList {

	/**
	 * ����һ��LNode�����洢�ڵ������ֵ����һ���ڵ��ָ������
	 */
	class LNode{
		
		private String data;  //�洢����ֵ
		
		private int post;    //��ǰ�ڵ�λ��
		
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

	//��ӽڵ�-β�巨
	 public void add( String value ){
		 LNode ptr = null;    //��ǰ�ڵ�
		 LNode pnext = null;  //��һ�ڵ�
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
