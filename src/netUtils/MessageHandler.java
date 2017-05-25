package netUtils;

import app.Request;

import java.io.DataOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by leon2 on 25.05.2017.
 */

public interface MessageHandler {
    void handle(Request request, ObjectOutputStream outputStream);
}

