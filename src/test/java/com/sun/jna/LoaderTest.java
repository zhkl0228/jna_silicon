package com.sun.jna;

import junit.framework.TestCase;
import org.scijava.nativelib.NativeLibraryUtil;

public class LoaderTest extends TestCase {

    static {
        NativeLoader.loadAppleSilicon();
    }

    public void testAppleSilicon() {
        if (NativeLibraryUtil.getArchitecture() == NativeLibraryUtil.Architecture.OSX_ARM64) {
            assertTrue(Platform.is64Bit());
            assertTrue(Platform.isMac());
            assertEquals(8, Native.POINTER_SIZE);
        }
    }

}
