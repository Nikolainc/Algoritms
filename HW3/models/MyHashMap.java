package HW3.models;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MyHashMap<K, V> implements Map<K,V>, Iterable<V> {

    private transient Node<K, V>[] table;
    private int size = 0;
    private float threshold;

    public MyHashMap() {

        this.table = new Node[16];
        this.threshold = table.length * 0.75f;


    }

    private class Node<K, V> implements Map.Entry<K,V> {

        private final int hash;
        private K key;
        private V value;
        private List<Node<K, V>> listNodes;

        private Node(K key, V value) {

            this.key = key;
            this.value = value;
            this.listNodes = new LinkedList<Node<K,V>>();
            this.hash = this.hashCode();

        }

        private List<Node<K, V>> getNodes() {

            return this.listNodes;

        }

        private int indexHash() {

            return this.hash % table.length;

        }

        @Override
        public K getKey() {

            return this.key;

        }

        @Override
        public V getValue() {

            return this.value;

        }

        @Override
        public V setValue(V value) {

            return this.value = value;

        }

        @Override
        public int hashCode() {

            return key.hashCode() ^ 13 + key.hashCode();

        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) {

                return true;
                
            }

            if(obj != null && this.getClass() == obj.getClass()) {

                return Objects.equals(this.key, ((Node)(obj)).getKey()) && Objects.equals(this.value, 
                        ((Node) (obj)).getValue());

            }

            return false;

        }

    }

    @Override
    public int size() {

        return this.size;
        
    }

    @Override
    public boolean isEmpty() {

        return this.table == null;

    }

    @Override
    public boolean containsKey(Object key) {

        if(key == null || this.isEmpty()) {

            return false;

        }
        
        Node<K, V> findNode = new Node<>((K)key, null);
        int index = findNode.hash % this.table.length;

        if(this.table[index] == null) {

            return false;

        } else {

            List<Node<K, V>> nodeList = this.table[index].getNodes();

            for (Node<K,V> item : nodeList) {
                
                if(item.getKey().equals(findNode.getKey())) {

                    return true;

                }

            }

            return false;

        }

    }

    @Override
    public boolean containsValue(Object value) {

        if (value == null || this.isEmpty()) {

            return false;

        }

        for (Node<K,V> node : table) {

            if (node != null) {

                for (Node<K, V> item : node.getNodes()) {

                    if (item.getValue().equals(value)) {

                        return true;

                    }

                }
            
            }

        }

        return false;

    }

    @Override
    public V get(Object key) {

        if (key == null || this.isEmpty()) {

            return null;

        }

        Node<K, V> findNode = new Node<>((K) key, null);
        int index = findNode.hash % this.table.length;

        if (this.table[index] == null) {

            return null;

        } else {

            List<Node<K, V>> nodeList = this.table[index].getNodes();

            for (Node<K, V> item : nodeList) {

                if (item.getKey().equals(findNode.getKey())) {

                    return item.getValue();

                }

            }

            return null;

        }

    }

    @Override
    public V put(K key, V value) {

        if(this.size + 1 >= this.threshold) {

            this.threshold *= 2;
            //тут метод расширения

        }

        Node<K,V> newNode = new Node<>(key, value);
        int index = newNode.indexHash();

        if (this.table[index] == null) {

            this.table[index] = new Node<>(null, null);
            this.table[index].getNodes().add(newNode);
            this.size++;
            return newNode.getValue();

        }

        List<Node<K,V>> nodeList = this.table[index].getNodes();
        
        for (Node<K,V> item : nodeList) {

            if (newNode.getKey().equals(item.getKey()) && !newNode.getValue().equals(item.getValue())) {

                item.setValue(value);
                return value;

            } else if (newNode.hash == item.hash && (!newNode.getKey().equals(item.getKey()) && !newNode.getValue()
                    .equals(item.getValue()))) {

                nodeList.add(newNode);
                this.size++;
                return value;

            }
            
        }

        return null;

    }

    @Override
    public V remove(Object key) {

        if (key == null || this.isEmpty()) {

            return null;

        }

        Node<K, V> findNode = new Node<>((K) key, null);
        int index = findNode.hash % this.table.length;

        if (this.table[index] == null) {

            return null;

        } else {

            List<Node<K, V>> nodeList = this.table[index].getNodes();
            int itemIndex = 0;

            for (Node<K, V> item : nodeList) {

                if (item.getKey().equals(findNode.getKey())) {

                    V removed = item.getValue();
                    nodeList.remove(itemIndex);
                    this.size--;
                    return removed;

                }

                itemIndex++;

            }

            return null;

        }

    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'putAll'");
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    @Override
    public Set<K> keySet() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keySet'");
    }

    @Override
    public Collection<V> values() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'values'");
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'entrySet'");
    }

    @Override
    public Iterator<V> iterator() {
        
        return new Iterator<V>() {

            int counter = 0;
            int values = 0;
            Iterator<Node<K,V>> nodeIterator = null;

            @Override
            public boolean hasNext() {
                
                if(size == values) {

                    return false;

                }

                if(nodeIterator == null || !nodeIterator.hasNext()) {
                    
                    if(moveNext()) {

                        nodeIterator = table[counter].getNodes().iterator();

                    } else {

                        return false;

                    }

                }

                return nodeIterator.hasNext();

            }

            private boolean moveNext() {

                counter++;

                while(table[counter] == null) {

                    counter++;

                }

                return table[counter] != null;

            }

            @Override
            public V next() {

                values++;
                return nodeIterator.next().getValue();
                
            }
            
        };


    }
    
}
