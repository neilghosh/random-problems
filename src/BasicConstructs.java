import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

class BasicConstructs {
  Logger logger = Logger.getAnonymousLogger();

  int[] nums = { 0, 1, 2, 3, 4, 5 };
  char[] chars;
  List<String> list;
  Map<String, String> map;

  class Node {
    int value;
    Node next;

    Node(int value) {
      this.value = value;
    }
  }

  BasicConstructs() {
    this.list = List.of("s1", "s2", "s3"); // Initialize
    this.map = Map.of("key1", "value1", "key2", "value2", "key3", "value3");
    this.chars = "This is a String".toCharArray();
  }

  public void iterateList() {
    logger.info("Iterating list");

    List<String> list = new ArrayList<>(this.list);
    list.add("s4");
    list.remove("s4");
    list.size(); // size

    for (String s : list) { // Iterate
      System.out.println(s);
    }
  }

  public void iterateMap() {
    // Maps
    Map<String, String> map = new HashMap<>(this.map);
    map.put("key4", "value4");
    map.remove("key4");
    map.size();

    // Iterate by key
    for (Entry<String, String> entry : map.entrySet()) {
      System.out.println(entry.getKey());
    }

    for (String key : map.keySet()) {
      System.out.println(map.get(key));
    }
  }

  public void charOperations() {
    System.out.println(Character.getNumericValue('a'));
    System.out.println('z' - 'a');
    StringBuilder sb = new StringBuilder();
    for (char c : chars) {
      sb.append(c);
    }
    System.out.println(sb.toString());
  }

  public Node buildList() {
    Node head = new Node(0);
    head.next = new Node(1);
    return head;
  }

  public void iterateList(Node head) {
    Node current = head;
    while (current != null) {
      System.out.println(current.value);
      current = current.next;
    }
  }

  public void stringSlice() {
    String string = "0123456789";
    System.out.println(string.charAt(0));
    System.out.println(string.substring(1, 5));
  }

  public Node arrayToLinkedList(int[] values) {
    Node head = null;
    Node current = null;
    for (int n : values) {
      Node newNode = new Node(n);
      if (current == null) {
        head = newNode;
        current = head;
      } else {
        current.next = newNode;
        current = newNode;
      }
    }
    return head;
  }

  public static void main(String[] args) {
    BasicConstructs bc = new BasicConstructs();
    // bc.iterateList();
    // bc.iterateMap();
    // bc.charOperations();
    // bc.iterateList(bc.buildList());
    // bc.stringSlice();
    Node head = bc.arrayToLinkedList(bc.nums);
    bc.iterateList(head);
  }
}