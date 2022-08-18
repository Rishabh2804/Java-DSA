public class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    private void add(TrieNode root, String key) {
        if (key.length() == 0) {
            root.isEndOfWord = true;
            return;
        }
        int childIndex = key.charAt(0) - 'a';
        TrieNode child = root.children[childIndex];
        if (child == null) {
            child = new TrieNode();
            root.children[childIndex] = child;
        }

        add(child, key.substring(1));
    }

    public void add(String word) {
        add(this.root, word);
    }

    private boolean search(TrieNode root, String key) {
        if (key.length() == 0) {
            return root.isEndOfWord;
        }

        int childIndex = key.charAt(0) - 'a';
        TrieNode child = root.children[childIndex];
        if (child == null)
            return false;
        else {
            return search(child, key.substring(1));
        }

    }

    public boolean search(String word) {
        return search(this.root, word);
    }
}
