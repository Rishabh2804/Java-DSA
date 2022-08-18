public class TrieNode {
    boolean isEndOfWord;
    TrieNode[] children;

    public TrieNode() {
        isEndOfWord = false;
        children = new TrieNode[26];
    }

}
