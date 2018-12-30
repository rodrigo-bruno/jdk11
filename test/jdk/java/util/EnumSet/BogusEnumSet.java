/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * @test
 * @bug     6739302
 * @summary Check that deserialization preserves EnumSet integrity
 * @author  Josh Bloch
 */

import java.util.*;
import java.io.*;

public class BogusEnumSet {
    public static void main(String[] args) throws Throwable {
        // This test depends on the current serialVersionUID of EnumSet,
        // which may change if the EnumSet class is modified.
        // The current value is -2409567991088730183L = 0xde8f7eadb5012fb9L
        // If the value changes, it will have to be patched into the
        // serialized byte stream below at the location noted.
        byte[] serializedForm  = {
            (byte)0xac, (byte)0xed, 0x0, 0x5, 0x73, 0x72, 0x0, 0x18,
            0x6a,  0x61,  0x76,  0x61, 0x2e,  0x75,  0x74,  0x69,
            0x6c,  0x2e, 0x52, 0x65, 0x67, 0x75, 0x6c, 0x61, 0x72, 0x45,
            0x6e, 0x75, 0x6d, 0x53, 0x65, 0x74, 0x2f, 0x58, 0x6f, (byte)0xc7,
            0x7e, (byte)0xb0, (byte)0xd0, 0x7e, 0x2, 0x0, 0x1, 0x4a, 0x0, 0x8,
            0x65, 0x6c, 0x65, 0x6d, 0x65, 0x6e, 0x74, 0x73, 0x78, 0x72, 0x0,
            0x11, 0x6a,  0x61,  0x76,  0x61, 0x2e,  0x75,  0x74,  0x69,
            0x6c,  0x2e, 0x45, 0x6e, 0x75, 0x6d, 0x53, 0x65, 0x74,
            // EnumSet's serialVersionUID is the following eight bytes (big-endian)
            (byte)0xde, (byte)0x8f, 0x7e, (byte)0xad, (byte)0xb5, (byte)0x01, 0x2f, (byte)0xb9,
            0x2, 0x0, 0x2, 0x4c, 0x0, 0xb, 0x65, 0x6c, 0x65, 0x6d, 0x65, 0x6e, 0x74,
            0x54, 0x79, 0x70, 0x65, 0x74, 0x0, 0x11, 0x4c, 0x6a, 0x61, 0x76,
            0x61, 0x2f, 0x6c, 0x61, 0x6e, 0x67, 0x2f, 0x43, 0x6c, 0x61, 0x73,
            0x73, 0x3b, 0x5b, 0x0, 0x8, 0x75, 0x6e, 0x69, 0x76, 0x65, 0x72,
            0x73, 0x65, 0x74, 0x0, 0x11, 0x5b, 0x4c, 0x6a, 0x61, 0x76, 0x61,
            0x2f, 0x6c, 0x61, 0x6e, 0x67, 0x2f, 0x45, 0x6e, 0x75, 0x6d, 0x3b,
            0x78, 0x70, 0x76, 0x72, 0x0, 0x16, 0x6a, 0x61, 0x76, 0x61, 0x2e,
            0x6c, 0x61, 0x6e, 0x67, 0x2e, 0x54, 0x68, 0x72, 0x65, 0x61,
            0x64, 0x24, 0x53, 0x74, 0x61, 0x74, 0x65, 0x0, 0x0, 0x0, 0x0, 0x0,
            0x0, 0x0, 0x0, 0x12, 0x0, 0x0, 0x78, 0x72, 0x0, 0xe, 0x6a, 0x61,
            0x76, 0x61, 0x2e, 0x6c, 0x61, 0x6e, 0x67, 0x2e, 0x45, 0x6e, 0x75,
            0x6d, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x12, 0x0, 0x0, 0x78,
            0x70, 0x75, 0x72, 0x0, 0x19, 0x5b, 0x4c, 0x6a, 0x61, 0x76, 0x61,
            0x2e, 0x6c, 0x61, 0x6e, 0x67, 0x2e, 0x54, 0x68, 0x72, 0x65, 0x61,
            0x64, 0x24, 0x53, 0x74, 0x61, 0x74, 0x65, 0x3b, 0x68, (byte)0xa3,
            (byte)0xb5, (byte)0xd5, 0x11, 0x7d, 0x1b, (byte)0xb3, 0x2, 0x0,
            0x0, 0x78, 0x70, 0x0, 0x0, 0x0, 0x6, 0x7e, 0x71, 0x0, 0x7e, 0x0,
            0x5, 0x74, 0x0, 0x3, 0x4e, 0x45, 0x57, 0x7e, 0x71, 0x0, 0x7e, 0x0,
            0x5, 0x74, 0x0, 0x8, 0x52, 0x55, 0x4e, 0x4e, 0x41, 0x42, 0x4c, 0x45,
            0x7e, 0x71, 0x0, 0x7e, 0x0, 0x5, 0x74, 0x0, 0x7, 0x42, 0x4c, 0x4f,
            0x43, 0x4b, 0x45, 0x44, 0x7e, 0x71, 0x0, 0x7e, 0x0, 0x5, 0x74, 0x0,
            0x7, 0x57, 0x41, 0x49, 0x54, 0x49, 0x4e, 0x47, 0x7e, 0x71, 0x0,
            0x7e, 0x0, 0x5, 0x74, 0x0, 0xd, 0x54, 0x49, 0x4d, 0x45, 0x44,
            0x5f, 0x57, 0x41, 0x49, 0x54, 0x49, 0x4e, 0x47, 0x7e, 0x71, 0x0,
            0x7e, 0x0, 0x5, 0x74, 0x0, 0xa, 0x54, 0x45, 0x52, 0x4d, 0x49,
            0x4e, 0x41, 0x54, 0x45, 0x44, (byte)0xff, (byte)0xff, (byte)0xff,
            (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff
        };

        try {
          // Should fail, but instead creates corrupt EnumSet
          @SuppressWarnings("unchecked")
          EnumSet<Thread.State> es = (EnumSet<Thread.State>)
              deserialize(serializedForm);

          // Demonstrates corruption
          System.out.println("Enum size: " + Thread.State.values().length); // 6
          System.out.println("Set size: " + es.size()); // 64
          System.out.println("Set: " + es); // Throws IndexOutOfBoundsException
          throw new AssertionError("Expected exception InvalidObjectException not thrown");
        } catch (java.io.InvalidObjectException expected) { /* OK */ }
    }

    private static Object deserialize(byte[] sf) throws Throwable {
      return new ObjectInputStream(
          new ByteArrayInputStream(sf))
          .readObject();
    }
}
