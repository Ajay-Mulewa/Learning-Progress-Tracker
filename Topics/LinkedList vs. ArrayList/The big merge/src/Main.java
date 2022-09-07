
class ListOperations {
    public static void mergeLists(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        arrayList.forEach(linkedList::add);
        System.out.println("The new size of LinkedList is " + linkedList.size());
    }
}
