class Node:
  def __init__(self, data=None):
    self.data = data
    self.next = None

# Creates a hardcoded Linked List
def create_list():
  head = Node(0)
  head.next = Node(1)
  return head
  
def print_list(head):
  current = head
  while current != None:
    print(current.data)
    current = current.next

def main():
  list = create_list()
  print_list(list)

if __name__ == "__main__":
  main()
