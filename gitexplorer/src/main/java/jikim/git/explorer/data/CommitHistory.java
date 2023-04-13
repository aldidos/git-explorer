package jikim.git.explorer.data;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.lib.PersonIdent;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class CommitHistory
{
    String id;
    String parentCommit;
    String message;

    PersonIdent author;
    PersonIdent committer;
    List<DiffEntry> diffs;

    public CommitHistory( String id, String parentCommit, String message, PersonIdent author, PersonIdent committer, List<DiffEntry> diffs )
    {
        this.id = id;
        this.parentCommit = parentCommit;
        this.message = message;
        this.author = author;
        this.committer = committer;
        this.diffs = diffs;
    }

    public static void writeToJSON(List<CommitHistory> listCommitHistory, String jsonPath) throws IOException
    {
        Gson gson = new Gson();
        Type type = new TypeToken<List<CommitHistory>>() {}.getType();
        JsonWriter writer = new JsonWriter( Files.newBufferedWriter( Paths.get( jsonPath ) ) );
        gson.toJson( listCommitHistory, type, writer);
        writer.close();
    }

    public static List<CommitHistory> readFromJSON(String jsonPath) throws IOException
    {
        Gson gson = new Gson();
        Type type = new TypeToken<List<CommitHistory>>() {}.getType();
        JsonReader reader = new JsonReader( Files.newBufferedReader( Paths.get( jsonPath ) ) );
        List<CommitHistory> listCommitHistory = gson.fromJson(reader, type);
        reader.close();
        
        return listCommitHistory;
    }   

}
