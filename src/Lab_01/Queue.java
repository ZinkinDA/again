package Lab_01;

class Queue {
    private int[] queue;
    private int maxSize; // максимальное количество элементов в очереди
    private int nElem;  // текущее количество элементов в очереди
    private int front;
    private int rear;

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        queue = new int[maxSize];
        rear = -1;
        front = 0;
        nElem = 0;
    }


    public Queue(Queue queue1) {
        this.maxSize = queue1.maxSize;
        queue = queue1.queue;
        rear = queue1.rear;
        front = queue1.front;
        nElem = queue1.nElem;
    }

    public void insert(int elem) {
        if (rear == maxSize - 1) {  // циклический перенос
            rear = -1;
        }

        queue[++rear] = elem;  //увеличение Rear и вставка
        nElem++;  // увеличение количества элементов в очереди
    }

    public int remove() {
        int temp = queue[front++]; // получаем первый элемент из очереди

        if (front == maxSize) { // циклический перенос
            front = 0;
        }
        nElem--; // уменьшаем количество элементов в очереди
        return temp;

    }
    public int getFront() {
        return queue[front];
    }

    public int getRear() {
        return queue[rear];
    }

    public boolean isFull() {
        return (nElem == maxSize - 1);
    }

    public boolean isEmpty() {
        return (nElem == 0);
    }

    public int getSize() {
        return nElem;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int q : queue) {
            if (q == queue[rear]) {
                stringBuilder.append(q);
            }
            else {
                stringBuilder.append(q).append(", ");
            }
        }
        return stringBuilder.toString();
    }

    public String toString(int c) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int q : queue) {
            if (q == queue[rear]) {
                stringBuilder.append(q);
            }
            else {
                stringBuilder.append(q).append(", ");
            }
        }
        return stringBuilder.toString();
    }
}
