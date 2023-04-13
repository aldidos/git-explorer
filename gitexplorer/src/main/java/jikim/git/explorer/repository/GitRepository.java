package jikim.git.explorer.repository;

import java.io.IOException;

import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;

public class GitRepository 
{
    Repository repository;
    
    public GitRepository( Repository repository ) throws IOException
    {
        this.repository = repository;
    }
    
    public Repository getRepository()
    {
        return repository;
    }

    public ObjectId getMaster() 
        throws RevisionSyntaxException, AmbiguousObjectException, IncorrectObjectTypeException, IOException
    {
        return this.repository.resolve( Constants.MASTER );
    }

    public ObjectId getHeadId() 
        throws RevisionSyntaxException, AmbiguousObjectException, IncorrectObjectTypeException, IOException
    {
        return this.repository.resolve( Constants.HEAD );
    }

    public String getDiffString(DiffEntry diffEntry) throws IOException
    {
        return ContentLoader.loadDiffText(this.repository , diffEntry);
    }
    
}
