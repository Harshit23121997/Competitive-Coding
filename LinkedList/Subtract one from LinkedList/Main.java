class Main{
    static Node head;
    static class Node{
        int data;
        Node next;
        Node(int data){
            this.data=data;
        }
    }
    static int subtractOne(Node node){
        if(node==null){
            return -1;
        }
        int borrow=subtractOne(node.next);
        if(borrow==-1){
            if(node.data==0){
                node.data=9;
                return -1;
            }
            else{
                node.data=node.data-1;
                return 0;
            }
        }
        else{
            return 0;
        }
    }
    public static void main(String[] args) {
        head=new Node(1);
        head.next=new Node(2);
        head.next.next=new Node(0);
        subtractOne(head);
        while(head!=null){
            System.out.println(head.data);
            head=head.next;
        }
    }
}