package app;

import java.io.Serializable;

/**
 * Created by leon2 on 25.05.2017.
 */
public class Request implements Serializable {
    public int type;

    public Message sendMessage;

    public MessagePackage msgs;
    public int lastId;

    private static final long serialVersionUID = 1L;

    public Request( Message message )
    {
        this.type           = 1;
        this.sendMessage    = message;

        this.msgs   = null;
        this.lastId = -1;
    }

    public Request( MessagePackage msgs )
    {
        this.type   = 0;
        this.msgs   = msgs;

        this.sendMessage = null;
        this.lastId = -1;
    }

    public Request( int lastId ) {
        this.type = 0;
        this.lastId = lastId;

        this.sendMessage = null;
        this.msgs = null;
    }
}
