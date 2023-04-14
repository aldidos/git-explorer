package jikim.git.explorer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;

import jikim.git.explorer.data.CommitHistory;
import jikim.git.explorer.log.Log;
import jikim.git.explorer.repository.ObjectParser;
import jikim.git.explorer.repository.RepositoryFactory;
import jikim.git.explorer.repository.commands.GitDiff;
import jikim.git.explorer.repository.commands.GitLog;

public class CommitHistoryExtraction 
{
    CommitHistoryExtractionOption option;

    public CommitHistoryExtraction(CommitHistoryExtractionOption option)
    {
        this.option = option;
    }

    public void run() throws IOException, NoHeadException, GitAPIException
    {
        for ( File repoDir : Paths.get( option.baseRepositoryDirPath ).toFile().listFiles() ) 
        {
            Log.log("from " + repoDir.toPath() );
            List<CommitHistory> listCommitHistory = extractCommitHistory( repoDir );
            String writePath = option.outputDirPath + repoDir.getName() + ".json";
            CommitHistory.writeToJSON(listCommitHistory, writePath);
        }
    }    

    public List<CommitHistory> extractCommitHistory(File repoDir) throws IOException, NoHeadException, GitAPIException
    {
        Repository repository = RepositoryFactory.create( repoDir.getAbsolutePath() );
        Iterable<RevCommit> iter = GitLog.logAll( repository );
        List<CommitHistory> listCommitHistory = new ArrayList<CommitHistory>();

        for ( RevCommit revCommit : iter ) 
        {
            RevCommit curCommit = ObjectParser.parseCommit( repository, revCommit.getId() );
            for( RevCommit pCommit : curCommit.getParents() ) 
            {
                RevCommit parentCommit = ObjectParser.parseCommit( repository, pCommit.getId() );
                List<DiffEntry> listDiffs = GitDiff.getDiffEntries(repository, curCommit, parentCommit);
                
                CommitHistory commitHistory = createCommitHistory(curCommit, parentCommit, listDiffs);
                listCommitHistory.add( commitHistory );
            }
        }

        return listCommitHistory;
    }

    private CommitHistory createCommitHistory(RevCommit curCommit, RevCommit parentCommit,  List<DiffEntry> listDiffs) 
    {
        return new CommitHistory(curCommit.name(), parentCommit.name(), curCommit.getFullMessage(), curCommit.getAuthorIdent(), curCommit.getCommitterIdent(), listDiffs);
    }
    
}
