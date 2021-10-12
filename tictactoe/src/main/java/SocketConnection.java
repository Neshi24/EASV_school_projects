import io.socket.client.IO;
import io.socket.client.Socket;

import java.net.URI;

public class SocketConnection {

    public SocketConnection(){
        URI uri = URI.create("https://example.com");
        IO.Options options = IO.Options.builder()
                // ...
                .build();

        Socket socket = IO.socket(uri, options);
    }
}
