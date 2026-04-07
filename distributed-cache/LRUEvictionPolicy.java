import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy implements EvictionPolicy {
    private static class ListNode {
        String key;
        ListNode prev;
        ListNode next;
        ListNode(String key) {
            this.key = key;
        }
    }

    private final Map<String, ListNode> nodeMap = new HashMap<>();
    private final ListNode head;
    private final ListNode tail;
    public LRUEvictionPolicy() {
        head = new ListNode("-1");
        tail = new ListNode("-1");
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public void keyAccessed(String key) {
        if (nodeMap.containsKey(key)) {
            ListNode node = nodeMap.get(key);
            remove(node);
            insertAfterHead(node);
            return;
        }

        ListNode node = new ListNode(key);
        nodeMap.put(key, node);
        insertAfterHead(node);
    }

    @Override
    public String getKeyToEvict() {

        if (tail.prev == head) {
            return null;
        }

        return tail.prev.key;
    }

    @Override
    public void removeKey(String key) {

        ListNode node = nodeMap.remove(key);
        if (node != null) {
            remove(node);
        }
    }

    private void insertAfterHead(ListNode node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void remove(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }
}