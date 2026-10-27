public class ArrayQueue<T> implements QueueInterface<T>
{
    private T[] queue;
    private int size;
    private int indexStart; 
    private int indexEnd; 

    public ArrayQueue()
    {
        queue = (T[]) new Object[4];
        size = 0;
    }

    @Override 
    public int size()
    {
        return size;
    }

    @Override 
    public boolean empty()
    {
        return size == 0;
    }

    @Override 
    public T peek() throws QueueUnderflowException
    {
        if (empty())
        {
            throw new QueueUnderflowException("Queue is empty");
        }
        else
        {
            return queue[indexStart];
        }
    }
    
    @Override 
    public T remove() throws QueueUnderflowException
    {
        if(empty())
        {
            throw new QueueUnderflowException("Queue is Empty");
        }
        else
        {
            T pushed = queue[indexStart];
            queue[indexStart] = null;
            size--;

            if (size > 0)
            {
                if (indexStart == queue.length - 1)
                {
                    indexStart = 0;
                }
                else
                {
                    indexStart++;
                }
            }
            else
            {
                indexStart = 0;
                indexEnd = 0;
            }
            return pushed;
        }
    }
    
    @Override 
    public T add(T item)
    {
        
        if (size == this.queue.length)
        {
            doubleLength();
        }
        queue[indexEnd]=item;
        if (indexEnd == queue.length - 1)
        {
            indexEnd = 0;
        }
        else
        {
            indexEnd++;
        }
        size++;
        return item;
    }
        

    @Override 
    public void clear()
    {
        queue = (T[]) new Object[4];
        size=0;
    }
 
    public int search(Object o)
    {
        int count = 1;
        int index = size - 1;

        while (index >= 0)
        {
            if ((queue[index] == null && o == null) || (queue[index] != null && queue[index].equals(o)))
            {
                return count;
            }

            index--;
            count++;
        }

        return -1;
    }

   public void doubleLength()
    {
        int oldLength = queue.length;
        T[] tempArray = (T[]) new Object[oldLength * 2];

        for (int i = 0; i < size; i++)
        {
            tempArray[i] = queue[(indexStart + i) % oldLength];
        }

        queue = tempArray;
        indexStart = 0;
        indexEnd = size;
    }
    public void halfLength()
    {
        int oldLength = queue.length;
        int newLength = oldLength / 2;
        T[] tempArray = (T[]) new Object[newLength];

        for (int i = 0; i < size; i++)
        {
            tempArray[i] = queue[(indexStart + i) % oldLength];
        }

        queue = tempArray;
        indexStart = 0;
        indexEnd = size % newLength;
    }
}