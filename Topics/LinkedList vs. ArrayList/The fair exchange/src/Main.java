
class ListOperations {
    
    public static void changeHeadsAndTails(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        String head = linkedList.removeFirst();
        String tail = linkedList.removeLast();
        
        linkedList.addFirst(arrayList.get(0));
        linkedList.addLast(arrayList.get(arrayList.size() - 1));
        
        arrayList.set(0, head);
        arrayList.set(arrayList.size() - 1, tail);
    }
}
