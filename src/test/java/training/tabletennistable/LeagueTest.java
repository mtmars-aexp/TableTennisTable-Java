package training.tabletennistable;

import net.bytebuddy.pool.TypePool;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class LeagueTest {
    @Test
    public void testAddPlayer()
    {
        // Given
        League league = new League();

        // When
        league.addPlayer("Bob");

        // Then
        List<LeagueRow> rows = league.getRows();
        Assert.assertEquals(1, rows.size());
        List<String> firstRowPlayers = rows.get(0).getPlayers();
        Assert.assertEquals(1, firstRowPlayers.size());
        Assert.assertThat(firstRowPlayers, IsCollectionContaining.hasItem("Bob"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddPlayerInvalidName(){
        League league = new League();

        league.addPlayer("this name doesnt match the regex 420");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddPlayerDuplicate(){
        League league = new League();

        league.addPlayer("twosixteen");
        league.addPlayer("twosixteen");
    }

    @Test
    public void testGetRows(){

        League league = new League();

        league.addPlayer("twosixteen");
        league.addPlayer("Josie");

        List<LeagueRow> rows = league.getRows();
        Assert.assertEquals("twosixteen", rows.get(0).getPlayers().get(0));
        Assert.assertEquals("Josie", rows.get(1).getPlayers().get(0));

    }

    @Test
    public void testRecordWin(){

        League league = new League();

        league.addPlayer("0216");
        league.addPlayer("5890");
        league.addPlayer("Hex");
        league.addPlayer("Josie");
        league.addPlayer("10121F");

        league.recordWin("5890", "0216");

        Assert.assertEquals("5890", league.getRows().get(0).getPlayers().get(0));
        Assert.assertEquals("0216", league.getRows().get(1).getPlayers().get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRecordWinPlayerNotInGame(){

        League league = new League();
        league.recordWin("0216", "5890");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRecordWinWinnerAheadOfLoser(){
        League league = new League();
        league.addPlayer("0216");
        league.addPlayer("5890");
        league.recordWin("0216", "5890");
    }

    @Test
    public void testGetWinner(){
        League league = new League();

        league.addPlayer("twosixteen");
        league.addPlayer("10121F");
        league.addPlayer("Hex");

        Assert.assertEquals("twosixteen", league.getWinner());
    }

    @Test
    public void testGetWinnerNoPlayers(){
        League league = new League();

        Assert.assertNull(league.getWinner());
    }
}
