package com.foxminded.source;

import org.hibernate.SessionFactory;

public interface StartConnection {
    SessionFactory openSession();
}
