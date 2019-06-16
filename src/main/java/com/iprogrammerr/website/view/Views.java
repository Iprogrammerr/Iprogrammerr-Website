package com.iprogrammerr.website.view;

import java.util.Map;

public interface Views {

    String view(String name);

    String rendered(String name, Map<String, Object> params);
}
