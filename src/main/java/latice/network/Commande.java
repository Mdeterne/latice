package latice.network;

import java.io.Serializable;

public class Commande implements Serializable{
    private String type;
    private Object data;

    public Commande(String type, Object data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public Object getData() {
        return data;
    }
}