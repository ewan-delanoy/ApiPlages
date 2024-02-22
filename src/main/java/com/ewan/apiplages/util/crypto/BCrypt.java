package com.ewan.apiplages.util.crypto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

public class BCrypt {

    private static final int[] bf_crypt_ciphertext = new int[]{1332899944, 1700884034, 1701343084, 1684370003, 1668446532, 1869963892};
    private static final char[] base64_code = new char[]{'.', '/', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final byte[] index_64 = new byte[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, -1, -1, -1, -1, -1, -1, -1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, -1, -1, -1, -1, -1, -1, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, -1, -1, -1, -1, -1};


    static void encode_base64(byte[] d, int len, StringBuilder rs) throws IllegalArgumentException {
        int off = 0;
        if (len > 0 && len <= d.length) {
            while(off < len) {
                int c1 = d[off++] & 255;
                rs.append(base64_code[c1 >> 2 & 63]);
                c1 = (c1 & 3) << 4;
                if (off >= len) {
                    rs.append(base64_code[c1 & 63]);
                    break;
                }

                int c2 = d[off++] & 255;
                c1 |= c2 >> 4 & 15;
                rs.append(base64_code[c1 & 63]);
                c1 = (c2 & 15) << 2;
                if (off >= len) {
                    rs.append(base64_code[c1 & 63]);
                    break;
                }

                c2 = d[off++] & 255;
                c1 |= c2 >> 6 & 3;
                rs.append(base64_code[c1 & 63]);
                rs.append(base64_code[c2 & 63]);
            }

        } else {
            throw new IllegalArgumentException("Invalid len");
        }
    }

    private static byte char64(char x) {
        return x >= 0 && x < index_64.length ? index_64[x] : -1;
    }

    static byte[] decode_base64(String s) throws IllegalArgumentException {
        StringBuilder rs = new StringBuilder();
        int off = 0;
        int slen = s.length();
        int olen = 0;
        if (16 <= 0) {
            throw new IllegalArgumentException("Invalid maxolen");
        } else {
            while(off < slen - 1 && olen < 16) {
                byte c1 = char64(s.charAt(off++));
                byte c2 = char64(s.charAt(off++));
                if (c1 == -1 || c2 == -1) {
                    break;
                }

                byte o = (byte)(c1 << 2);
                o = (byte)(o | (c2 & 48) >> 4);
                rs.append((char)o);
                ++olen;
                if (olen >= 16 || off >= slen) {
                    break;
                }

                byte c3 = char64(s.charAt(off++));
                if (c3 == -1) {
                    break;
                }

                o = (byte)((c2 & 15) << 4);
                o = (byte)(o | (c3 & 60) >> 2);
                rs.append((char)o);
                ++olen;
                if (olen >= 16 || off >= slen) {
                    break;
                }

                byte c4 = char64(s.charAt(off++));
                o = (byte)((c3 & 3) << 6);
                o |= c4;
                rs.append((char)o);
                ++olen;
            }

            byte[] ret = new byte[olen];

            for(off = 0; off < olen; ++off) {
                ret[off] = (byte)rs.charAt(off);
            }

            return ret;
        }
    }

    private static String hashpwforcheck(byte[] passwordb, String salt) {
        return hashpw_generic(passwordb, salt, true);
    }

    public static String hashpw(String password, String salt) {
        byte[] passwordb = password.getBytes(StandardCharsets.UTF_8);
        return hashpw_as_bytes_array(passwordb, salt);
    }

    public static String hashpw_as_bytes_array(byte[] passwordb, String salt) {
        return hashpw_generic(passwordb, salt, false);
    }

    private static String hashpw_generic(byte[] passwordb, String salt, boolean for_check) {
        char minor = 0;
        StringBuilder rs = new StringBuilder();
        if (salt == null) {
            throw new IllegalArgumentException("salt cannot be null");
        } else {
            int saltLength = salt.length();
            if (saltLength < 28) {
                throw new IllegalArgumentException("Invalid salt");
            } else if (salt.charAt(0) == '$' && salt.charAt(1) == '2') {
                byte off;
                if (salt.charAt(2) == '$') {
                    off = 3;
                } else {
                    minor = salt.charAt(2);
                    if (minor != 'a' && minor != 'x' && minor != 'y' && minor != 'b' || salt.charAt(3) != '$') {
                        throw new IllegalArgumentException("Invalid salt revision");
                    }

                    off = 4;
                }

                if (salt.charAt(off + 2) > '$') {
                    throw new IllegalArgumentException("Missing salt rounds");
                } else if (off == 4 && saltLength < 29) {
                    throw new IllegalArgumentException("Invalid salt");
                } else {
                    int rounds = Integer.parseInt(salt.substring(off, off + 2));
                    String real_salt = salt.substring(off + 3, off + 25);
                    byte[] saltb = decode_base64(real_salt);
                    if (minor >= 'a') {
                        passwordb = Arrays.copyOf(passwordb, passwordb.length + 1);
                    }

                    BInnerCrypt B = new BInnerCrypt();
                    byte[] hashed = B.crypt_raw(passwordb, saltb, rounds, minor == 'x', minor == 'a' ? 65536 : 0, for_check);
                    rs.append("$2");
                    if (minor >= 'a') {
                        rs.append(minor);
                    }

                    rs.append("$");
                    if (rounds < 10) {
                        rs.append("0");
                    }

                    rs.append(rounds);
                    rs.append("$");
                    encode_base64(saltb, saltb.length, rs);
                    encode_base64(hashed, bf_crypt_ciphertext.length * 4 - 1, rs);
                    return rs.toString();
                }
            } else {
                throw new IllegalArgumentException("Invalid salt version");
            }
        }
    }

    public static String gensalt(String prefix, int log_rounds, SecureRandom random) throws IllegalArgumentException {
        StringBuilder rs = new StringBuilder();
        byte[] rnd = new byte[16];
        if (!prefix.startsWith("$2") || prefix.charAt(2) != 'a' && prefix.charAt(2) != 'y' && prefix.charAt(2) != 'b') {
            throw new IllegalArgumentException("Invalid prefix");
        } else if (log_rounds >= 4 && log_rounds <= 31) {
            random.nextBytes(rnd);
            rs.append("$2");
            rs.append(prefix.charAt(2));
            rs.append("$");
            if (log_rounds < 10) {
                rs.append("0");
            }

            rs.append(log_rounds);
            rs.append("$");
            encode_base64(rnd, rnd.length, rs);
            return rs.toString();
        } else {
            throw new IllegalArgumentException("Invalid log_rounds");
        }
    }

    public static String gensalt(String prefix, int log_rounds) throws IllegalArgumentException {
        return gensalt(prefix, log_rounds, new SecureRandom());
    }

    public static boolean checkpw(String plaintext, String hashed) {
        byte[] passwordb = plaintext.getBytes(StandardCharsets.UTF_8);
        return equalsNoEarlyReturn(hashed, hashpwforcheck(passwordb, hashed));
    }

    static boolean equalsNoEarlyReturn(String a, String b) {
        return MessageDigest.isEqual(a.getBytes(StandardCharsets.UTF_8), b.getBytes(StandardCharsets.UTF_8));
    }
}
