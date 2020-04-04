package com.twiagle.cache;

/**
 * @author
 * @date 7/22/19-3:19 PM
 */
public class CycQueue {
        private Long[] arr;//previous Request sequence
        private int MAXSIZE = 17; //循环队列最大长度为7  0~15+blank
        private int front;//头指针，若队列不为空，指向队头元素
        private int rear; //尾指针，若队列不为空，指向队列尾元素的下一个位置

        public CycQueue() {
            arr = new Long[MAXSIZE];
            front = rear = 0;
        }


    //入队前判满

        public void EnQueue(Long e) {
            //队列头指针在队尾指针的下一位位置上  说明满了 override it!
            if ((rear + 1) % MAXSIZE == front) {
                front = (front + 1) % MAXSIZE;
            }
            arr[rear] = e;
            rear = (rear + 1) % MAXSIZE;
        }

        public Long getLastElement(int lastWhat) {
            assert lastWhat >=0 ;
            if (lastWhat > QueueLength()) {
                return Long.MAX_VALUE;//no valid value
            }
            return arr[(rear - lastWhat + MAXSIZE) % MAXSIZE];
        }
    //useless       
        public CycQueue DestroyQueue() {
            arr = null;
            rear = front = 0;
            return this;
        }
    //出队前判空

        public Long DeQueue() {
                if (rear == front) {
                    return null;
                }
                Long e = arr[front];
                front = (front + 1) % MAXSIZE;
                return e; 
        }
        public CycQueue ClearQueue() {
            rear = front = 0;
            for (int i = 0; i < arr.length; i++) {
                arr[i] = null;
            }
            return this;
        }

        public Boolean isEmpty() {
            if (front == rear) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        }
        
        public Integer QueueLength() {
            return (rear - front + MAXSIZE) % MAXSIZE; //求环形队列的元素个数
        }

        public Long GetHead() {
            return arr[front];
        }
}
