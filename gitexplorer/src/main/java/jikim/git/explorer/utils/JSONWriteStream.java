package jikim.git.explorer.utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

public class JSONWriteStream 
{    
    JsonWriter writer; 
    Gson gson;

    public JSONWriteStream(String streamPath) throws IOException
    {
        this.gson = new Gson();
        this.writer = new JsonWriter( Files.newBufferedWriter( Paths.get( streamPath ) ) );
        this.writer.beginArray();
    }

    public void write(Object object, Type type)
    {
        gson.toJson(object, type, writer);
    }

    public void close() throws IOException
    {
        writer.endArray();
        writer.close();
    }
}
