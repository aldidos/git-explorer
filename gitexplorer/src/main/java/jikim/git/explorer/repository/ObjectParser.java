package jikim.git.explorer.repository;

import java.io.IOException;

import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;

public class ObjectParser 
{   
    public static RevCommit parseCommit(Repository repository, ObjectId objectId) 
        throws MissingObjectException, IncorrectObjectTypeException, IOException
    {
        RevWalk walk = new RevWalk(repository);        
        RevCommit commit =  walk.parseCommit(objectId);
        walk.close();
        return commit;
    }

    public static RevTree parseTree( Repository repository, ObjectId objectId ) 
        throws MissingObjectException, IncorrectObjectTypeException, IOException
    {
        RevWalk walk = new RevWalk(repository);
        RevTree tree = walk.parseTree( objectId );
        walk.close();
        return tree;
    }
        
}

