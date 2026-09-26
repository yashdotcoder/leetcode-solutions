class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> mapping = new HashMap<>();
        for (List<String> list: knowledge) {
            mapping.put(list.get(0), list.get(1));
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                StringBuilder key = new StringBuilder();

                while (s.charAt(++i) != ')') {
                    key.append(s.charAt(i));    
                }         

                String keyString = key.toString();

                if (mapping.containsKey(keyString)) {
                    String value = mapping.get(keyString);
                    sb.append(value);
                } else {
                    sb.append('?');
                }

            } else {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }
}