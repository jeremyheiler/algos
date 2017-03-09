package algos;

// TODO: The stream needs to be as long as the message. Repeatedly cycle through state until it's long enough.

public class RC4 {

    private static byte[] ksa(byte[] key) {
        byte[] state = new byte[256];
        for (int i = 0; i < state.length; ++i) {
            state[i] = (byte) i;
        }
        for (int i = 0, j = 0; i < state.length; ++i) {
            j = (j + state[i] + key[i % key.length]) & 0xFF;

            byte t = state[i];
            state[i] = state[j];
            state[j] = t;
        }
        return state;
    }

    private static byte[] prga(byte[] state) {
        int i = 0, j = 0;
        byte[] output = new byte[1024];
        for (int x = 0; x < state.length; ++x) {
            i = (i + 1) & 0xFF;
            j = (j + state[i]) & 0xFF;

            byte t = state[i];
            state[i] = state[j];
            state[j] = t;

            output[x] = state[(state[i] + state[j]) & 0xFF];
        }
        return output;
    }

    private static byte[] run(byte[] stream, byte[] message) {
        byte[] output = new byte[message.length];
        int i = 0;
        for (byte b : message) {
            output[i] = (byte)((b ^ stream[i]) & 0xFF);
            ++i;
        }
        return output;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder buf = new StringBuilder();
        int col = 0;
        for (byte b : bytes) {
            if (col % 16 == 0) {
                buf.append("\n");
            }
            buf.append(String.format("%02X", b));
            ++col;
        }
        return buf.append("\n").toString();
    }

    public static void main(String[] args) {
        String key = "supersecretkey";
        byte[] state = ksa(key.getBytes());
        System.out.println("KSA:" + bytesToHex(state));

        byte[] stream = prga(state);
        System.out.println("PRGA:" + bytesToHex(stream));

        String message = "hello, world! how are you?";
        System.out.println("Message:\n" + message);

        byte[] encrypted = run(stream, message.getBytes());
        System.out.println("\nEncrypted Message:" + bytesToHex(encrypted));

        byte[] decrypted = run(stream, encrypted);
        System.out.println("Decrypted Message:\n" + new String(decrypted));
    }
}
