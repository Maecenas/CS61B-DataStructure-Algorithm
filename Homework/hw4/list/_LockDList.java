package list;

/* LockDList.java */

public class LockDList extends DList {


    public void lockNode(DListNode node) {
//        LockDListNode lNode = (LockDListNode) node;
//        lNode.isLocked = true;
        if (node instanceof LockDListNode) {
            ((LockDListNode) node).isLocked = true;
        }

    }

    protected DListNode newNode(Object item, DListNode prev, DListNode next) {
        return new LockDListNode(item, prev, next);
    }

    public void remove(DListNode node) {
        LockDListNode lNode = (LockDListNode) node;
        if (!lNode.isLocked) {
            super.remove(node);
        }
    }



}
