package com.my.bytebuffer;

import java.nio.ByteBuffer;

public class ByteBufferTest {
    public static void main(String[] args) {
        ByteBuffer dfasdf = ByteBuffer.allocate(100);
        dfasdf.put(new byte[]{0x00, 0x00, 0x00, 0x01, 0x02, 0x00, 0x00, 0x00, 0x01, 0x03});
        dfasdf.clear();
        int posi = dfasdf.position();
        AnnexbSearch as = new AnnexbSearch();
        byte[] frame = annexbDemux(dfasdf);
        int posi1 = dfasdf.position();
    }

    private static byte[] annexbDemux(ByteBuffer bb) {
        AnnexbSearch annexbSearch = new AnnexbSearch();
        avcStartWithAnnexb(annexbSearch, bb);

        if (!annexbSearch.match || annexbSearch.startCode < 3) {
            return null;
        }

        for (int i = 0; i < annexbSearch.startCode; i++) {
            bb.get();
        }

        ByteBuffer frameBuffer = bb.slice();
        int pos = bb.position();
        while (bb.position() < 10) {
            avcStartWithAnnexb(annexbSearch, bb);
            if (annexbSearch.match) {
                break;
            }
            bb.get();
        }

        int size = bb.position() - pos;
        byte[] frameBytes = new byte[size];
        frameBuffer.get(frameBytes);
        return frameBytes;
    }

    private static void avcStartWithAnnexb(AnnexbSearch as, ByteBuffer bb) {
        as.match = false;
        as.startCode = 0;
        int pos = bb.position();
        while (pos < 4) {
            // not match.
            if (bb.get(pos) != 0x00 || bb.get(pos + 1) != 0x00) {
                break;
            }

            // match N[00] 00 00 01, where N>=0
            if (bb.get(pos + 2) == 0x01) {
                as.match = true;
                as.startCode = pos + 3 - bb.position();
                break;
            }
            pos++;
        }
    }

    static class AnnexbSearch {
        public int startCode = 0;
        public boolean match = false;
    }
}
