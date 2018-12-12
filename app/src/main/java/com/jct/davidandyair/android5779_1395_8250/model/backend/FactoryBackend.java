package com.jct.davidandyair.android5779_1395_8250.model.backend;

public class FactoryBackend {
    private static IBackend backend;
    public static IBackend getBackend()
    {
        if(backend == null)
            backend = new FireBaseBackend();
        return backend;
    }
}
