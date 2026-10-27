public class LinkedQueue<T> implements QueueInterface<T>
{
    private LLNode<T> top;
    private int size;
    private LLNode<T> bottom; 

    public LinkedQueue()
    {
        top = new LLNode<T>(null);
        size = 0;
        bottom = top; 
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
            throw new QueueUnderflowException("Stack is empty");
        }
        else
        {
            return top.getLink().getInfo();
        }
    }
    
    @Override 
    public T remove() throws QueueUnderflowException
    {
        if(empty())
        {
            throw new QueueUnderflowException("Stack is Empty");
        }
        else
        {
            LLNode<T> pushed = top.getLink();
            top.setLink(pushed.getLink());
            size--;
            if (size == 0)
            {
                bottom = top;
            }
            return pushed.getInfo();
        }
    }
    
    @Override 
    public T add(T item)
    {
        LLNode<T> added = new LLNode<T>(item);
        bottom.setLink(added);
        bottom = added;
        size++;
        return item;
    }
        

    @Override 
    public void clear()
    {
        top.setLink(null);
        size=0;
    }

    public int search(Object o)
    {
        int count = 0;
        LLNode<T> using = top.getLink();

        while (using != null)
        {
            if ((using.getInfo() == null && o == null)|| (using.getInfo() != null && using.getInfo().equals(o)))
            {
                return count+1;
            }

            using = using.getLink();
            count++;
        }

        return -1;
    }
}