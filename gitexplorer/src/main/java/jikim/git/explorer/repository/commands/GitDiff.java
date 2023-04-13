package jikim.git.explorer.repository.commands;

import java.io.IOException;
import java.util.List;

import org.eclipse.jgit.api.DiffCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.filter.PathSuffixFilter;

import jikim.git.explorer.repository.GitRepository;
import jikim.git.explorer.repository.RevTreeFactory;

public class GitDiff 
{
    private static DiffCommand createDiffCommand( Repository repository, RevCommit curCommit, RevCommit parentCommit ) 
        throws IOException
    {
        AbstractTreeIterator newTreeParser = RevTreeFactory.prepareTreeParser(repository, curCommit);
        AbstractTreeIterator oldTreeParser = RevTreeFactory.prepareTreeParser(repository, parentCommit);

        Git git = new Git( repository );
        DiffCommand diffCommand = git.diff()
            .setOldTree(oldTreeParser)
            .setNewTree(newTreeParser);

        git.close();

        return diffCommand;
    }

    public static List<DiffEntry> getDiffEntries( GitRepository gitRepo, RevCommit curCommit, RevCommit parentCommit ) 
        throws GitAPIException, IOException
    {
        DiffCommand diffCommand = createDiffCommand( gitRepo.getRepository(), curCommit, parentCommit);
        return diffCommand.call();
    }

    public static List<DiffEntry> getDiffEntries( GitRepository gitRepo, RevCommit curCommit, RevCommit parentCommit, PathSuffixFilter pathSuffixFilter ) 
        throws GitAPIException, IOException
    {
        DiffCommand diffCommand = createDiffCommand( gitRepo.getRepository(), curCommit, parentCommit);
        diffCommand.setPathFilter( pathSuffixFilter );
        return diffCommand.call();
    }

    public static List<DiffEntry> getDiffEntries( Repository repository, RevCommit curCommit, RevCommit parentCommit ) 
        throws GitAPIException, IOException
    {        
        DiffCommand diffCommand = createDiffCommand(repository, curCommit, parentCommit);
        return diffCommand.call();
    }

}
