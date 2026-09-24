class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Character> mapping = new HashMap<>();
        Map<Character, Character> reverseMapping = new HashMap<>();

        for (int i = 0; i < s.length(); ++i) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            if (mapping.containsKey(charS)) {
                if (mapping.get(charS) != charT) {
                    return false;
                }
            }

            if (reverseMapping.containsKey(charT)) {
                if (reverseMapping.get(charT) != charS) {
                    return false;
                }
            }

            mapping.put(charS, charT);
            reverseMapping.put(charT, charS);
        }

        return true;
    }
}