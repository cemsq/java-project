package impl;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;

public class SimpleQueueTest {

    @Test
    public void shouldAdd() {
        SimpleQueue<Integer> queue = createQueue(1, 2, 4, 5);

        Assert.assertEquals(queue.size(), 4);
    }

    @Test
    public void shouldGetFirst() {
        SimpleQueue<Integer> queue = createQueue(3, 4, 5, 6, 7);

        Assert.assertEquals(queue.peek(), Integer.valueOf(3));
        Assert.assertEquals(queue.size(), 5);
    }

    @Test
    public void shouldRemoveFirst() {
        SimpleQueue<Integer> queue = createQueue(3, 4, 5, 6, 7);

        Assert.assertEquals(queue.poll(), Integer.valueOf(3));
        Assert.assertEquals(queue.size(), 4);
    }

    @SafeVarargs
    public final <T> SimpleQueue<T> createQueue(T... elements) {
        SimpleQueue<T> queue = new SimpleQueue<>();

        Collections.addAll(queue, elements);

        return queue;
    }
}
