public class Hash {

    public static class SeparateChainingHash<Key, Value>
    {
        protected int M = 1000; // number of chains
        private Node[] st; // array of chains



        static class Node<Key, value>
        {
            protected Object key;
            protected Object val;
            protected Node<Key, value> next;



            public Node(Object key, Object val, Node next) {
                this.key = key;
                this.val = val;
                this.next = next;
            }

        }
        public SeparateChainingHash() {
            st = new Node[M];
        }
        protected int hashCode(Key key) {
            int hash = 0;
            String str = key.toString();
            for (int i = 0; i < str.length(); i++){
                hash = (hash * 31) + str.charAt(i);
            }

             return (key.hashCode() & 0x7fffffff) % M;
        }

        public Value get(Key key) {
            int i = hashCode(key);
            int comparisons = 0;
            for (Node x = st[i]; x != null; x = x.next){
                comparisons++;
                if (key.equals(x.key)){
                    System.out.println("Number of comparisons for the key " + key + " in Separate Chaining: " + comparisons);
                    return (Value) x.val;
                }

            }
            System.out.println("Number of comparisons for the key " + key + " in Separate Chaining: " + comparisons);
            return null;
        }

        public void put(Key key, Value val) {
            int i = hashCode(key);

            for (Node<Key,Value> x = st[i]; x != null; x = x.next) {
                if (key.equals(x.key)) {
                    x.val = val; // Update existing key's value
                    return;
                }
            }
            st[i] = new Node<>(key, val, st[i]); // Insert new node at the head of the chain
        }
        public Node<Key, Value> getBucket(int index) {
            if (index < 0 || index >= M) throw new IndexOutOfBoundsException("Invalid bucket index");
            return st[index];
        }
        public boolean contains(Key key) {
            int i = hashCode(key);
            for (Node x = st[i]; x != null; x = x.next){

                if (key.equals(x.key)){

                    return true;
                }

            }
            return false;

        }

    }

    public static class LinearProbingHashST<Key, Value> {
        private int M = 20000; // Fixed table size
        private Key[] keys; // Array for keys
        private Value[] vals; // Array for values

        public LinearProbingHashST() {
            keys = (Key[]) new Object[M];
            vals = (Value[]) new Object[M];
        }

        protected int hashCode(Key key) {
            int hash = 0;
            String str = key.toString();
            for (int i = 0; i < str.length(); i++){
                hash = (hash * 31) + str.charAt(i);
            }

            return (key.hashCode() & 0x7fffffff) % M;
        }

        public void put(Key key, Value val) {
            int i;
            for (i = hashCode(key); keys[i] != null; i = (i + 1) % M) {
                if (keys[i].equals(key)) {
                    vals[i] = val; // Update value if key exists
                    return;
                }
            }
            keys[i] = key;
            vals[i] = val;
        }

        public Value get(Key key) {
            int comparisons = 0;
            for (int i = hashCode(key); keys[i] != null; i = (i + 1) % M) {
                comparisons++;
                if (key.equals(keys[i])) {
                    System.out.println("Number of comparisons for the key " + key + " in Linear Probing: " + comparisons);
                    return vals[i];

                }
            }
            System.out.println("Number of comparisons for the key " + key + " in Linear Probing: " + comparisons);
            return null; // Key not found
        }

        public boolean contains(Key key) {
            for (int i = hashCode(key); keys[i] != null; i = (i + 1) % M) {
                if (key.equals(keys[i])) {
                    return true;

                }
            }
            return false;
        }


        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {

                if (keys[i] != null) {
                    sb.append("Index ").append(i).append(": Key = ").append(keys[i])
                            .append(", Value = ").append(vals[i]).append("\n");
                }
            }
            return sb.toString();
        }


    }



}
