package Helpers;

public interface XMLBuilder<T> {
    XMLBuilder<T> addOpenTag(String tag);

    XMLBuilder<T> addAttribute(String name, String value);

    XMLBuilder<T> addData(String data);

    XMLBuilder<T> addCloseTag(String tag);

    T getRoot();
}

