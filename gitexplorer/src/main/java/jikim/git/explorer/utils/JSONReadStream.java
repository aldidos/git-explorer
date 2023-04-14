package jikim.git.explorer.utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class JSONReadStream 
{    
    JsonReader reader;
    Gson gson;

    public JSONReadStream(String streamPath) throws IOException
    {
        this.gson = new Gson();
        this.reader = new JsonReader(Files.newBufferedReader( Paths.get(streamPath) ));        
        this.reader.beginArray();        
    }

    public Object read(Type type)
    {
        return gson.fromJson( reader, type );
    }

    public void close() throws IOException
    {
        reader.endArray();
        reader.close();
    }

}
