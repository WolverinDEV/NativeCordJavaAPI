package dev.wolveringer.nativecord.terminal;

import dev.wolveringer.Callback;

import java.io.*;

/**
 * Created by wolverindev on 06.11.16.
 */
public class Console {
    static class LinedOutputSteam extends OutputStream {
        public static final int DEFAULT_BUFFER_LENGTH = 2048;
        protected static final String LINE_SEPERATOR = System.getProperty("line.separator");
        protected boolean hasBeenClosed = false;
        protected byte[] buf;
        protected int count;
        private int bufLength;
        private Callback<String> call;

        public LinedOutputSteam(Callback<String> call) {
            this.call = call;
            bufLength = DEFAULT_BUFFER_LENGTH;
            buf = new byte[DEFAULT_BUFFER_LENGTH];
            count = 0;
        }

        public void close() {
            flush();
            hasBeenClosed = true;
        }


        public void write(final int b) throws IOException {
            if (hasBeenClosed) {
                throw new IOException("The stream has been closed.");
            }

            // don't log nulls
            if (b == 0) {
                return;
            }

            // would this be writing past the buffer?
            if (count == bufLength) {
                // grow the buffer
                final int newBufLength = bufLength+DEFAULT_BUFFER_LENGTH;
                final byte[] newBuf = new byte[newBufLength];

                System.arraycopy(buf, 0, newBuf, 0, bufLength);
                buf = newBuf;
                bufLength = newBufLength;
            }

            buf[count] = (byte)b;
            count++;
        }


        public void flush() {
            if (count == 0) {
                return;
            }
            // don't print out blank lines; flushing from PrintStream puts out these
            if (count == LINE_SEPERATOR.length()) {
                if ( ((char)buf[0]) == LINE_SEPERATOR.charAt(0)  &&
                        ( ( count == 1 ) ||  // <- Unix & Mac, -> Windows
                                ( (count == 2) && ((char)buf[1]) == LINE_SEPERATOR.charAt(1) ) ) ) {
                    reset();
                    return;
                }
            }

            final byte[] theBytes = new byte[count];
            System.arraycopy(buf, 0, theBytes, 0, count);
            call.done(new String(theBytes), null);
            reset();
        }


        private void reset() {
            // not resetting the buffer -- assuming that if it grew that it
            //   will likely grow similarly again
            count = 0;
        }

    }

    private static boolean setuped = false;

    public static native void logMessage(String message);
    public static native void logError(String message);
    public static native void debugMessage(String message);

    public static synchronized void setup(){
        if(setuped)
            return;
        setuped = true;
        System.setOut(new PrintStream(new LinedOutputSteam(new Callback<String>() {
            @Override
            public void done(String obj, Throwable ex) {
                Console.logMessage(obj);
            }
        }), true));
        System.setErr(new PrintStream(new LinedOutputSteam(new Callback<String>() {
            @Override
            public void done(String obj, Throwable ex) {
                Console.logError(obj);
            }
        }), true));
    }
}
