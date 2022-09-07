class ListOperations {
    public static void transferAllElements(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        for (int i = 0; i < linkedList.size(); i++) {
            linkedList.addLast(arrayList.get(i));
            arrayList.set(i, linkedList.removeFirst());
        }
    }
}
