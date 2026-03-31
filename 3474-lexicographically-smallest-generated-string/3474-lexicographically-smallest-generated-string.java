import java.util.*;

class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int L = n + m - 1;

        char[] word = new char[L];
        Arrays.fill(word, '?');

        // Step 1: Apply 'T'
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    int idx = i + j;
                    if (word[idx] == '?' || word[idx] == str2.charAt(j)) {
                        word[idx] = str2.charAt(j);
                    } else {
                        return "";
                    }
                }
            }
        }

        // Step 2: Fill remaining with 'a'
        for (int i = 0; i < L; i++) {
            if (word[i] == '?') word[i] = 'a';
        }

        // Step 3: Fix 'F'
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {

                if (matches(word, i, str2)) {

                    boolean fixed = false;

                    // try to break from rightmost
                    for (int j = m - 1; j >= 0; j--) {
                        int idx = i + j;

                        char original = word[idx];

                        for (char c = 'a'; c <= 'z'; c++) {
                            if (c == original) continue;

                            word[idx] = c;

                            // ensure no T is broken
                            if (!validT(word, str1, str2)) {
                                word[idx] = original;
                                continue;
                            }

                            // check this F is broken
                            if (!matches(word, i, str2)) {
                                fixed = true;
                                break;
                            }

                            word[idx] = original;
                        }

                        if (fixed) break;
                    }

                    if (!fixed) return "";
                }
            }
        }

        // Step 4: Final validation
        for (int i = 0; i < n; i++) {
            boolean match = matches(word, i, str2);

            if (str1.charAt(i) == 'T' && !match) return "";
            if (str1.charAt(i) == 'F' && match) return "";
        }

        return new String(word);
    }

    private boolean matches(char[] word, int start, String str2) {
        for (int j = 0; j < str2.length(); j++) {
            if (word[start + j] != str2.charAt(j)) return false;
        }
        return true;
    }

    private boolean validT(char[] word, String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (word[i + j] != str2.charAt(j)) return false;
                }
            }
        }
        return true;
    }
}