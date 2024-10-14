import java.util.ArrayList;
import java.util.List;

public class P2MaxHeap {
    private final int children;
    private final List<Integer> heap;

    public P2MaxHeap(int children) {
        this.children = children * children;
        this.heap = new ArrayList<>();
    }

    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    public int popMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        int max = heap.getFirst();
        int last = heap.removeLast();

        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }

        return max;
    }

    private void heapifyUp(int i) {
        int parent = (i - 1) / children;
        while (i > 0 && heap.get(i) > heap.get(parent)) {
            swap(i, parent);
            i = parent;
            parent = (i - 1) / children;
        }
    }

    private void heapifyDown(int i) {
        while (true) {
            int max = i;
            for (int j = 1; j <= children; j++) {
                int child = children * i + j;
                if (child < heap.size() && heap.get(child) > heap.get(max)) {
                    max = child;
                }
            }
            if (max == i) {
                break;
            }
            swap(i, max);
            i = max;
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public static void main(String[] args) {
        P2MaxHeap heap = new P2MaxHeap(3);
        heap.insert(10);
        heap.insert(5);
        heap.insert(3);
        heap.insert(2);
        heap.insert(4);
        heap.insert(7);
        heap.insert(6);
        heap.insert(8);
        heap.insert(9);
        heap.insert(1);

        System.out.println(heap.popMax()); // 10
        System.out.println(heap.popMax()); // 9
        System.out.println(heap.popMax()); // 8
        System.out.println(heap.popMax()); // 7
        System.out.println(heap.popMax()); // 6
        System.out.println(heap.popMax()); // 5
        System.out.println(heap.popMax()); // 4
        System.out.println(heap.popMax()); // 3
        System.out.println(heap.popMax()); // 2
        System.out.println(heap.popMax()); // 1
    }
}
