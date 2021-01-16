package com.sun.jna;

import org.scijava.nativelib.NativeLibraryUtil;

import java.io.File;
import java.io.IOException;

public class NativeLoader {

    static {
        if (NativeLibraryUtil.getArchitecture() == NativeLibraryUtil.Architecture.OSX_ARM64) {
            try {
                String path = NativeLibraryUtil.getPlatformLibraryPath(NativeLibraryUtil.DEFAULT_SEARCH_PATH);
                File extracted = org.scijava.nativelib.NativeLoader.getJniExtractor().extractJni(path, "jnidispatch");
                if (extracted == null) {
                    throw new IllegalStateException("extract osx arm64 libjnidispatch.jnilib failed.");
                }
                extracted.deleteOnExit();
                System.setProperty("jna.boot.library.path", extracted.getParentFile().getAbsolutePath());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public static void loadAppleSilicon() {
        if (NativeLibraryUtil.getArchitecture() == NativeLibraryUtil.Architecture.OSX_ARM64) {
            System.setProperty("jna.nosys", "true");
        }
    }

}
