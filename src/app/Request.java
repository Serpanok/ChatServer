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

    @Override
    public int hashCode() {
        final int prime = 29;
        int result = 1;
        result = prime * result + sendMessage.hashCode();
        result = prime * result + msgs.hashCode();
        result = prime * result + type;
        result = prime * result + lastId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Request other = (Request) obj;
        if (type != other.type)
            return false;
        if (lastId != other.lastId)
            return false;
        if (!sendMessage.equals(other.sendMessage))
            return false;
        if (!msgs.equals(other.msgs))
            return false;
        return true;
    }
}
