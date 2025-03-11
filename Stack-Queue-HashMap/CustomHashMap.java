import java.util.LinkedList;

class CustomHashMap<K, V> {

    private static final int SIZE = 16;
    private LinkedList<Entry<K, V>>[] buckets;

    public CustomHashMap() {
        buckets = new LinkedList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int getBucketIndex(K key) {
        return Math.abs(key.hashCode()) % SIZE;
    }

    public void put(K key, V value) {
        int index = getBucketIndex(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        bucket.add(new Entry<>(key, value));
    }

    public V get(K key) {
        int index = getBucketIndex(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public void remove(K key) {
        int index = getBucketIndex(key);
        LinkedList<Entry<K, V>> bucket = buckets[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                bucket.remove(entry);
                return;
            }
        }
    }

    private static class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        map.put("Alice", 25);
        map.put("Bob", 30);
        map.put("Charlie", 35);

        System.out.println("Alice's age: " + map.get("Alice"));
        System.out.println("Bob's age: " + map.get("Bob"));

        map.put("Alice", 26);
        System.out.println("Alice's updated age: " + map.get("Alice"));

        map.remove("Bob");
        System.out.println("Bob's age after removal: " + map.get("Bob"));
    }
}
